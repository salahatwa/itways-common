package com.api.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

public class Utils {
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public static String generateRandomCode() {
		StringBuilder salt = new StringBuilder();
		Random random = new Random();
		while (salt.length() < 4) {
			int index = (int) (random.nextFloat() * 10);
			salt.append(index);
		}
		return salt.toString();
	}

	public static String getRiyadhTime() {
		sdf2.setTimeZone(TimeZone.getTimeZone("Asia/Riyadh"));
		return sdf2.format(Calendar.getInstance().getTime());
	}
}
