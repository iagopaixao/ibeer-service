package com.ipaixao.ibeer.domain.beer;

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
public interface BeerRepository extends JpaRepository<Beer, Long> {

    @EntityGraph(attributePaths = {"manufacturer"})
    Optional<Beer> findById(long id);

    @NonNull
    @EntityGraph(attributePaths = {"manufacturer"})
    Page<Beer> findAll(@NonNull Pageable pageable);

    @Query("SELECT id from Beer WHERE name = :name")
    Optional<Long> getIdByName(@Param("name") String name);

    @Query("SELECT id from Beer WHERE id = :id AND name = :name ")
    Optional<Long> getIdByNameAndIdNot(@Param("name") String name, @Param("id") long id);
}
