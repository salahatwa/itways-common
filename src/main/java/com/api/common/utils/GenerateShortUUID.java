package com.api.common.utils;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.UUID;

public class GenerateShortUUID {

	private static String sessionPrefix = "SR";
	private static String trxPrefix = "TRX";
	private static SimpleDateFormat sessionRefTimeFormat = new SimpleDateFormat("MMddHHmm");

	public static String requestId(String channel) {
		return (Objects.nonNull(channel) ? channel : "") + UUID.randomUUID().toString().replace("-", "");
	}

	public static String sessionRef() {
		return sessionPrefix + sessionRefTimeFormat.format(System.currentTimeMillis())
				+ UUID.randomUUID().toString().replace("-", "").trim();
	}

	public static String trxRef() {
		return trxPrefix + sessionRefTimeFormat.format(System.currentTimeMillis())
				+ UUID.randomUUID().toString().replace("-", "").trim();
	}

}
