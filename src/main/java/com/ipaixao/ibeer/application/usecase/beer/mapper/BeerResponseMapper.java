package com.ipaixao.ibeer.application.usecase.beer.mapper;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.infrastructure.base.BaseResponseMapper;
import com.ipaixao.ibeer.infrastructure.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class)
public interface BeerResponseMapper extends BaseResponseMapper<BeerResponse, BeerDomain> {
    @Mapping(target = "brewery", source = "brewery.name")
    BeerResponse toResponse(BeerDomain domain);
}
