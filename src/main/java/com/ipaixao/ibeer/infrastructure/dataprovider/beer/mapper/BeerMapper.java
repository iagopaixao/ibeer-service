package com.ipaixao.ibeer.infrastructure.dataprovider.beer.mapper;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseEntityMapper;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseConfigMapper;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.entity.Beer;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface BeerMapper extends BaseEntityMapper<BeerDomain, Beer> {}
