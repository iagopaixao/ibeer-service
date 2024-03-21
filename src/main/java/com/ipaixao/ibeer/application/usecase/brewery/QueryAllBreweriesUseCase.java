package com.ipaixao.ibeer.application.usecase.brewery;

import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryAllBreweriesUseCase {
    Page<BreweryResponse> getAll(Pageable pageable);
}
