package com.ipaixao.ibeer.domain.manufacturer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    @EntityGraph(value = "Beer", attributePaths = {"id"})
    Optional<Manufacturer> findByName(String name);

    @EntityGraph(value = "Beer", attributePaths = {"id"})
    Optional<Manufacturer> findByNameAndIdNot(String name, long id);
}

