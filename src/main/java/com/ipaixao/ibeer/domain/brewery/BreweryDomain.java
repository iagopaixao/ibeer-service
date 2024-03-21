package com.ipaixao.ibeer.domain.brewery;

import com.ipaixao.ibeer.domain.beer.BeerDomain;

import java.util.List;

public record BreweryDomain(Long id,
                            String name,
                            String birthplace,
                            List<BeerDomain> beers) {}
