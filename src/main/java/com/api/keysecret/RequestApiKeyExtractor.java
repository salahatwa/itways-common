package com.api.keysecret;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestApiKeyExtractor {

	default Optional<String> getApiKey(final HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader("X-Api-Key"));
	}
	
	default Optional<String> getApiSecret(final HttpServletRequest request) {
		return Optional.ofNullable(request.getHeader("X-Api-Secret"));
	}
}
