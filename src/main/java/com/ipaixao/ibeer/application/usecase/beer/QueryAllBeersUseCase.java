package com.ipaixao.ibeer.application.usecase.beer;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryAllBeersUseCase {
    Page<BeerResponse> getAll(Pageable pageable);
}
