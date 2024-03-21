package com.ipaixao.ibeer.infrastructure.dataprovider.beer.provider;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.gateway.BeerUpdateDataSourceGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.mapper.CrudBeerMapper;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.repository.JpaBeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@RequiredArgsConstructor(access = PROTECTED)
@Transactional
public class JpaBeerDataUpdateProvider implements BeerUpdateDataSourceGateway {
    private final JpaBeerRepository repository;
    private final CrudBeerMapper mapper;

    public boolean existsByNameNotEqualId(String name, long id) {
        return repository.getIdByNameAndIdNot(name, id).isPresent();
    }

    public BeerDomain update(BeerDomain domain) {
        final var entity = mapper.toEntity(domain);
        final var updatedBeer = repository.save(entity);

        return mapper.toDomain(updatedBeer);
    }
}
