package com.ipaixao.ibeer.application.usecase.beer.impl;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.QueryAllBeersUseCase;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.domain.beer.gateway.BeerQueryDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetrieveAllBeersUseCaseImpl implements QueryAllBeersUseCase {
    private final BeerQueryDataSourceGateway gateway;
    private final BeerResponseMapper mapper;

    @Override
    public Page<BeerResponse> getAll(Pageable pageable) {
        return gateway.getAll(pageable).map(mapper::toResponse);
    }
}
