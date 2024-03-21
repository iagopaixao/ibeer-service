package com.ipaixao.ibeer.infrastructure.dataprovider.beer.provider;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.gateway.BeerQueryDataSourceGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.mapper.CrudBeerMapper;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.repository.JpaBeerRepository;
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
public class JpaBeerDataQueryProvider implements BeerQueryDataSourceGateway {
    private final JpaBeerRepository repository;
    private final CrudBeerMapper mapper;

    public Page<BeerDomain> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }

    public Optional<BeerDomain> getById(long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
