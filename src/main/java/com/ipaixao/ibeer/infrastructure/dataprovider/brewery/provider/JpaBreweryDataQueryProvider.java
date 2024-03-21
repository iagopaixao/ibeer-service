package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.provider;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryQueryDataSourceGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.mapper.CrudBreweryMapper;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.repository.JpaBreweryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = PROTECTED)
public class JpaBreweryDataQueryProvider implements BreweryQueryDataSourceGateway {
    private final JpaBreweryRepository repository;
    private final CrudBreweryMapper mapper;

    public Page<BreweryDomain> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }

    public Optional<BreweryDomain> getById(long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
