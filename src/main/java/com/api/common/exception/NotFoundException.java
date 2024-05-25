package com.api.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception of entity not found.
 *
 * @author ssatwa
 */
public class NotFoundException extends AbstractBlinkedException {

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
}
