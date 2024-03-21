package com.ipaixao.ibeer.application.usecase.brewery.impl;

import com.ipaixao.ibeer.api.controller.brewery.BreweryRequest;
import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;
import com.ipaixao.ibeer.application.usecase.brewery.UpdateBreweryUseCase;
import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryRequestMapper;
import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryResponseMapper;
import com.ipaixao.ibeer.application.validator.DuplicationValidator;
import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryUpdateDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateBreweryByIdUseCaseImpl implements UpdateBreweryUseCase {
    private final BreweryUpdateDataSourceGateway gateway;
    private final BreweryResponseMapper responseMapper;
    private final BreweryRequestMapper requestMapper;

    @Override
    public BreweryResponse update(long id, BreweryRequest request) {
        final var domain = requestMapper.toDomain(id, request);
        applyValidations(domain);

        final var updatedBeer = gateway.update(domain);

        return responseMapper.toResponse(updatedBeer);
    }

    private void applyValidations(@NonNull BreweryDomain domain) {
        final var isDuplicated = gateway.existsByNameNotEqualId(domain.name(), domain.id());
        new DuplicationValidator().accept(isDuplicated);
    }
}
