package se.unlogic.standardutils.time;

import java.sql.Time;

import se.unlogic.standardutils.string.Stringyfier;


public class TimeStringyfier implements Stringyfier<Time> {

	@Override
	public String format(Time bean) {

		return TimeUtils.TIME_FORMATTER.format(bean);
	}

}
