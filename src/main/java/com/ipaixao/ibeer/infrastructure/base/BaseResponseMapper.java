package com.ipaixao.ibeer.infrastructure.base;

public interface BaseResponseMapper<R, D> {
    R toResponse(D domain);
}
