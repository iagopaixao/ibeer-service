package com.ipaixao.ibeer.domain.manufacturer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @EntityGraph(attributePaths = {"beers"})
    Optional<Manufacturer> findById(long id);

    @NonNull
    @EntityGraph(attributePaths = {"beers"})
    Page<Manufacturer> findAll(@NonNull Pageable pageable);

    @Query("SELECT id from Manufacturer WHERE name = :name")
    Optional<Long> findIdByName(@Param("name") String name);

    @Query("SELECT id from Manufacturer WHERE id = :id AND name = :name ")
    Optional<Long> findIdByNameAndIdNot(@Param("name") String name, @Param("id") long id);
}

