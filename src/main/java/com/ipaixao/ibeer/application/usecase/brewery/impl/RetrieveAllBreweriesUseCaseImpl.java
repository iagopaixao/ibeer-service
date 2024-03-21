package com.ipaixao.ibeer.application.usecase.brewery.impl;

import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;
import com.ipaixao.ibeer.application.usecase.brewery.QueryAllBreweriesUseCase;
import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryResponseMapper;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryQueryDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetrieveAllBreweriesUseCaseImpl implements QueryAllBreweriesUseCase {
    private final BreweryQueryDataSourceGateway gateway;
    private final BreweryResponseMapper mapper;

    public Page<BreweryResponse> getAll(Pageable pageable) {
        return gateway.getAll(pageable).map(mapper::toResponse);
    }
}
