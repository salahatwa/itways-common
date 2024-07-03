package com.api.keysecret;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiKeyWebInterceptorConfig implements WebMvcConfigurer {

    private final ApiKeyAuthenticationService authenticationService;
    private final ApiKeyAuthenticationInterceptorProperties interceptorProperties;

    public ApiKeyWebInterceptorConfig(final ApiKeyAuthenticationService authenticationService,
                                                        final ApiKeyAuthenticationInterceptorProperties interceptorProperties) {
        this.authenticationService = authenticationService;
        this.interceptorProperties = interceptorProperties;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new ApiKeyAuthenticationInterceptor(authenticationService))
                .addPathPatterns(interceptorProperties.getIncludePatterns())
                .excludePathPatterns(interceptorProperties.getExcludePatterns());
    }
}