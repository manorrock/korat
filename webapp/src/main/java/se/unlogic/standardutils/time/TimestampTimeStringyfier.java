package se.unlogic.standardutils.time;

import java.sql.Timestamp;

import se.unlogic.standardutils.string.Stringyfier;


public class TimestampTimeStringyfier implements Stringyfier<Timestamp> {
	
	@Override
	public String format(Timestamp bean) {

		return TimeUtils.TIME_FORMATTER.format(bean);
	}
}