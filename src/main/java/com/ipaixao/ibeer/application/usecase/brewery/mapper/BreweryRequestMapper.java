package com.ipaixao.ibeer.application.usecase.brewery.mapper;

import com.ipaixao.ibeer.api.controller.brewery.BreweryRequest;
import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.infrastructure.base.BaseRequestMapper;
import com.ipaixao.ibeer.infrastructure.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class)
public interface BreweryRequestMapper extends BaseRequestMapper<BreweryRequest, BreweryDomain> {
    @Mapping(target = "id", source = "id")
    BreweryDomain toDomain(Long id, BreweryRequest request);
}
