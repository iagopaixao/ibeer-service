package com.ipaixao.ibeer.application.usecase.brewery.impl;

import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;
import com.ipaixao.ibeer.application.usecase.brewery.QueryBreweryByIdUseCase;
import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryResponseMapper;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryQueryDataSourceGateway;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetrieveBreweryByIdUseCaseImpl implements QueryBreweryByIdUseCase {
    private final BreweryQueryDataSourceGateway gateway;
    private final BreweryResponseMapper mapper;

    @Override
    public BreweryResponse getById(long id) {
        return gateway.getById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> {
                    final var e = new EntityNotFoundException("Entity not found!");
                    log.error("m=getById, status=error, message={}", e.getMessage(), e);
                    return e;
                });
    }
}
