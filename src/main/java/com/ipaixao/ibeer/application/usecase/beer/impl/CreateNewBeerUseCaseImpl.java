package com.ipaixao.ibeer.application.usecase.beer.impl;

import com.ipaixao.ibeer.api.broker.CreatedBeerEvent;
import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.RegisterBeerUseCase;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerRequestMapper;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.application.validator.DuplicationValidator;
import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.gateway.BeerRegisterDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateNewBeerUseCaseImpl implements RegisterBeerUseCase {
    private final BeerRegisterDataSourceGateway gateway;
    private final BeerResponseMapper responseMapper;
    private final BeerRequestMapper requestMapper;
    private final ApplicationEventPublisher publisher;

    public BeerResponse create(BeerRequest request) {
        final var domain = requestMapper.toDomain(request);
        applyValidations(domain);

        final var createdBeer = gateway.create(domain);
        publisher.publishEvent(new CreatedBeerEvent(createdBeer, "CREATED"));

        return responseMapper.toResponse(createdBeer);
    }

    private void applyValidations(@NonNull BeerDomain domain) {
        final var isDuplicated = gateway.existsByName(domain.name());
        new DuplicationValidator().accept(isDuplicated);
    }
}
