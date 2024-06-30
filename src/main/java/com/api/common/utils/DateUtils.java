package com.api.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * Date utilities.
 *
 * @author ssatwa
 * @date 3/18/19
 */
public class DateUtils {
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private DateUtils() {
    }
    
    public static long getDayCount(String start, String end) {
		long diff = -1;
		try {
			Date dateStart = DATE_FORMAT.parse(start);
			Date dateEnd = DATE_FORMAT.parse(end);

			// time is always 00:00:00, so rounding should help to ignore the missing hour
			// when going from winter to summer time, as well as the extra hour in the other
			// direction
			diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
		} catch (Exception e) {
			// handle the exception according to your own situation
		}
		return diff;
	}
    
    

    /**
     * Gets current date.
     *
     * @return current date
     */
    @NonNull
    public static Date now() {
        return new Date();
    }

    /**
     * Converts from date into a calendar instance.
     *
     * @param date date instance must not be null
     * @return calendar instance
     */
    @NonNull
    public static Calendar convertTo(@NonNull Date date) {
        Assert.notNull(date, "Date must not be null");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Adds date.
     *
     * @param date     current date must not be null
     * @param time     time must not be less than 1
     * @param timeUnit time unit must not be null
     * @return added date
     */
    public static Date add(@NonNull Date date, long time, @NonNull TimeUnit timeUnit) {
        Assert.notNull(date, "Date must not be null");
        Assert.isTrue(time >= 0, "Addition time must not be less than 1");
        Assert.notNull(timeUnit, "Time unit must not be null");

        Date result;

        int timeIntValue;

        if (time > Integer.MAX_VALUE) {
            timeIntValue = Integer.MAX_VALUE;
        } else {
            timeIntValue = Long.valueOf(time).intValue();
        }

        // Calc the expiry time
        switch (timeUnit) {
            case DAYS:
                result = org.apache.commons.lang3.time.DateUtils.addDays(date, timeIntValue);
                break;
            case HOURS:
                result = org.apache.commons.lang3.time.DateUtils.addHours(date, timeIntValue);
                break;
            case MINUTES:
                result = org.apache.commons.lang3.time.DateUtils.addMinutes(date, timeIntValue);
                break;
            case SECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addSeconds(date, timeIntValue);
                break;
            case MILLISECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addMilliseconds(date, timeIntValue);
                break;
            default:
                result = date;
        }
        return result;
    }
}
