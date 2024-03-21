package com.ipaixao.ibeer.infrastructure.dataprovider.beer.repository;

import com.ipaixao.ibeer.infrastructure.dataprovider.beer.entity.Beer;
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
public interface JpaBeerRepository extends JpaRepository<Beer, Long> {

    @EntityGraph(attributePaths = {"brewery"})
    Optional<Beer> findById(long id);

    @NonNull
    @EntityGraph(attributePaths = {"brewery"})
    Page<Beer> findAll(@NonNull Pageable pageable);

    @Query("SELECT id from Beer WHERE name = :name")
    Optional<Long> getIdByName(@Param("name") String name);

    @Query("SELECT id from Beer WHERE name = :name AND id <> :id")
    Optional<Long> getIdByNameAndIdNot(@Param("name") String name, @Param("id") long id);
}
