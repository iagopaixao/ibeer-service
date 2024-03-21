package com.ipaixao.ibeer.application.usecase.beer.impl;

import com.ipaixao.ibeer.application.usecase.beer.DeleteBeerUseCase;
import com.ipaixao.ibeer.domain.beer.gateway.BeerDeleteDataSourceGateway;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteBeerByIdUseCaseImpl implements DeleteBeerUseCase {
    private final BeerDeleteDataSourceGateway gateway;
    @Override
    public void deleteById(long id) {
        if (!gateway.existsById(id)) {
            final var e = new EntityNotFoundException("Entity not found with the given ID: " + id);
            log.error("m=deleteById, status=error, message={}", e.getMessage(), e);
            throw e;
        }
        gateway.deleteById(id);
    }
}
