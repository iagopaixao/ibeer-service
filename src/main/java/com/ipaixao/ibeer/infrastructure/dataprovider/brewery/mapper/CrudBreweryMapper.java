package com.ipaixao.ibeer.infrastructure.dataprovider.brewery.mapper;

import com.ipaixao.ibeer.domain.brewery.BreweryDomain;
import com.ipaixao.ibeer.infrastructure.base.BaseEntityMapper;
import com.ipaixao.ibeer.infrastructure.config.BaseMapperConfig;
import com.ipaixao.ibeer.infrastructure.dataprovider.brewery.entity.Brewery;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface CrudBreweryMapper extends BaseEntityMapper<BreweryDomain, Brewery> {}
