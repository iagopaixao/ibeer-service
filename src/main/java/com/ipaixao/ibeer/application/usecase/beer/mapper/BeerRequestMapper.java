package com.ipaixao.ibeer.application.usecase.beer.mapper;

import com.ipaixao.ibeer.api.controller.beer.BeerRequest;
import com.ipaixao.ibeer.application.usecase.brewery.mapper.BreweryRequestMapper;
import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.infrastructure.base.BaseRequestMapper;
import com.ipaixao.ibeer.infrastructure.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, uses = BreweryRequestMapper.class)
public interface BeerRequestMapper extends BaseRequestMapper<BeerRequest, BeerDomain> {
    @Mapping(target = "id", source = "id")
    BeerDomain toDomain(Long id, BeerRequest request);
}
