package com.ipaixao.ibeer.domain.common;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

import static java.util.Objects.nonNull;

@Slf4j
public class DuplicationValidator implements Consumer<Object> {

    @Override
    public void accept(Object dto) {
        if (nonNull(dto)) {
            final var e = new NameDuplicatedException("Name Duplicated!");
            log.error("m=accept, status=error, message={}", e.getMessage(), e);
            throw e;
        }
    }
}
