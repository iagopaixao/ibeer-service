package com.ipaixao.ibeer.domain.beer.gateway;

import com.ipaixao.ibeer.domain.beer.BeerDomain;

public interface CreatedBeerKafkaDispatcherGateway {
    void dispatch(BeerDomain beer);
}
