package com.ipaixao.ibeer.infrastructure.dataprovider.beer.provider;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.gateway.BeerRegisterDataSourceGateway;
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
public class JpaBeerDataRegisterProvider implements BeerRegisterDataSourceGateway {
    private final JpaBeerRepository repository;
    private final CrudBeerMapper mapper;

    public boolean existsByName(String name) {
        return repository.getIdByName(name).isPresent();
    }

    public BeerDomain create(BeerDomain domain) {
        final var entity = mapper.toEntity(domain);
        final var createdBeer = repository.save(entity);

        return mapper.toDomain(createdBeer);
    }
}
