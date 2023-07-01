package com.ipaixao.ibeer.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SwaggerConfig.SwaggerPropertiesConfig.class)
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(SwaggerPropertiesConfig properties) {
        return new OpenAPI()
                .info(new Info()
                        .title(properties.title)
                        .description(properties.description)
                        .version(properties.version)
                        .contact(new Contact()
                                .name(properties.contactName)
                                .url(properties.contactUrl)
                                .email(properties.contactEmail)
                        )
                        .license(new License()
                                .name(properties.license)
                        )
                );
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "api-info.swagger-ui")
    static class SwaggerPropertiesConfig {
        private String title;
        private String description;
        private String license;
        private String version;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
    }
}
