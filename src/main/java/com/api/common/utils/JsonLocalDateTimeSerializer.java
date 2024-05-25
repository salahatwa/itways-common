package com.api.common.utils;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider)
			throws IOException {

		String dateTimeString = dateTime.format(ISO_LOCAL_DATE_TIME);
		generator.writeString(dateTimeString);
	}
}
