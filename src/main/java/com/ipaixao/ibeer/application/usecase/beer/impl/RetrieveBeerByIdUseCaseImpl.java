package com.ipaixao.ibeer.application.usecase.beer.impl;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.QueryBeerByIdUseCase;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.domain.beer.gateway.BeerQueryDataSourceGateway;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetrieveBeerByIdUseCaseImpl implements QueryBeerByIdUseCase {
    private final BeerQueryDataSourceGateway gateway;
    private final BeerResponseMapper mapper;

    public BeerResponse getById(long id) {
        return gateway.getById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> {
                    final var e = new EntityNotFoundException("Entity not found for ID: " + id);
                    log.error("m=getById, status=error, message={}", e.getMessage(), e);
                    return e;
                });
    }
}
