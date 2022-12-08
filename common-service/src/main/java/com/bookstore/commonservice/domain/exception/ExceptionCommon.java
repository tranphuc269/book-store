package com.bookstore.commonservice.domain.exception;

import lombok.Getter;

import java.io.Serializable;


@Getter
public class ExceptionCommon  extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private final BusinessError errorCode;

    public ExceptionCommon(BusinessError errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public ExceptionCommon(BusinessError errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ExceptionCommon(BusinessError error, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = error;
    }
}
