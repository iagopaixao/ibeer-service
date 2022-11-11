package com.ipaixao.ibeer.domain.manufacturer;

import com.ipaixao.ibeer.infrastructure.db.DataSourceRoutingConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManufacturerDataProvider {

    private final ManufacturerRepository repository;
    private final DataSource dataSource;

    @Transactional
    public Manufacturer create(Manufacturer manufacturer) {
        try (Connection conn = dataSource.getConnection()) {
            log.info("DB INSTANCE: {}", conn.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repository.save(manufacturer);
    }

    public Page<Manufacturer> getAll(Pageable pageable) {
        try (Connection conn = dataSource.getConnection()) {
            log.info("DB INSTANCE: {}", conn.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repository.findAll(pageable);
    }

    public Optional<Manufacturer> getById(long id) {
        try (Connection conn = dataSource.getConnection()) {
            log.info("DB INSTANCE: {}", conn.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repository.findById(id);
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Optional<Manufacturer> getByNameAndIdNot(String name, Long id) {
        try (Connection conn = dataSource.getConnection()) {
            log.info("DB INSTANCE: {}", conn.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repository.findByNameAndIdNot(name, id);
    }

    public Optional<Manufacturer> getByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            log.info("DB INSTANCE: {}", conn.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repository.findByName(name);
    }
}
