package com.api.keysecret;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ApiKeyAuthenticationService {

	private final RequestApiKeyExtractor requestApiKeyExtractor;
	private final ApiKeyVerificationHandler apiKeyVerificationHandler;

	public ApiKeyAuthenticationService(final RequestApiKeyExtractor requestApiKeyExtractor,
			final ApiKeyVerificationHandler apiKeyVerificationHandler) {
		this.requestApiKeyExtractor = requestApiKeyExtractor;
		this.apiKeyVerificationHandler = apiKeyVerificationHandler;
	}

	public void authenticate(final HttpServletRequest request) throws Exception {
		final String apiKey = requestApiKeyExtractor.getApiKey(request)
				.orElseThrow(() -> new Exception("Api key not found"));

		final String apiSecret = requestApiKeyExtractor.getApiSecret(request)
				.orElseThrow(() -> new Exception("Api secret not found"));

		try {
			apiKeyVerificationHandler.verify(apiKey, apiSecret);
		} catch (final Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}
}
