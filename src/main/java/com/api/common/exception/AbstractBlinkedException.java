package com.api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Base exception of the project.
 *
 * @author ssatwa
 * @date 2019-03-15
 */
public abstract class AbstractBlinkedException extends RuntimeException {

    /**
     * Error errorData.
     */
    private Object errorData;

    public AbstractBlinkedException(String message) {
        super(message);
    }

    public AbstractBlinkedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public AbstractBlinkedException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
