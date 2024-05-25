package com.api.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.Data;
import lombok.NonNull;

/**
 * Halo configuration properties.
 *
 * @author ssatwa
 * @date 2019-03-15
 */
@Data
@Component
@ConfigurationProperties("api")
public class ApiProperties {

	/**
	 * Doc api disabled. (Default is true)
	 */
	private boolean docDisabled = true;

	/**
	 * Production env. (Default is true)
	 */
	private boolean productionEnv = true;

	/**
	 * Authentication enabled
	 */
	private boolean authEnabled = true;

	/**
	 * Admin path.
	 */
	private String adminPath = "admin";

	/**
	 * Work directory.
	 */
	private String workDir = ensureSuffix(System.getProperties().getProperty("user.home"), File.separator) + ".halo"
			+ File.separator;

	private String imageHost = "";

	@NonNull
	public static String ensureSuffix(@NonNull String string, @NonNull String suffix) {
		Assert.hasText(string, "String must not be blank");
		Assert.hasText(suffix, "Suffix must not be blank");

		return StringUtils.removeEnd(string, suffix) + suffix;
	}

	/**
	 * Upload prefix.
	 */
	private String uploadUrlPrefix = "upload";

	/**
	 * cache store impl memory level
	 */
	private String cache = "memory";

	private List<String> cacheRedisNodes = new ArrayList<>();

	private String cacheRedisPassword = "";

	/**
	 * hazelcast cache store impl memory level
	 */
	private List<String> hazelcastMembers = new ArrayList<>();

	private String hazelcastGroupName;

	private int initialBackoffSeconds = 5;
}
