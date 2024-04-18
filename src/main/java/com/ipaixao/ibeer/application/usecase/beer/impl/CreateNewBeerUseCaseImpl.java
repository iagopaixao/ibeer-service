package com.ipaixao.ibeer.application.usecase.beer.impl;

import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.RegisterBeerUseCase;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerRequestMapper;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.application.validator.DuplicationValidator;
import com.ipaixao.ibeer.domain.beer.gateway.BeerExistsVerificatorGateway;
import com.ipaixao.ibeer.domain.beer.CreatedBeerEvent;
import com.ipaixao.ibeer.domain.beer.gateway.BeerRegisterDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class CreateNewBeerUseCaseImpl implements RegisterBeerUseCase {
    private final BeerRequestMapper requestMapper;
    private final BeerResponseMapper responseMapper;
    private final ApplicationEventPublisher publisher;
    private final BeerExistsVerificatorGateway existsGateway;
    private final BeerRegisterDataSourceGateway registerGateway;

    public BeerResponse create(BeerRequest request) {
        validateDuplication(request.name());

        final var domain = requestMapper.toDomain(request);
        final var createdBeer = registerGateway.create(domain);

        publisher.publishEvent(new CreatedBeerEvent(createdBeer));

        return responseMapper.toResponse(createdBeer);
    }

    private void validateDuplication(String beerName) {
        final var isDuplicated = existsGateway.existsByName(beerName);
        new DuplicationValidator().accept(isDuplicated);
    }
}
