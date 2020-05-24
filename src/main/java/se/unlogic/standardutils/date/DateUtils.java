/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.date;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static final PooledSimpleDateFormat DATE_TIME_SECONDS_FORMATTER = new PooledSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final PooledSimpleDateFormat DATE_TIME_FORMATTER = new PooledSimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final PooledSimpleDateFormat DATE_FORMATTER = new PooledSimpleDateFormat("yyyy-MM-dd");
	public static final PooledSimpleDateFormat YEAR_FORMATTER = new PooledSimpleDateFormat("yyyy");
	public static final PooledSimpleDateFormat YEAR_WEEK_FORMATTER = new PooledSimpleDateFormat("w");

	private static final Date SENSIBLE_YEAR_MIN;
	private static final Date SENSIBLE_YEAR_MAX;

	static {
		Calendar calendar = Calendar.getInstance();

		calendar.set(1000, 1, 1);
		SENSIBLE_YEAR_MIN = calendar.getTime();

		calendar.set(9999, 1, 1);
		SENSIBLE_YEAR_MAX = calendar.getTime();
	}

	public static boolean isValidDate(ThreadSafeDateFormat sdf, String date) {

		return isValidDate(sdf, date, false);
	}

	public static boolean isValidDate(ThreadSafeDateFormat sdf, String date, boolean sensibleYearCheck) {

		try {
			Date parsedDate = sdf.parse(date);

			if (sensibleYearCheck && (parsedDate.before(SENSIBLE_YEAR_MIN) || parsedDate.after(SENSIBLE_YEAR_MAX))) {
				return false;
			}

		} catch (ParseException e) {
			return false;
		} catch (RuntimeException e) {
			return false;
		}
		return true;
	}

	public static boolean isValidDate(DateFormat sdf, String date) {

		return isValidDate(sdf, date, false);
	}

	public static boolean isValidDate(DateFormat sdf, String date, boolean sensibleYearCheck) {

		try {
			Date parsedDate = sdf.parse(date);

			if (sensibleYearCheck && (parsedDate.before(SENSIBLE_YEAR_MIN) || parsedDate.after(SENSIBLE_YEAR_MAX))) {
				return false;
			}

		} catch (ParseException e) {
			return false;
		} catch (RuntimeException e) {
			return false;
		}
		return true;
	}

	public static Date getDate(ThreadSafeDateFormat sdf, String date) {

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		} catch (RuntimeException e) {
			return null;
		}
	}

	public static java.sql.Date getSQLDate(ThreadSafeDateFormat sdf, String date) {

		try {
			return new java.sql.Date(sdf.parse(date).getTime());
		} catch (ParseException e) {
			return null;
		} catch (RuntimeException e) {
			return null;
		}
	}
	
	public static Date getDate(DateFormat sdf, String date) {

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		} catch (RuntimeException e) {
			return null;
		}
	}

	public static double getDateProgress(Date startDate, Date currentDate, Date endDate) {

		if (startDate == null || currentDate == null || endDate == null) {
			throw new NullPointerException();
		}

		if (currentDate.before(startDate)) {
			return 0;
		}

		if (currentDate.after(endDate)) {
			return 1;
		}

		if (startDate.equals(endDate)) {

			if (currentDate.before(endDate)) {
				return 0;

			} else {
				return 1;
			}
		}

		long progress = getDaysBetween(startDate, currentDate);
		long total = getDaysBetween(startDate, endDate);

		if (progress <= 0 || total <= 0) {
			return 0;
		}

		if (progress >= total) {
			return 1;
		}

		return (double) progress / total;
	}

	public static long getDaysBetween(Date startDate, Date endDate) {

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		return getDaysBetween(start, end);
	}

	//TODO check for bugs!
	public static long getDaysBetween(Calendar startDate, Calendar endDate) {

		startDate = (Calendar) startDate.clone();

		long daysBetween = 0;

		while (startDate.get(Calendar.YEAR) < endDate.get(Calendar.YEAR)) {

			if (startDate.get(Calendar.DAY_OF_YEAR) != 1) {

				int diff = startDate.getMaximum(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR);

				diff++;

				startDate.add(Calendar.DAY_OF_YEAR, diff);

				daysBetween += diff;

			} else {

				daysBetween += startDate.getMaximum(Calendar.DAY_OF_YEAR);

				startDate.add(Calendar.YEAR, 1);
			}
		}

		daysBetween += endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR);

		return daysBetween;
	}

	public static int getCurrentYear() {

		return Integer.parseInt(YEAR_FORMATTER.format(new Date()));
	}

	public static int getCurrentWeek() {

		return Integer.parseInt(YEAR_WEEK_FORMATTER.format(new Date()));
	}

	public static java.sql.Date getCurrentSQLDate(boolean includeTime) {

		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

		if (includeTime) {

			return date;

		} else {

			return setTimeToMidnight(date);
		}
	}

	public static <T extends Date> T setTimeToMidnight(T date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		date.setTime(calendar.getTimeInMillis());

		return date;
	}

	public static <T extends Date> T setTimeToMaximum(T date) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		date.setTime(calendar.getTimeInMillis());

		return date;

	}

	public static final String getDateWithMonthString(Date date, Locale locale) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		String monthNames[] = new DateFormatSymbols(locale).getShortMonths();

		return calendar.get(Calendar.DATE) + " " + monthNames[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);

	}

	public static int getWorkingDays(Date startDate, Date endDate) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);

			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

				workDays++;
			}

		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		return workDays;
	}

	public static int getNumberOfWeeksInYear(int year) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		int week = cal.get(Calendar.WEEK_OF_YEAR); //Either 1 or 53

		if (week == 1) {
			return 52;
		} else {
			return week;
		}
	}

	public static java.sql.Date getTodaysDatePlusMinusDays(int days) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);

		return new java.sql.Date(calendar.getTimeInMillis());
	}

	public static int getWeek(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static Date getNextWorkingDay(Date date) {

		return getNextWorkingDay(date, 1);
	}

	public static Date getPreviousWorkingDay(Date date) {

		return getNextWorkingDay(date, -1);
	}

	public static Date getNextWorkingDay(Date date, int direction) {

		if (direction >= 0) {
			direction = 1;

		} else {
			direction = -1;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		do {
			calendar.add(Calendar.DATE, direction);

		} while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);

		return new Date(calendar.getTimeInMillis());
	}

	public static Integer getYearsBetween(Date startDate, Date endDate) {

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		return getYearsBetween(start, end);
	}

	public static Integer getYearsBetween(Calendar start, Calendar end) {

		int diff = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);

		if (start.get(Calendar.MONTH) > end.get(Calendar.MONTH) || (start.get(Calendar.MONTH) == end.get(Calendar.MONTH) && start.get(Calendar.DATE) > end.get(Calendar.DATE))) {
			diff--;
		}

		return diff;
	}
}
