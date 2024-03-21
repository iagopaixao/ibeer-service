package com.ipaixao.ibeer.application.usecase.brewery.impl;

import com.ipaixao.ibeer.api.controller.brewery.BreweryRequest;
import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;
import com.ipaixao.ibeer.application.usecase.brewery.RegisterBreweryUseCase;
import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryRequestMapper;
import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryResponseMapper;
import com.ipaixao.ibeer.application.validator.DuplicationValidator;
import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryRegisterDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateNewBreweryUseCaseImpl implements RegisterBreweryUseCase {
    private final BreweryRegisterDataSourceGateway gateway;
    private final BreweryResponseMapper responseMapper;
    private final BreweryRequestMapper requestMapper;

    public BreweryResponse create(BreweryRequest request) {
        final var domain = requestMapper.toDomain(request);
        applyValidations(domain);

        final var createdBrewery = gateway.create(domain);

        return responseMapper.toResponse(createdBrewery);
    }

    private void applyValidations(@NonNull BreweryDomain domain) {
        final var isDuplicated = gateway.existsByName(domain.name());
        new DuplicationValidator().accept(isDuplicated);
    }
}
