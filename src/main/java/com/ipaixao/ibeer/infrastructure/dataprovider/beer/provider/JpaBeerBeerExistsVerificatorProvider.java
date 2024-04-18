package com.ipaixao.ibeer.infrastructure.dataprovider.beer.provider;

import com.ipaixao.ibeer.domain.beer.gateway.BeerExistsVerificatorGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.repository.JpaBeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = PROTECTED)
public class JpaBeerBeerExistsVerificatorProvider implements BeerExistsVerificatorGateway {
    private final JpaBeerRepository repository;

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
