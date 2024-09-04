package com.ipaixao.ibeer.infrastructure.mapstruct;

public interface BaseResponseMapper<R, D> {
    R toResponse(D domain);
}
