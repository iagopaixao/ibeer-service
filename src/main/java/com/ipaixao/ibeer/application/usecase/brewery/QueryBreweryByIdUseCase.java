package com.ipaixao.ibeer.application.usecase.brewery;

import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;

public interface QueryBreweryByIdUseCase {
    BreweryResponse getById(long id);
}
