package com.ipaixao.ibeer.application.usecase.beer.impl;

import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.application.usecase.beer.UpdateBeerUseCase;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerRequestMapper;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.application.validator.DuplicationValidator;
import com.ipaixao.ibeer.domain.beer.gateway.BeerExistsVerificatorGateway;
import com.ipaixao.ibeer.domain.beer.gateway.BeerUpdateDataSourceGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateBeerByIdUseCaseImpl implements UpdateBeerUseCase {
    private final BeerExistsVerificatorGateway existsGateway;
    private final BeerUpdateDataSourceGateway gateway;
    private final BeerResponseMapper responseMapper;
    private final BeerRequestMapper requestMapper;

    public BeerResponse update(long id, BeerRequest request) {
        validateDuplication(request.name(), id);

        final var domain = requestMapper.toDomain(id, request);
        final var updatedBeer = gateway.update(domain);

        return responseMapper.toResponse(updatedBeer);
    }

    private void validateDuplication(String brewaryName, long id) {
        final var isDuplicated = existsGateway.existsByNameWithDifferentId(brewaryName, id);
        new DuplicationValidator().accept(isDuplicated);
    }
}
