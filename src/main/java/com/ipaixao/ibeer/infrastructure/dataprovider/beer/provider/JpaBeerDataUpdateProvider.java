package com.ipaixao.ibeer.infrastructure.dataprovider.beer.provider;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.domain.beer.BeerEvent;
import com.ipaixao.ibeer.domain.beer.gateway.BeerUpdateDataSourceGateway;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.mapper.BeerMapper;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.repository.JpaBeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ipaixao.ibeer.domain.beer.BeerEvent.BeerStatus.UPDATED;
import static lombok.AccessLevel.PROTECTED;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(access = PROTECTED)
public class JpaBeerDataUpdateProvider implements BeerUpdateDataSourceGateway {
    private final ApplicationEventPublisher publisher;
    private final JpaBeerRepository repository;
    private final BeerMapper mapper;

    public boolean existsByNameNotEqualId(String name, long id) {
        return repository.getIdByNameAndNotEqualId(name, id).isPresent();
    }

    public BeerDomain update(BeerDomain domain) {
        final var entity = mapper.toEntity(domain);
        final var updatedBeer = repository.save(entity);

        final var beer = mapper.toDomain(updatedBeer);
        publisher.publishEvent(new BeerEvent(beer, UPDATED));

        return beer;
    }
}
