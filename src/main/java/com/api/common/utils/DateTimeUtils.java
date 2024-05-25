package com.api.common.utils;
//package run.halo.app.utils;
//
//import static cn.hutool.core.date.DatePattern.*;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.time.temporal.ChronoField;
//import java.time.temporal.Temporal;
//
///**
// * Date tool
// *
// * @author ssatwa
// * @date 2019/12/10
// */
//public class DateTimeUtils {
//	/**
//	 * Standard date format {@link DateTimeFormatter}: yyyyMMddHHmmssSSS
//	 */
//	public static final DateTimeFormatter PURE_DATETIME_MS_FORMATTER = new DateTimeFormatterBuilder()
//			.appendPattern(PURE_DATETIME_PATTERN).appendValue(ChronoField.MILLI_OF_SECOND, 3).toFormatter();
//	/**
//	 * Standard date format {@link DateTimeFormatter}: yyyyMMdd
//	 */
//	public static final DateTimeFormatter PURE_DATE_FORMATTER = DateTimeFormatter.ofPattern(PURE_DATE_PATTERN);
//	/**
//	 * Standard date format {@link DateTimeFormatter}: yyyy-MM-dd
//	 */
//	public static final DateTimeFormatter NORM_DATE_FORMATTER = DateTimeFormatter.ofPattern(NORM_DATE_PATTERN);
//	/**
//	 * Standard date format: HHmm
//	 */
//	public final static String TIME_PATTERN = "HHmm";
//	/**
//	 * Standard date format {@link DateTimeFormatter} HHmm
//	 */
//	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
//	/**
//	 * Standard date format: HH:mm
//	 */
//	public final static String NORM_TIME_PATTERN = "HH:mm";
//
//	/**
//	 * Standard date format {@link DateTimeFormatter} HH:mm
//	 */
//	public static final DateTimeFormatter NORM_TIME_FORMATTER = DateTimeFormatter.ofPattern(NORM_TIME_PATTERN);
//
//	/**
//	 * Standard date and time format, accurate to the second
//	 * {@link DateTimeFormatter}: yyyy-MM-dd HH:mm:ss
//	 */
//	public static final DateTimeFormatter NORM_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN);
//
//	/**
//	 * Date and time format separated by horizontal line: yyyy-MM-dd-HH-mm-ss-
//	 */
//	public final static String HORIZONTAL_LINE_PATTERN = "yyyy-MM-dd-HH-mm-ss-";
//
//	/**
//	 * Date and time format separated by horizontal lines, accurate to the second
//	 * {@link DateTimeFormatter}: yyyy-MM-dd-HH-mm-ss-
//	 */
//	public static final DateTimeFormatter HORIZONTAL_LINE_DATETIME_FORMATTER = DateTimeFormatter
//			.ofPattern(HORIZONTAL_LINE_PATTERN);
//
//	/**
//	 * Shanghai time zone format
//	 */
//	public static final String CTT = ZoneId.SHORT_IDS.get("CTT");
//
//	/**
//	 * Shanghai time zone
//	 */
//	public static final ZoneId CTT_ZONE_ID = ZoneId.of(CTT);
//
//	private DateTimeUtils() {
//	}
//
//	/**
//	 * Get the current time, the default is Shanghai time zone
//	 *
//	 * @return Now LocalDateTime
//	 */
//	public static LocalDateTime now() {
//		return now(CTT_ZONE_ID);
//	}
//
//	/**
//	 * Get current time according to time zone
//	 *
//	 * @param zoneId time zone
//	 * @return Now LocalDateTime
//	 */
//	public static LocalDateTime now(ZoneId zoneId) {
//		return LocalDateTime.now(zoneId);
//	}
//
//	/**
//	 * Format by yyyyMMdd
//	 *
//	 * @param localDateTime date and time
//	 * @return Result
//	 */
//	public static String formatDate(LocalDateTime localDateTime) {
//		return format(localDateTime, PURE_DATE_FORMATTER);
//	}
//
//	/**
//	 * Format by yyyyMMdd
//	 *
//	 * @param localDate date
//	 * @return Result
//	 */
//	public static String formatDate(LocalDate localDate) {
//		return format(localDate, PURE_DATE_FORMATTER);
//	}
//
//	/**
//	 * Format by HHmm
//	 *
//	 * @param localDateTime time
//	 * @return Result
//	 */
//	public static String formatTime(LocalDateTime localDateTime) {
//		return format(localDateTime, TIME_FORMATTER);
//	}
//
//	/**
//	 * Format by HHmm
//	 *
//	 * @param localTime time
//	 * @return Result
//	 */
//	public static String formatTime(LocalTime localTime) {
//		return format(localTime, TIME_FORMATTER);
//	}
//
//	/**
//	 * Format in yyyy-MM-dd HH:mm:ss format
//	 *
//	 * @param localDateTime time
//	 * @return Result
//	 */
//	public static String formatDateTime(LocalDateTime localDateTime) {
//		return format(localDateTime, NORM_DATETIME_FORMATTER);
//	}
//
//	/**
//	 * Format time according to date
//	 *
//	 * @param localDateTime time
//	 * @param formatter     time format
//	 * @return Result
//	 */
//	public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter) {
//		return localDateTime.format(formatter);
//	}
//
//	/**
//	 * Format the time according to the time format
//	 *
//	 * @param localTime time
//	 * @param formatter time format
//	 * @return Result
//	 */
//	public static String format(LocalTime localTime, DateTimeFormatter formatter) {
//		return localTime.format(formatter);
//	}
//
//	/**
//	 * Format the date according to the date format
//	 *
//	 * @param localDate date
//	 * @param formatter date format
//	 * @return Result
//	 */
//	public static String format(LocalDate localDate, DateTimeFormatter formatter) {
//		return localDate.format(formatter);
//	}
//
//	/**
//	 * Analyze time in Hong Kong format according to Shanghai time zone
//	 * <p>
//	 * Time format yyyyMMddHHmmssSSS
//	 *
//	 * @param time time
//	 * @return LocalDateTime
//	 */
//	public static LocalDateTime parseCttDateTime(String time) {
//		return parse(time, PURE_DATETIME_MS_FORMATTER);
//	}
//
//	/**
//	 * Parse time according to date format
//	 *
//	 * @param formatter time format
//	 * @param time      time
//	 * @return LocalDateTime
//	 */
//	public static LocalDateTime parse(String time, DateTimeFormatter formatter) {
//		return LocalDateTime.parse(time, formatter);
//	}
//
//	/**
//	 * to instant by default zoneId(Shanghai)
//	 *
//	 * @param localDateTime time
//	 * @return Instant
//	 */
//	public static Instant toInstant(LocalDateTime localDateTime) {
//		return toInstant(localDateTime, CTT_ZONE_ID);
//	}
//
//	/**
//	 * To instant by zoneId
//	 *
//	 * @param localDateTime time
//	 * @return Instant
//	 */
//	public static Instant toInstant(LocalDateTime localDateTime, ZoneId zoneId) {
//		return localDateTime.atZone(zoneId).toInstant();
//	}
//
//	/**
//	 * Convert localDateTime to seconds
//	 *
//	 * @param localDateTime time
//	 * @return seconds
//	 */
//	public static long getEpochSecond(LocalDateTime localDateTime) {
//		return toInstant(localDateTime).getEpochSecond();
//	}
//
//	/**
//	 * Convert localDateTime to timestamp
//	 *
//	 * @param localDateTime time
//	 * @return timestamp
//	 */
//	public static long toEpochMilli(LocalDateTime localDateTime) {
//		return toInstant(localDateTime).toEpochMilli();
//	}
//
//	/**
//	 * Set seconds and milliseconds to 0
//	 *
//	 * @param localDateTime The time that needs to be set
//	 * @return returns the LocalDateTime that is set
//	 */
//	public static LocalDateTime secondAndNanoSetZero(LocalDateTime localDateTime) {
//		return localDateTime.withSecond(0).withNano(0);
//	}
//
//	/**
//	 * Add one day
//	 *
//	 * @param localDateTime date and time
//	 * @return Add LocalDateTime one day later
//	 */
//	public static LocalDateTime plusOneDayToDateTime(LocalDateTime localDateTime) {
//		return plusDays(localDateTime, 1);
//	}
//
//	/**
//	 * Add one day
//	 *
//	 * @param localDateTime date and time
//	 * @param localTime     time
//	 * @return Add LocalDateTime one day later
//	 */
//	public static LocalDateTime plusOneDay(LocalDateTime localDateTime, LocalTime localTime) {
//		return plusOneDay(localDateTime.toLocalDate(), localTime);
//	}
//
//	/**
//	 * Add one day
//	 *
//	 * @param localDate date
//	 * @param localTime time
//	 * @return Add LocalDateTime one day later
//	 */
//	public static LocalDateTime plusOneDay(LocalDate localDate, LocalTime localTime) {
//		final LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
//		return plusDays(localDateTime, 1);
//	}
//
//	/**
//	 * Add one day
//	 *
//	 * @param localDateTime date and time
//	 * @return Add LocalDate one day later
//	 */
//	public static LocalDate plusOneDayToDate(LocalDateTime localDateTime) {
//		return plusDays(localDateTime, 1).toLocalDate();
//	}
//
//	/**
//	 * Add days according to days
//	 *
//	 * @param localDateTime date and time
//	 * @param days
//	 * @return adds LocalDateTime after days
//	 */
//	public static LocalDateTime plusDays(LocalDateTime localDateTime, long days) {
//		return localDateTime.plusDays(days);
//	}
//
//	/**
//	 * Add a day
//	 *
//	 * @param localDate date
//	 * @return Add LocalDate one day later
//	 */
//	public static LocalDate plusOneDayToDate(LocalDate localDate) {
//		return plusDays(localDate, 1);
//	}
//
//	/**
//	 * Add days according to days
//	 *
//	 * @param localDate date
//	 * @param days      added days
//	 * @return adds LocalDate days later
//	 */
//	public static LocalDate plusDays(LocalDate localDate, long days) {
//		return localDate.plusDays(days);
//	}
//
//	/**
//	 * Add 1 minute
//	 *
//	 * @param localDateTime date and time
//	 * @return returns the newly added LocalDateTime
//	 */
//	public static LocalDateTime plusOneMinute(LocalDateTime localDateTime, LocalTime localTime) {
//		return plusOneMinute(localDateTime.toLocalDate(), localTime);
//	}
//
//	/**
//	 * Add 1 minute
//	 *
//	 * @param localDate date
//	 * @param localTime time
//	 * @return returns the newly added LocalDateTime
//	 */
//	public static LocalDateTime plusOneMinute(LocalDate localDate, LocalTime localTime) {
//		final LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
//		return plusMinutes(localDateTime, 1);
//	}
//
//	/**
//	 * Add 1 minute
//	 *
//	 * @param localDateTime date and time
//	 * @return returns the newly added LocalDateTime
//	 */
//	public static LocalDateTime plusOneMinute(LocalDateTime localDateTime) {
//		return plusMinutes(localDateTime, 1);
//	}
//
//	/**
//	 * Add 30 minutes
//	 *
//	 * @param localDateTime date and time
//	 * @return returns the newly added LocalDateTime
//	 */
//	public static LocalDateTime plusThirtyMinute(LocalDateTime localDateTime) {
//		return plusMinutes(localDateTime, 30);
//	}
//
//	/**
//	 * Add 1 minute
//	 *
//	 * @param localDateTime date and time
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalTime plusOneMinuteToTime(LocalDateTime localDateTime) {
//		return plusMinutes(localDateTime, 1).toLocalTime();
//	}
//
//	/**
//	 * Add 1 minute
//	 *
//	 * @param localTime date and time
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalTime plusOneMinute(LocalTime localTime) {
//		return plusMinutes(localTime, 1);
//	}
//
//	/**
//	 * Add 30 minutes
//	 *
//	 * @param localTime date and time
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalTime plusThirtyMinute(LocalTime localTime) {
//		return plusMinutes(localTime, 30);
//	}
//
//	/**
//	 * Add minutes according to minutes
//	 *
//	 * @param localDateTime date and time
//	 * @param minutes       minutes
//	 * @return returns the newly added LocalDateTime
//	 */
//	public static LocalDateTime plusMinutes(LocalDateTime localDateTime, long minutes) {
//		return localDateTime.plusMinutes(minutes);
//	}
//
//	/**
//	 * Add minutes according to minutes
//	 *
//	 * @param localTime time
//	 * @param minutes   minutes
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalTime plusMinutes(LocalTime localTime, long minutes) {
//		return localTime.plusMinutes(minutes);
//	}
//
//	/**
//	 * Reduce by 1 minute
//	 *
//	 * @param localDateTime date and time
//	 * @param localTime     time
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalDateTime minusOneMinutes(LocalDateTime localDateTime, LocalTime localTime) {
//		return minusOneMinutes(localDateTime.toLocalDate(), localTime);
//	}
//
//	/**
//	 * Reduce by 1 minute
//	 *
//	 * @param localDate date
//	 * @param localTime time
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalDateTime minusOneMinutes(LocalDate localDate, LocalTime localTime) {
//		final LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
//		return minusMinutes(localDateTime, 1);
//	}
//
//	/**
//	 * Reduce by 1 minute
//	 *
//	 * @param localDateTime date and time
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalDateTime minusOneMinutes(LocalDateTime localDateTime) {
//		return minusMinutes(localDateTime, 1);
//	}
//
//	/**
//	 * Decrease minutes according to minutes
//	 *
//	 * @param localDateTime date and time
//	 * @param minutes       minutes
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalDateTime minusMinutes(LocalDateTime localDateTime, long minutes) {
//		return localDateTime.minusMinutes(minutes);
//	}
//
//	/**
//	 * Decrease minutes according to minutes
//	 *
//	 * @param localTime time
//	 * @param minutes   minutes
//	 * @return returns the newly added LocalTime
//	 */
//	public static LocalTime minusMinutes(LocalTime localTime, long minutes) {
//		return localTime.minusMinutes(minutes);
//	}
//
//	/**
//	 * Determine whether it is noon
//	 *
//	 * @param startInclusive Start
//	 * @return boolean
//	 */
//	public static boolean isNoon(LocalDateTime startInclusive) {
//		LocalDateTime noonDateTime = LocalDateTime.of(startInclusive.toLocalDate(), LocalTime.NOON);
//		return Duration.between(startInclusive, noonDateTime).isZero();
//	}
//
//	/**
//	 * Determine whether it is noon
//	 *
//	 * @param startInclusive Start
//	 * @return boolean
//	 */
//	public static boolean isNoon(LocalTime startInclusive) {
//		return Duration.between(startInclusive, LocalTime.NOON).isZero();
//	}
//
//	/**
//	 * Whether it is a negative number, startInclusive greater than endInclusive is
//	 * a negative number
//	 *
//	 * @param startInclusive Start
//	 * @param endInclusive   end
//	 * @return boolean
//	 */
//	public static boolean isNegative(Temporal startInclusive, Temporal endInclusive) {
//		return Duration.between(startInclusive, endInclusive).isNegative();
//	}
//
//	/**
//	 * Compared to whether it is 0, if the two times are consistent, it is 0
//	 *
//	 * @param startInclusive Start
//	 * @param endInclusive   end
//	 * @return boolean
//	 */
//	public static boolean isZero(Temporal startInclusive, Temporal endInclusive) {
//		return Duration.between(startInclusive, endInclusive).isZero();
//	}
//
//	/**
//	 * endInclusive is greater than or equal to startInclusive
//	 *
//	 * @param startInclusive Start
//	 * @param endInclusive   end
//	 * @return boolean
//	 */
//	public static boolean isGreaterOrEqual(Temporal startInclusive, Temporal endInclusive) {
//		return Duration.between(startInclusive, endInclusive).toNanos() >= 0;
//	}
//
//	/**
//	 * endInclusive is greater than startInclusive
//	 *
//	 * @param startInclusive Start
//	 * @param endInclusive   end
//	 * @return boolean
//	 */
//	public static boolean isGreater(Temporal startInclusive, Temporal endInclusive) {
//		return Duration.between(startInclusive, endInclusive).toNanos() > 0;
//	}
//
//	/**
//	 * endInclusive is less than or equal to startInclusive
//	 *
//	 * @param startInclusive Start
//	 * @param endInclusive   end
//	 * @return boolean
//	 */
//	public static boolean isLessThanOrEqual(Temporal startInclusive, Temporal endInclusive) {
//		return Duration.between(startInclusive, endInclusive).toNanos() <= 0;
//	}
//}
