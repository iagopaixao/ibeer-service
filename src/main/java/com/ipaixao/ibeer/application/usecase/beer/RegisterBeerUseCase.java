package com.ipaixao.ibeer.application.usecase.beer;

import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.api.controller.beer.BeerResponse;

public interface RegisterBeerUseCase {
    BeerResponse create(BeerRequest request);
}
