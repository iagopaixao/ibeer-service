package com.ipaixao.ibeer.application.usecase.brewery.impl;

import com.ipaixao.ibeer.application.usecase.brewery.DeleteBreweryUseCase;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryDeleteDataSourceGateway;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteBreweryByIdUseCaseImpl implements DeleteBreweryUseCase {
    private final BreweryDeleteDataSourceGateway gateway;
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
