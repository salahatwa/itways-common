package com.api.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception caused by entity existence already.
 *
 * @author ssatwa
 */
public class AlreadyExistsException extends AbstractBlinkedException {

	public AlreadyExistsException(String message) {
		super(message);
	}

	public AlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
