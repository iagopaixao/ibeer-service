package com.ipaixao.ibeer.domain.beer.gateway;

import com.ipaixao.ibeer.domain.beer.BeerDomain;

public interface BeerUpdateDataSourceGateway {
    BeerDomain update(BeerDomain beer);
}
