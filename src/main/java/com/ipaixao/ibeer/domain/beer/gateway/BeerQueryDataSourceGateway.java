package com.ipaixao.ibeer.domain.beer.gateway;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BeerQueryDataSourceGateway {
    Page<BeerDomain> getAll(Pageable pageable);
    Optional<BeerDomain> getById(long id);
}
