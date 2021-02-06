package com.ipaixao.ibeer.infrastructure.mapstruct;

import java.util.List;

public interface BaseEntityMapper<D, E> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntity(List<D> dtos);

    List<D> toDTO(List<E> entities);
}
