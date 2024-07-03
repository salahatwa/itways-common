package com.api.keysecret;

import java.util.List;

public class ApiKeyAuthenticationInterceptorProperties {

	private final List<String> includePatterns;
	private final List<String> excludePatterns;

	public ApiKeyAuthenticationInterceptorProperties(final List<String> includePatterns,
			final List<String> excludePatterns) {
		this.includePatterns = includePatterns;
		this.excludePatterns = excludePatterns;
	}

	public List<String> getIncludePatterns() {
		return includePatterns;
	}

	public List<String> getExcludePatterns() {
		return excludePatterns;
	}
}
