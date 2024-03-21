package com.ipaixao.ibeer.application.usecase.beer;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;

public interface QueryBeerByIdUseCase {
    BeerResponse getById(long id);
}
