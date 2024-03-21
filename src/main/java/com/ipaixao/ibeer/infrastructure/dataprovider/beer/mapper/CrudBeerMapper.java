package com.ipaixao.ibeer.infrastructure.dataprovider.beer.mapper;

import com.ipaixao.ibeer.domain.beer.BeerDomain;
import com.ipaixao.ibeer.infrastructure.base.BaseEntityMapper;
import com.ipaixao.ibeer.infrastructure.config.BaseMapperConfig;
import com.ipaixao.ibeer.infrastructure.dataprovider.beer.entity.Beer;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface CrudBeerMapper extends BaseEntityMapper<BeerDomain, Beer> {
}
