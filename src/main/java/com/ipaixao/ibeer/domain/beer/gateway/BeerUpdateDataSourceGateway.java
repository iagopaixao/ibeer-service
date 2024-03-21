package com.ipaixao.ibeer.domain.beer.gateway;

import com.ipaixao.ibeer.domain.beer.BeerDomain;

public interface BeerUpdateDataSourceGateway {
    boolean existsByNameNotEqualId(String name, long id);
    BeerDomain update(BeerDomain beer);
}
