package com.ipaixao.ibeer.application.usecase.beer.impl;

import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.UpdateBeerUseCase;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerRequestMapper;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.application.validator.DuplicationValidator;
import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.gateway.BeerUpdateDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateBeerByIdUseCaseImpl implements UpdateBeerUseCase {
    private final BeerUpdateDataSourceGateway gateway;
    private final BeerResponseMapper responseMapper;
    private final BeerRequestMapper requestMapper;

    public BeerResponse update(long id, BeerRequest request) {
        final var domain = requestMapper.toDomain(id, request);
        applyValidations(domain);

        final var updatedBeer = gateway.update(domain);

        return responseMapper.toResponse(updatedBeer);
    }

    private void applyValidations(@NonNull BeerDomain domain) {
        final var isDuplicated = gateway.existsByNameNotEqualId(domain.name(), domain.id());
        new DuplicationValidator().accept(isDuplicated);
    }
}
