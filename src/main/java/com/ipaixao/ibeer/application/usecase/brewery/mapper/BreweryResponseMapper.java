package com.ipaixao.ibeer.application.usecase.brewery.mapper;

import com.ipaixao.ibeer.api.controller.brewery.BreweryResponse;
import com.ipaixao.ibeer.application.usecase.beer.mapper.BeerResponseMapper;
import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.infrastructure.base.BaseResponseMapper;
import com.ipaixao.ibeer.infrastructure.config.BaseMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class, uses = BeerResponseMapper.class)
public interface BreweryResponseMapper extends BaseResponseMapper<BreweryResponse, BreweryDomain> {}
