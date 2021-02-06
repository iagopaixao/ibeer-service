package com.ipaixao.ibeer.interfaces.incomming.beer.mapper;

import com.ipaixao.ibeer.domain.beer.Beer;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseEntityMapper;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseMapperConfig;
import com.ipaixao.ibeer.infrastructure.mapstruct.BaseResponseMapper;
import com.ipaixao.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import com.ipaixao.ibeer.interfaces.incomming.beer.response.BeerResponse;
import com.ipaixao.ibeer.interfaces.incomming.manufacturer.mapper.ManufacturerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, uses = ManufacturerMapper.class)
public interface BeerMapper extends BaseEntityMapper<BeerDTO, Beer>, BaseResponseMapper<BeerResponse, Beer> {

    @Mapping(target = "manufacturer", source = "manufacturer.name")
    BeerResponse toResponse(Beer entity);
}
