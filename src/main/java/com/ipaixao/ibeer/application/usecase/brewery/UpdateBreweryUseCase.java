package com.ipaixao.ibeer.application.usecase.brewery;

import com.ipaixao.ibeer.api.controller.brewery.BreweryRequest;
import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;

public interface UpdateBreweryUseCase {
    BreweryResponse update(long id, BreweryRequest request);
}
