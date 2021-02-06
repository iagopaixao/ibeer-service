package com.ipaixao.ibeer.domain.beer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    @EntityGraph(value = "Beer", attributePaths = {"id"})
    Optional<Beer> findByName(String name);

    @EntityGraph(value = "Beer", attributePaths = {"id"})
    Optional<Beer> findByNameAndIdNot(String name, long id);
}
