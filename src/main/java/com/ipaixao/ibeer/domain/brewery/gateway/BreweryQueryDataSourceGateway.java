package com.ipaixao.ibeer.domain.brewery.gateway;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BreweryQueryDataSourceGateway {
    Page<BreweryDomain> getAll(Pageable pageable);
    Optional<BreweryDomain> getById(long id);
}
