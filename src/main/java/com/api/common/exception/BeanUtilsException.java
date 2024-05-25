package com.api.common.exception;

import org.springframework.http.HttpStatus;

/**
 * BeanUtils exception.
 *
 * @author ssatwa
 */
public class BeanUtilsException extends AbstractBlinkedException {

    public BeanUtilsException(String message) {
        super(message);
    }

    public BeanUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
