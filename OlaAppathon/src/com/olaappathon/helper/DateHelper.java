package com.olaappathon.helper;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.content.Context;
import android.text.format.DateUtils;

/**
 * The Class DateHelper
 */
public class DateHelper {
	private static final long MILLISECONDS_IN_SECOND = (1000L);
	private static final long MILLISECONDS_IN_MINUTE = (MILLISECONDS_IN_SECOND * 60L);
	private static final long MILLISECONDS_IN_HOUR = (MILLISECONDS_IN_MINUTE * 60L);
	public static final long MILLISECONDS_IN_DAY = (MILLISECONDS_IN_HOUR * 24L);

	/** The Constant MONDAY. */
	public static final int MONDAY = (1 << 0);

	/** The Constant TUESDAY. */
	public static final int TUESDAY = (1 << 1);

	/** The Constant WEDNESDAY. */
	public static final int WEDNESDAY = (1 << 2);

	/** The Constant THURSDAY. */
	public static final int THURSDAY = (1 << 3);

	/** The Constant FRIDAY. */
	public static final int FRIDAY = (1 << 4);

	/** The Constant SATURDAY. */
	public static final int SATURDAY = (1 << 5);

	/** The Constant SUNDAY. */
	public static final int SUNDAY = (1 << 6);

	/**
	 * Method newCalendar.
	 * 
	 * @param date
	 *            long
	 * @return Calendar
	 */
	public static Calendar newCalendar(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		return calendar;
	}

	/**
	 * Method newCalendar.
	 * 
	 * @param a_date
	 *            long
	 * @param a_time
	 *            long
	 * @return Calendar
	 */
	public static Calendar newCalendar(long a_date, long a_time) {
		Calendar date = Calendar.getInstance();
		date.setTime(new Date(a_date));

		date.set(Calendar.HOUR_OF_DAY, (int) (a_time / DateUtils.HOUR_IN_MILLIS));
		date.set(Calendar.MINUTE, (int) ((a_time % DateUtils.HOUR_IN_MILLIS) / DateUtils.MINUTE_IN_MILLIS));
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date;
	}

	/**
	 * Method getTimeOfDay.
	 * 
	 * @param time
	 *            long
	 * @return long
	 */
	public static long getTimeOfDay(long time) {
		time %= MILLISECONDS_IN_DAY;
		while (time < 0) {
			time += MILLISECONDS_IN_DAY;
		}
		return time;
	}

	/**
	 * Method getDayOfWeekAsMask.
	 * 
	 * @param calendar
	 *            Calendar
	 * @return int
	 */
	public static int getDayOfWeekAsMask(Calendar calendar) {
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			return (1 << 0);
		case Calendar.TUESDAY:
			return (1 << 1);
		case Calendar.WEDNESDAY:
			return (1 << 2);
		case Calendar.THURSDAY:
			return (1 << 3);
		case Calendar.FRIDAY:
			return (1 << 4);
		case Calendar.SATURDAY:
			return (1 << 5);
		case Calendar.SUNDAY:
			return (1 << 6);
		}
		return 0;
	}

	/**
	 * Method millisToNextMinute.
	 * 
	 * @return long
	 */
	public static long millisToNextMinute() {
		long now = System.currentTimeMillis();
		return MILLISECONDS_IN_MINUTE - (now % MILLISECONDS_IN_MINUTE);
	}

	/**
	 * Method formatTime.
	 * 
	 * @param context
	 *            Context
	 * @param time
	 *            long
	 * @return String
	 */
	public static String formatTime(Context context, long time) {
		DateFormat format = android.text.format.DateFormat.getTimeFormat(context);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		return format.format(new Date(time));
	}

}
