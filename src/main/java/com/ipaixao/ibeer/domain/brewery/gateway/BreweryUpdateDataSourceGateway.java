package com.ipaixao.ibeer.domain.brewery.gateway;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;

public interface BreweryUpdateDataSourceGateway {
    BreweryDomain update(BreweryDomain domain);
    boolean existsByNameNotEqualId(String name, long id);
}
