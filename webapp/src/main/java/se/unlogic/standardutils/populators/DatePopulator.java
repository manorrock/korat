/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.populators;

import java.sql.Date;
import java.text.ParseException;

import se.unlogic.standardutils.date.DateUtils;
import se.unlogic.standardutils.date.ThreadSafeDateFormat;
import se.unlogic.standardutils.validation.StringFormatValidator;

public class DatePopulator extends BaseStringPopulator<Date> {

	private static final DatePopulator POPULATOR = new DatePopulator();
	private static final DatePopulator YEAR_LIMITED_POPULATOR = new DatePopulator(true);

	public static DatePopulator getPopulator() {

		return POPULATOR;
	}
	
	public static DatePopulator getYearLimitedPopulator() {

		return YEAR_LIMITED_POPULATOR;
	}

	private final ThreadSafeDateFormat dateFormat;
	private final boolean sensibleYearCheck;

	public DatePopulator() {

		super();

		this.dateFormat = DateUtils.DATE_FORMATTER;
		sensibleYearCheck = false;
	}

	public DatePopulator(ThreadSafeDateFormat dateFormat) {

		super();

		this.dateFormat = dateFormat;
		sensibleYearCheck = false;
	}

	public DatePopulator(String populatorID, ThreadSafeDateFormat dateFormat) {

		super(populatorID);

		this.dateFormat = dateFormat;
		sensibleYearCheck = false;
	}

	public DatePopulator(String populatorID, ThreadSafeDateFormat dateFormat, StringFormatValidator formatValidator) {

		super(populatorID, formatValidator);
		
		this.dateFormat = dateFormat;
		sensibleYearCheck = false;
	}
	
	public DatePopulator(boolean sensibleYearCheck) {

		super();

		this.dateFormat = DateUtils.DATE_FORMATTER;
		this.sensibleYearCheck = sensibleYearCheck;
	}

	public DatePopulator(ThreadSafeDateFormat dateFormat, boolean sensibleYearCheck) {

		super();

		this.dateFormat = dateFormat;
		this.sensibleYearCheck = sensibleYearCheck;
	}

	public DatePopulator(String populatorID, ThreadSafeDateFormat dateFormat, boolean sensibleYearCheck) {

		super(populatorID);

		this.dateFormat = dateFormat;
		this.sensibleYearCheck = sensibleYearCheck;
	}

	public DatePopulator(String populatorID, ThreadSafeDateFormat dateFormat, StringFormatValidator formatValidator, boolean sensibleYearCheck) {

		super(populatorID, formatValidator);
		
		this.dateFormat = dateFormat;
		this.sensibleYearCheck = sensibleYearCheck;
	}

	@Override
	public Class<? extends Date> getType() {

		return Date.class;
	}

	@Override
	public Date getValue(String value) {

		try {
			java.util.Date utilDate = this.dateFormat.parse(value);

			return new Date(utilDate.getTime());

		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean validateDefaultFormat(String value) {

		return DateUtils.isValidDate(this.dateFormat, value, sensibleYearCheck);
	}
}
