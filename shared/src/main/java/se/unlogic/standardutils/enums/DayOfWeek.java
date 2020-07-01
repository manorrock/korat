/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.enums;

public enum DayOfWeek {

	MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

	private final int value;

	private DayOfWeek(int value) {

		this.value = value;
	}

	public int getValue() {

		return value;
	}

	public static DayOfWeek fromInt(int dayOfWeek, boolean weekBeginsOnSunday) {

		if (weekBeginsOnSunday) {

			if (dayOfWeek == 1) {

				dayOfWeek = 7;

			} else {

				dayOfWeek--;
			}
		}

		for (DayOfWeek day : DayOfWeek.values()) {

			if (day.value == dayOfWeek) {

				return day;
			}
		}

		return null;

	}

	public static DayOfWeek fromInt(int dayOfWeek) {

		return fromInt(dayOfWeek, false);
	}

}
