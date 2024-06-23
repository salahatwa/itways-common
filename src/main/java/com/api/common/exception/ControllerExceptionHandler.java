package com.api.common.exception;

import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.api.common.model.BaseResponse;
import com.api.common.utils.DateUtils;
import com.api.common.utils.ValidationUtils;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Exception handler of controller.
 *
 * @author ssatwa
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		BaseResponse<?> baseResponse = handleBaseException(e);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		baseResponse.setStatus(status.value());
//		if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
//			baseResponse = handleBaseException(e.getCause());
//		}
		baseResponse.setMessage("Field validation error, please complete and try again！");
		return baseResponse;
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		BaseResponse<?> baseResponse = handleBaseException(e);
		baseResponse.setMessage(String.format("Request field is missing, type is %s, name is %s", e.getParameterType(),
				e.getParameterName()));
		return baseResponse;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<?> handleConstraintViolationException(ConstraintViolationException e) {
		BaseResponse<Map<String, String>> baseResponse = handleBaseException(e);
		baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		baseResponse.setMessage("Field validation error, please complete and try again！");
		baseResponse.setData(ValidationUtils.mapWithValidError(e.getConstraintViolations()));
		return baseResponse;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BaseResponse<Map<String, String>> baseResponse = handleBaseException(e);
		baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		baseResponse.setMessage("Field validation error, please complete and try again！");
		Map<String, String> errMap = ValidationUtils.mapWithFieldError(e.getBindingResult().getFieldErrors());
		baseResponse.setData(errMap);
		return baseResponse;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		BaseResponse<?> baseResponse = handleBaseException(e);
		baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return baseResponse;
	}

	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public BaseResponse<?> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
		BaseResponse<?> baseResponse = handleBaseException(e);
		baseResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		return baseResponse;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		BaseResponse<?> baseResponse = handleBaseException(e);
		baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		baseResponse.setMessage("Missing request body");
		return baseResponse;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public BaseResponse<?> handleNoHandlerFoundException(NoHandlerFoundException e) {
		BaseResponse<?> baseResponse = handleBaseException(e);
		HttpStatus status = HttpStatus.BAD_GATEWAY;
		baseResponse.setStatus(status.value());
		baseResponse.setMessage(status.getReasonPhrase());
		return baseResponse;
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public BaseResponse<?> handleUploadSizeExceededException(MaxUploadSizeExceededException e) {
		BaseResponse<Object> response = handleBaseException(e);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage("The current request exceeds the maximum limit：" + e.getMaxUploadSize() + " bytes");
		return response;
	}

	@ExceptionHandler(AbstractItWaysException.class)
	public ResponseEntity<BaseResponse<?>> handleHaloException(AbstractItWaysException e) {
		System.out.println(">>>>>>>>>>>>>>>>::::" + e.getMessage());
		BaseResponse<Object> baseResponse = handleBaseException(e);
		baseResponse.setStatus(e.getStatus().value());
		baseResponse.setData(e.getErrorData());
		return new ResponseEntity<>(baseResponse, e.getStatus());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public BaseResponse<?> handleGlobalException(Exception e) {
		BaseResponse<?> baseResponse = handleBaseException(e);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		baseResponse.setStatus(status.value());
		baseResponse.setMessage(e.getMessage());
		return baseResponse;
	}

	private <T> BaseResponse<T> handleBaseException(Throwable t) {
		Assert.notNull(t, "Throwable must not be null");

		BaseResponse<T> baseResponse = new BaseResponse<>();
		baseResponse.setMessage(t.getMessage());
		baseResponse.setRequestTime(DateUtils.now());
		log.error("Captured an exception:", t);

		if (log.isDebugEnabled()) {
			baseResponse.setDevMessage(ExceptionUtils.getStackTrace(t));
		}

		return baseResponse;
	}

}
