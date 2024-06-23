package com.api.common.utils;

import java.io.File;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

/**
 * Common utils
 *
 * @author ssatwa
 * @date 2017-12-22
 */
@Slf4j
public class AppUtils {

	/**
	 * Path separator.
	 */
	public static final String FILE_SEPARATOR = File.separator;

	public final static String PROTOCOL_HTTPS = "https://";

	public final static String PROTOCOL_HTTP = "http://";

	public static final String URL_SEPARATOR = "/";
	private static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";

	@NonNull
	public static String ensureBoth(@NonNull String string, @NonNull String bothfix) {
		return ensureBoth(string, bothfix, bothfix);
	}

	@NonNull
	public static String ensureBoth(@NonNull String string, @NonNull String prefix, @NonNull String suffix) {
		return ensureSuffix(ensurePrefix(string, prefix), suffix);
	}

	/**
	 * Ensures the string contain prefix.
	 *
	 * @param string string must not be blank
	 * @param prefix prefix must not be blank
	 * @return string contain prefix specified
	 */
	@NonNull
	public static String ensurePrefix(@NonNull String string, @NonNull String prefix) {
		Assert.hasText(string, "String must not be blank");
		Assert.hasText(prefix, "Prefix must not be blank");

		return prefix + StringUtils.removeStart(string, prefix);
	}

	/**
	 * Ensures the string contain suffix.
	 *
	 * @param string string must not be blank
	 * @param suffix suffix must not be blank
	 * @return string contain suffix specified
	 */
	@NonNull
	public static String ensureSuffix(@NonNull String string, @NonNull String suffix) {
		Assert.hasText(string, "String must not be blank");
		Assert.hasText(suffix, "Suffix must not be blank");

		return StringUtils.removeEnd(string, suffix) + suffix;
	}

	/**
	 * Composites partial url to full http url.
	 *
	 * @param partUrls partial urls must not be empty
	 * @return full url
	 */
	public static String compositeHttpUrl(@NonNull String... partUrls) {
		Assert.notEmpty(partUrls, "Partial url must not be blank");

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < partUrls.length; i++) {
			String partUrl = partUrls[i];
			if (StringUtils.isBlank(partUrl)) {
				continue;
			}
			partUrl = StringUtils.removeStart(partUrl, URL_SEPARATOR);
			partUrl = StringUtils.removeEnd(partUrl, URL_SEPARATOR);
			if (i != 0) {
				builder.append(URL_SEPARATOR);
			}
			builder.append(partUrl);
		}

		return builder.toString();
	}

	/**
	 * Desensitizes the plain text.
	 *
	 * @param plainText plain text must not be null
	 * @param leftSize  left size
	 * @param rightSize right size
	 * @return desensitization
	 */
	public static String desensitize(@NonNull String plainText, int leftSize, int rightSize) {
		Assert.hasText(plainText, "Plain text must not be blank");

		if (leftSize < 0) {
			leftSize = 0;
		}

		if (leftSize > plainText.length()) {
			leftSize = plainText.length();
		}

		if (rightSize < 0) {
			rightSize = 0;
		}

		if (rightSize > plainText.length()) {
			rightSize = plainText.length();
		}

		if (plainText.length() < leftSize + rightSize) {
			rightSize = plainText.length() - leftSize;
		}

		int remainSize = plainText.length() - rightSize - leftSize;

		String left = StringUtils.left(plainText, leftSize);
		String right = StringUtils.right(plainText, rightSize);
		return StringUtils.rightPad(left, remainSize + leftSize, '*') + right;
	}

	/**
	 * Changes file separator to url separator.
	 *
	 * @param pathname full path name must not be blank.
	 * @return text with url separator
	 */
	public static String changeFileSeparatorToUrlSeparator(@NonNull String pathname) {
		Assert.hasText(pathname, "Path name must not be blank");

		return pathname.replace(FILE_SEPARATOR, "/");
	}

	/**
	 * Time format.
	 *
	 * @param totalSeconds seconds
	 * @return formatted time
	 */
	@NonNull
	public static String timeFormat(long totalSeconds) {
		if (totalSeconds <= 0) {
			return "0 second";
		}

		StringBuilder timeBuilder = new StringBuilder();

		long hours = totalSeconds / 3600;
		long minutes = totalSeconds % 3600 / 60;
		long seconds = totalSeconds % 3600 % 60;

		if (hours > 0) {
			if (StringUtils.isNotBlank(timeBuilder)) {
				timeBuilder.append(", ");
			}
			timeBuilder.append(pluralize(hours, "hour", "hours"));
		}

		if (minutes > 0) {
			if (StringUtils.isNotBlank(timeBuilder)) {
				timeBuilder.append(", ");
			}
			timeBuilder.append(pluralize(minutes, "minute", "minutes"));
		}

		if (seconds > 0) {
			if (StringUtils.isNotBlank(timeBuilder)) {
				timeBuilder.append(", ");
			}
			timeBuilder.append(pluralize(seconds, "second", "seconds"));
		}

		return timeBuilder.toString();
	}

	/**
	 * Pluralize the times label format.
	 *
	 * @param times       times
	 * @param label       label
	 * @param pluralLabel plural label
	 * @return pluralized format
	 */
	@NonNull
	public static String pluralize(long times, @NonNull String label, @NonNull String pluralLabel) {
		Assert.hasText(label, "Label must not be blank");
		Assert.hasText(pluralLabel, "Plural label must not be blank");

		if (times <= 0) {
			return "no " + pluralLabel;
		}

		if (times == 1) {
			return times + " " + label;
		}

		return times + " " + pluralLabel;
	}

	/**
	 * Gets random uuid without dash.
	 *
	 * @return random uuid without dash
	 */
	@NonNull
	public static String randomUUIDWithoutDash() {
		return StringUtils.remove(UUID.randomUUID().toString(), '-');
	}

	/**
	 * Initialize url if blank.
	 *
	 * @param url url can be blank
	 * @return initial url
	 */
	@NonNull
	public static String initializeUrlIfBlank(@Nullable String url) {
		if (!StringUtils.isBlank(url)) {
			return url;
		}
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * Normalize url
	 *
	 * @param originalUrl original url
	 * @return normalized url.
	 */
	@NonNull
	public static String normalizeUrl(@NonNull String originalUrl) {
		Assert.hasText(originalUrl, "Original Url must not be blank");

		if (StringUtils.startsWithAny(originalUrl, URL_SEPARATOR, PROTOCOL_HTTPS, PROTOCOL_HTTP)
				&& !StringUtils.startsWith(originalUrl, "//")) {
			return originalUrl;
		}

		try {
			return NormalizeURL.normalize(originalUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return originalUrl;
	}

	/**
	 * Gets machine IP address.
	 *
	 * @return current machine IP address.
	 */
	public static String getMachineIP() {
		InetAddress machineAddress;
		try {
			machineAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			machineAddress = InetAddress.getLoopbackAddress();
		}
		return machineAddress.getHostAddress();
	}

	/**
	 * Clean all html tag
	 *
	 * @param content html document
	 * @return text before cleaned
	 */
	public static String cleanHtmlTag(String content) {
		if (StringUtils.isEmpty(content)) {
			return StringUtils.EMPTY;
		}
		return content.replaceAll(RE_HTML_MARK, StringUtils.EMPTY);
	}

	/**
	 * 获取指定长度随机字符串
	 *
	 * @param length 长度
	 * @return
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(3);
			long result = 0;
			switch (number) {
			case 0:
				result = Math.round(Math.random() * 25 + 65);
				sb.append((char) result);
				break;
			case 1:
				result = Math.round(Math.random() * 25 + 97);
				sb.append((char) result);
				break;
			case 2:
				sb.append(new Random().nextInt(10));
				break;
			default:
				break;
			}
		}
		return sb.toString();
	}

	public static BigInteger stringToBigInt(String ipInString) {
		ipInString = ipInString.replace(" ", "");
		byte[] bytes;
		if (ipInString.contains(":")) {
			bytes = ipv6ToBytes(ipInString);
		} else {
			bytes = ipv4ToBytes(ipInString);
		}
		return new BigInteger(bytes);
	}

	private static byte[] ipv6ToBytes(String ipv6) {
		byte[] ret = new byte[17];
		ret[0] = 0;
		int ib = 16;
		boolean comFlag = false;
		if (ipv6.startsWith(":")) {
			ipv6 = ipv6.substring(1);
		}
		String groups[] = ipv6.split(":");
		for (int ig = groups.length - 1; ig > -1; ig--) {
			if (groups[ig].contains(".")) {
				byte[] temp = ipv4ToBytes(groups[ig]);
				ret[ib--] = temp[4];
				ret[ib--] = temp[3];
				ret[ib--] = temp[2];
				ret[ib--] = temp[1];
				comFlag = true;
			} else if ("".equals(groups[ig])) {
				// å‡ºçŽ°é›¶é•¿åº¦åŽ‹ç¼©,è®¡ç®—ç¼ºå°‘çš„ç»„æ•°
				int zlg = 9 - (groups.length + (comFlag ? 1 : 0));
				// å°†è¿™äº›ç»„ç½®0
				while (zlg-- > 0) {
					ret[ib--] = 0;
					ret[ib--] = 0;
				}
			} else {
				int temp = Integer.parseInt(groups[ig], 16);
				ret[ib--] = (byte) temp;
				ret[ib--] = (byte) (temp >> 8);
			}
		}
		return ret;
	}

	private static byte[] ipv4ToBytes(String ipv4) {
		byte[] ret = new byte[5];
		ret[0] = 0;
		int position1 = ipv4.indexOf(".");
		int position2 = ipv4.indexOf(".", position1 + 1);
		int position3 = ipv4.indexOf(".", position2 + 1);
		ret[1] = (byte) Integer.parseInt(ipv4.substring(0, position1));
		ret[2] = (byte) Integer.parseInt(ipv4.substring(position1 + 1, position2));
		ret[3] = (byte) Integer.parseInt(ipv4.substring(position2 + 1, position3));
		ret[4] = (byte) Integer.parseInt(ipv4.substring(position3 + 1));
		return ret;
	}
}
