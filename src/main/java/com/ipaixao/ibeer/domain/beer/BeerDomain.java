package com.ipaixao.ibeer.domain.beer;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;

import java.math.BigDecimal;

public record BeerDomain(Long id,
                         String name,
                         Integer ibu,
                         BigDecimal abv,
                         String style,
                         BigDecimal price,
                         Integer milliliter,
                         BreweryDomain brewery) {}
