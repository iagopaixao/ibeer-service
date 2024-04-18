package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.provider;

import com.ipaixao.ibeer.domain.brewery.gateway.BreweryExistsVerificatorGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.repository.JpaBreweryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = PROTECTED)
public class JpaBreweryExistsVerificatorProvider implements BreweryExistsVerificatorGateway {
    private final JpaBreweryRepository repository;

    @Override
    public boolean existsById(long id) {
        return repository.findById(id).isPresent();
    }

    @Override
    public boolean existsByName(String name) {
        return repository.getIdByName(name).isPresent();
    }

    @Override
    public boolean existsByNameWithDifferentId(String name, long id) {
        return repository.getIdByNameAndNotEqualId(name, id).isPresent();
    }
}
