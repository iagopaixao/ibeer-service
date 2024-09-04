package com.ipaixao.ibeer.infrastructure.mapstruct;

public interface BaseRequestMapper<R, D> {
    D toDomain(R request);
    R toResponse(D domain);
}
