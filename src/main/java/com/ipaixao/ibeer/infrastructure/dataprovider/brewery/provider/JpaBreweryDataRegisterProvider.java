package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.provider;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryRegisterDataSourceGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.mapper.CrudBreweryMapper;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.repository.JpaBreweryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@RequiredArgsConstructor(access = PROTECTED)
@Transactional
public class JpaBreweryDataRegisterProvider implements BreweryRegisterDataSourceGateway {
    private final JpaBreweryRepository repository;
    private final CrudBreweryMapper mapper;

    public BreweryDomain create(BreweryDomain domain) {
        return mapper.toDomain(repository.save(mapper.toEntity(domain)));
    }

    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.findIdByName(name).isPresent();
    }
}
