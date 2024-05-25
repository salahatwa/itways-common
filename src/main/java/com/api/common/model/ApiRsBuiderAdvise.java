//package com.api.common.model;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//@ControllerAdvice
//public class ApiRsBuiderAdvise<T> implements ResponseBodyAdvice<Object> {
//
//	@Override
//	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//		return true;
//	}
//
//	@Override
//	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
//			Class<? extends HttpMessageConverter<?>> selectedConverterType,
//			org.springframework.http.server.ServerHttpRequest request,
//			org.springframework.http.server.ServerHttpResponse response) {
//
//		if (body instanceof ApiRs) {
//			ApiRs resbBody = (ApiRs) body;
//			return BaseResponse.ok(resbBody);
//		} else {
//			return body;
//		}
//	}
//
//}
