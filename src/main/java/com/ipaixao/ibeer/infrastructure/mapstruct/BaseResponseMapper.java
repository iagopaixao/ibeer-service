package com.ipaixao.ibeer.infrastructure.mapstruct;

public interface BaseResponseMapper<R, E> {

    R toResponse(E entity);
}
