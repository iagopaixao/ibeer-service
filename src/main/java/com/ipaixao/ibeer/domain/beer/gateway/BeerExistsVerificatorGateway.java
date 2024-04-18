package com.ipaixao.ibeer.domain.beer.gateway;

public interface BeerExistsVerificatorGateway {
    boolean existsById(long id);
    boolean existsByName(String name);
    boolean existsByNameWithDifferentId(String name, long id);
}
