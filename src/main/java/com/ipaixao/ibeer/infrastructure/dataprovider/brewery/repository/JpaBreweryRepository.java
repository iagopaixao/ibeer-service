package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.repository;

import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.entity.Brewery;
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
public interface JpaBreweryRepository extends JpaRepository<Brewery, Long> {

    @EntityGraph(attributePaths = {"beers"})
    Optional<Brewery> findById(long id);

    @NonNull
    @EntityGraph(attributePaths = {"beers"})
    Page<Brewery> findAll(@NonNull Pageable pageable);

    @Query("SELECT id from Brewery WHERE name = :name")
    Optional<Long> findIdByName(@Param("name") String name);

    @Query("SELECT id from Brewery WHERE name = :name AND id <> :id ")
    Optional<Long> findIdByNameAndIdNot(@Param("name") String name, @Param("id") long id);
}

