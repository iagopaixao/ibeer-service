package com.ipaixao.ibeer.application.usecase.brewery.mapper;

import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseResponseMapper;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseConfigMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class, uses = BeerResponseMapper.class)
public interface BreweryResponseMapper extends BaseResponseMapper<BreweryResponse, BreweryDomain> {}
