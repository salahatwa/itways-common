package com.api.keysecret;

public interface ApiKeyVerificationHandler {
	void verify(String apiKey, String apiSecret) throws Exception;
}
