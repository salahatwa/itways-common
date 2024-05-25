package com.api.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

	public static byte[] getHash(String password) {
		if (password == null || password.trim().equals(""))
			return new byte[0];
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.reset();
		byte[] input = digest.digest(password.getBytes());
		return input;
	}

	public static String byteToHex(byte[] data) {
		if (data == null) {
			return null;
		}
		String hexa = "";
		for (int i = 0; i < data.length; i++) {
			hexa += byteToHex(data[i]);
		}
		return hexa;
	}

	public static String byteToHex(byte data) {
		StringBuffer buf = new StringBuffer();
		buf.append(toHexChar((data >>> 4) & 0x0F));
		buf.append(toHexChar(data & 0x0F));
		return buf.toString();
	}

	public static byte[] hexToBytes(String str) {
		if (str == null || str.length() < 2)
			return null;
		else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++)
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			return buffer;
		}
	}

	/**
	 * Convenience method to convert an int to a hex char.
	 * 
	 * @param i the int to convert
	 * @return char the converted char
	 */
	public static char toHexChar(int i) {
		if ((0 <= i) && (i <= 9)) {
			return (char) ('0' + i);
		} else {
			return (char) ('a' + (i - 10));
		}
	}

}