package com.ipaixao.ibeer.domain.beer.gateway;

import com.ipaixao.ibeer.domain.beer.BeerDomain;

public interface BeerRegisterDataSourceGateway {
    boolean existsByName(String name);
    BeerDomain create(BeerDomain beer);
}
