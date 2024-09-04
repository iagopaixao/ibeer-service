package com.ipaixao.ibeer.application.usecase.beer.mapper;

import com.ipaixao.ibeer.api.controller.beer.BeerResponse;
import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseResponseMapper;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseConfigMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseConfigMapper.class)
public interface BeerResponseMapper extends BaseResponseMapper<BeerResponse, BeerDomain> {
    @Mapping(target = "brewery", source = "brewery.name")
    BeerResponse toResponse(BeerDomain domain);
}
