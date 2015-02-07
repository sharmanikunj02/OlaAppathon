package com.olaappathon.system;

import java.util.Calendar;

public class DateTimeUtilities {
	public static final long GUID_DATE_CHANGED = 8877632280522743328L;
	public static final long GUID_TIMEZONE_CHANGED = 3596208183088439728L;
	public static final int ONESECOND = 1000;
	public static final int ONEMINUTE = 60000;
	public static final int ONEHOUR = 3600000;
	public static final int ONEDAY = 86400000;
	public static final int ONEWEEK = 604800000;
	public static final long ONEMONTH = 2419200000L;
	public static final long ONEYEAR = 31536000000L;
	public static String GMT;

	public static int getNumberOfDaysInMonth(int month, int year) {
		if (month < Calendar.JANUARY || month > Calendar.DECEMBER) {
			return -1;
		}

		final Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static boolean isSameDate(long d1, long d2) {
		final Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(d1);

		final Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(d2);

		return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
	}

}
