package com.ipaixao.ibeer.infrastructure.dataprovider.beer.provider;

import com.ipaixao.ibeer.domain.beer.gateway.BeerDeleteDataSourceGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.repository.JpaBeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(access = PROTECTED)
public class JpaBeerDataDeleteProvider implements BeerDeleteDataSourceGateway {
    private final JpaBeerRepository repository;

    @Transactional(readOnly = true)
    public boolean existsById(long id) {
        return repository.existsById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
