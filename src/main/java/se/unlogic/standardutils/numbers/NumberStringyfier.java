package se.unlogic.standardutils.numbers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import se.unlogic.standardutils.string.Stringyfier;

public class NumberStringyfier implements Stringyfier<Number> {

	private DecimalFormat formatter;

	public NumberStringyfier() {
		this(0, 2, true);
	}

	public NumberStringyfier(int minDecimals, int maxDecimals, boolean grouping) {

		this(minDecimals, maxDecimals, grouping, null);
	}

	public NumberStringyfier(int minDecimals, int maxDecimals, boolean grouping, Locale locale) {
		super();

		formatter = new DecimalFormat();
		formatter.setMinimumFractionDigits(minDecimals);
		formatter.setMaximumFractionDigits(maxDecimals);

		if (locale != null) {

			formatter.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
		}

		formatter.setGroupingUsed(grouping);
	}

	@Override
	public String format(Number number) {

		return formatter.format(number);
	}

}
