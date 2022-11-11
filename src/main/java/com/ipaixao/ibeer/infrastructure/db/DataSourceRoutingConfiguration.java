package com.ipaixao.ibeer.infrastructure.db;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

import static com.ipaixao.ibeer.infrastructure.db.TransactionRoutingDataSource.DataSourceType.READ_ONLY;
import static com.ipaixao.ibeer.infrastructure.db.TransactionRoutingDataSource.DataSourceType.READ_WRITE;

@Slf4j
@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
@EnableTransactionManagement
public class DataSourceRoutingConfiguration {

    private Map<Object, Object> dataSources;
    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 4;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @DependsOn({"primaryDataSourceProperties", "secondaryDataSourceProperties"})
    public DataSource routingDataSources(@Qualifier("primaryDataSourceProperties") DataSourceProperties primaryDataSourceProperties,
                                         @Qualifier("secondaryDataSourceProperties") DataSourceProperties secondaryDataSourceProperties) {
        dataSources = Map.of(
                READ_WRITE, buildDataSource("PrimaryHikariPool", primaryDataSourceProperties),
                READ_ONLY, buildDataSource("ReplicaHikariPool", secondaryDataSourceProperties)
        );
        final var routingDataSource = new TransactionRoutingDataSource();

        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(dataSources.get(READ_WRITE));

        return routingDataSource;
    }

    @Bean
    @DependsOn("routingDataSources")
    @ConfigurationProperties(prefix = "spring.liquibase")
    public SpringLiquibase primaryLiquibase(LiquibaseProperties liquibaseProps) {
        return buildLiquibase(dataSources.get(READ_WRITE), liquibaseProps);
    }

    @Bean
    @DependsOn("routingDataSources")
    @ConfigurationProperties(prefix = "spring.liquibase")
    public SpringLiquibase secondaryLiquibase(LiquibaseProperties liquibaseProps) {
        return buildLiquibase(dataSources.get(READ_ONLY), liquibaseProps);
    }

    private DataSource buildDataSource(String poolName, DataSourceProperties dbProps) {
        final var hikariDataSource = dbProps.initializeDataSourceBuilder()
                                                            .type(HikariDataSource.class)
                                                            .build();
        hikariDataSource.setPoolName(poolName);
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);

        return hikariDataSource;
    }

    private SpringLiquibase buildLiquibase(Object dataSource, LiquibaseProperties properties) {
        log.debug("Configuring Liquibase...");

        final var liquibase = new SpringLiquibase();
        liquibase.setDataSource((DataSource) dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());

        log.debug("It's Ok! Liquibase configured.");
        return liquibase;
    }
}
