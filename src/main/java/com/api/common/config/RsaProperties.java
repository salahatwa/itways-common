package com.api.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "api.rsa")
@Component
public class RsaProperties {
	private String publicKey;
	private String privateKey;
}
