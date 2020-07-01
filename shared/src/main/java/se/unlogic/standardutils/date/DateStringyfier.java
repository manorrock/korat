package se.unlogic.standardutils.date;

import java.util.Date;

import se.unlogic.standardutils.string.Stringyfier;


public class DateStringyfier implements Stringyfier<Date> {

	@Override
	public String format(Date bean) {

		return DateUtils.DATE_FORMATTER.format(bean);
	}

}
