package com.api.keysecret;

import static org.springframework.util.Assert.hasLength;

public class StaticApiKeyVerificationHandler implements ApiKeyVerificationHandler {

	private final String apiKey;

	public StaticApiKeyVerificationHandler(final String apiKey) {
		hasLength(apiKey, "Static api key may not be empty");
		this.apiKey = apiKey;
	}

	@Override
	public void verify(final String apiKey, String apiSecret) throws Exception {
		if (this.apiKey.equals(apiKey)) {
			return;
		}

		throw new Exception("Api key could not be verified");
	}
}
