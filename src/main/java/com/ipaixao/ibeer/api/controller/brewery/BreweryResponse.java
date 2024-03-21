package com.ipaixao.ibeer.api.controller.brewery;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;

import java.util.List;

public record BreweryResponse(long id,
                                   String name,
                                   String birthplace,
                                   List<BeerResponse> beers) {}
