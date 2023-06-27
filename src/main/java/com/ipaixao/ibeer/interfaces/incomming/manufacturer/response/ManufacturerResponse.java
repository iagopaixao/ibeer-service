package com.ipaixao.ibeer.interfaces.incomming.manufacturer.response;

import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;

import java.util.List;

public record ManufacturerResponse(long id,
                                   String name,
                                   String birthplace,
                                   List<BeerResponse> beers) {}
