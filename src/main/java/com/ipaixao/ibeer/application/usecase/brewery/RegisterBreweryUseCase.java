package com.ipaixao.ibeer.application.usecase.brewery;

import com.ipaixao.ibeer.api.controller.brewery.BreweryRequest;
import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;

public interface RegisterBreweryUseCase {
    BreweryResponse create(BreweryRequest request);
}
