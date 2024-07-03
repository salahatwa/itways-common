package com.api.keysecret;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyAutoconfiguration {

	@Bean
	@ConditionalOnMissingBean
	public ApiKeyAuthenticationService apiKeyAuthenticationService(final RequestApiKeyExtractor requestApiKeyExtractor,
			final ApiKeyVerificationHandler apiKeyVerificationHandler) {
		return new ApiKeyAuthenticationService(requestApiKeyExtractor, apiKeyVerificationHandler);
	}

	@Bean
	@ConditionalOnMissingBean
	public RequestApiKeyExtractor defaultApiKeyProvider() {
		return new RequestApiKeyExtractor() {
		};
	}


	@Bean
	@ConditionalOnMissingBean
	public ApiKeyAuthenticationInterceptorProperties apiKeyAuthenticationInterceptorProperties(
			@Qualifier("apiKeyAuthenticationIncludePatterns") final List<String> includePatterns,
			@Qualifier("apiKeyAuthenticationExcludePatterns") final List<String> excludePatterns) {
		return new ApiKeyAuthenticationInterceptorProperties(includePatterns, excludePatterns);
	}

//	@Bean
//	@ConditionalOnMissingBean(name = "apiKeyAuthenticationIncludePatterns")
//	public List<String> apiKeyAuthenticationIncludePatterns() {
//		return Collections.singletonList("/**");
//	}

//	@Bean
//	@ConditionalOnMissingBean(name = "apiKeyAuthenticationExcludePatterns")
//	public List<String> apiKeyAuthenticationExcludePatterns() {
//		return Collections.singletonList("/error");
//	}
}