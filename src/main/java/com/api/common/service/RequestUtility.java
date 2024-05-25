package com.api.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestUtility {

	@Autowired
	private HttpServletRequest request;

	public String getHeader(String key, String defaultValue) {
		if (StringUtils.isBlank(key))
			return "";
		String value = request.getHeader(key);

		return StringUtils.defaultIfEmpty(value, defaultValue);
	}

}
