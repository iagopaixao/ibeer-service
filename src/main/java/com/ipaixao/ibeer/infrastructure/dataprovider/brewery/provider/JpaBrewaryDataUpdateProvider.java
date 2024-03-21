package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.provider;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.domain.brewery.gateway.BreweryUpdateDataSourceGateway;
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
public class JpaBrewaryDataUpdateProvider implements BreweryUpdateDataSourceGateway {
    private final JpaBreweryRepository repository;
    private final CrudBreweryMapper mapper;

    public BreweryDomain update(BreweryDomain domain) {
        final var entity = mapper.toEntity(domain);
        final var updatedBrewery = repository.save(entity);

        return mapper.toDomain(updatedBrewery);
    }

    public boolean existsByNameNotEqualId(String name, long id) {
        return repository.findIdByNameAndIdNot(name, id).isPresent();
    }
}
