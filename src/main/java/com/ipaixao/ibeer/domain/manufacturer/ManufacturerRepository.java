package com.ipaixao.ibeer.domain.manufacturer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Optional<Manufacturer> findByName(String name);
    Optional<Manufacturer> findByNameAndIdNot(String name, long id);

    /*@Override
    @EntityGraph(value = "Manufacturer", attributePaths = { "beers" })
    Optional<Manufacturer> findById(@Param("id") Long id);*/

    /*Optional<SimpleManufacturer> getSimpleById(Long id);*/

}

