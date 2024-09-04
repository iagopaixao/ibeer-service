package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.mapper;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseEntityMapper;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseConfigMapper;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.entity.Brewery;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface BreweryMapper extends BaseEntityMapper<BreweryDomain, Brewery> {}
