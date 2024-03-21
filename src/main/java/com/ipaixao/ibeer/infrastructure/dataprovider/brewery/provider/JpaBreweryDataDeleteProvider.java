package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.provider;

import com.ipaixao.ibeer.domain.brewery.gateway.BreweryDeleteDataSourceGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.repository.JpaBreweryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(access = PROTECTED)
public class JpaBreweryDataDeleteProvider implements BreweryDeleteDataSourceGateway {
    private final JpaBreweryRepository repository;

    @Transactional(readOnly = true)
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
