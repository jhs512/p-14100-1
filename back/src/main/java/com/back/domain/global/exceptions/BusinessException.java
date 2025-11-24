package com.back.domain.global.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String resultCode, String msg) {
        super(resultCode + ": " + msg);
    }
}
