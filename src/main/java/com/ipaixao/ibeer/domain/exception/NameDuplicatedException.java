package com.ipaixao.ibeer.domain.exception;

public class NameDuplicatedException extends RuntimeException {

    public NameDuplicatedException(String message) {
        super(message);
    }
}
