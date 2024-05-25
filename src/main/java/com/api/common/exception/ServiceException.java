package com.api.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception caused by service.
 *
 * @author ssatwa
 */
public class ServiceException extends AbstractBlinkedException {

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
