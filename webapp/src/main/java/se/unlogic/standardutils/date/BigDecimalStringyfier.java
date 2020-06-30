package se.unlogic.standardutils.date;

import java.math.BigDecimal;

import se.unlogic.standardutils.string.Stringyfier;

public class BigDecimalStringyfier implements Stringyfier<BigDecimal> {

	@Override
	public String format(BigDecimal bean) {

		return bean.toPlainString();
	}

}
