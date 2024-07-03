package com.api.keysecret;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiKeyAuthenticationInterceptor implements HandlerInterceptor {

	private final ApiKeyAuthenticationService authenticationService;

	public ApiKeyAuthenticationInterceptor(final ApiKeyAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {

		try {
			authenticationService.authenticate(request);
		} catch (final Exception e) {
			log.warn("Api key authentication failed: {} [host {}, URI {}]", e.getMessage(), request.getRemoteHost(),
					request.getRequestURI());
			response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
			return false;
		}
		return true;
	}
}