package com.ipaixao.ibeer.infrastructure.base;

import java.util.List;

public interface BaseEntityMapper<D, E> {
    D toDomain(E entity);
    E toEntity(D domain);
    List<D> toDomain(List<E> entities);
    List<E> toEntity(List<D> domains);
}
