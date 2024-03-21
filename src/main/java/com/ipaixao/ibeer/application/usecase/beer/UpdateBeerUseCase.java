package com.ipaixao.ibeer.application.usecase.beer;

import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.api.controller.beer.BeerResponse;

public interface UpdateBeerUseCase {
    BeerResponse update(long id, BeerRequest request);
}
