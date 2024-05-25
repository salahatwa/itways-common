package com.api.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.common.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class JsonService {
	private ObjectMapper mapper;

	@PostConstruct
	public void init() {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public <T> T toObject(String content, Class<T> clazz) {
		try {
			return mapper.readValue(content, clazz);
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Parsing");
		}
	}

	public String toText(Object val) {
		try {
			return mapper.writeValueAsString(val);
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Parsing");
		}
	}

	public Map<String, String> toMap(String json) {
		try {
			return mapper.readValue(json, new TypeReference<Map<String, String>>() {
			});
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Parsing");
		}
	}

	public <T> List<T> toList(String content) {
		try {
			return mapper.readValue(content, new TypeReference<List<T>>() {
			});
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Parsing");
		}
	}
}
