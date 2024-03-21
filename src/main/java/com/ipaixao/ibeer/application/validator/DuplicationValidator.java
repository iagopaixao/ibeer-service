package com.ipaixao.ibeer.application.validator;

import com.ipaixao.ibeer.domain.exception.NameDuplicatedException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

import static java.lang.Boolean.TRUE;

@Slf4j
public class DuplicationValidator implements Consumer<Boolean> {

    public void accept(Boolean existed) {
        if (TRUE.equals(existed)) {
            final var e = new NameDuplicatedException("Name Duplicated!");
            log.error("m=accept, status=error, message={}", e.getMessage(), e);
            throw e;
        }
    }
}
