package se.unlogic.standardutils.date;

import java.util.Date;

import se.unlogic.standardutils.string.Stringyfier;

public class DateTimeStringyfier implements Stringyfier<Date> {

	@Override
	public String format(Date bean) {

		return DateUtils.DATE_TIME_FORMATTER.format(bean);
	}

}
