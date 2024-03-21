package com.ipaixao.ibeer.domain.brewery.gateway;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;

public interface BreweryRegisterDataSourceGateway {
    BreweryDomain create(BreweryDomain domain);
    boolean existsByName(String name);
}
