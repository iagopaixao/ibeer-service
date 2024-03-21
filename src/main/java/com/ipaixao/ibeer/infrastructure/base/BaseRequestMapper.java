package com.ipaixao.ibeer.infrastructure.base;

public interface BaseRequestMapper<R, D> {
    D toDomain(R request);
    R toResponse(D domain);
}
