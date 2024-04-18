package com.ipaixao.ibeer.domain.brewery.gateway;

public interface BreweryExistsVerificatorGateway {
    boolean existsById(long id);
    boolean existsByName(String name);
    boolean existsByNameWithDifferentId(String name, long id);
}
