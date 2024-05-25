package com.api.common.utils;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	private static final String NULL_VALUE = "null";

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);
		String dateString = node.textValue();

		LocalDateTime dateTime = null;
		if (!NULL_VALUE.equals(dateString)) {
			dateTime = LocalDateTime.parse(dateString, ISO_LOCAL_DATE_TIME);
		}
		return dateTime;
	}
}
