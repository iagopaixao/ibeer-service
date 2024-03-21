package com.ipaixao.ibeer.domain.beer.gateway;

public interface BeerDeleteDataSourceGateway {
    boolean existsById(long id);
    void deleteById(long id);
}
