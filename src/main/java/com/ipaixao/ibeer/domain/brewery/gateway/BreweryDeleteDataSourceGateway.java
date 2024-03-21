package com.ipaixao.ibeer.domain.brewery.gateway;

public interface BreweryDeleteDataSourceGateway {
    boolean existsById(long id);
    void deleteById(long id);
}
