package se.unlogic.standardutils.validation;

import java.util.Calendar;
import java.util.regex.Pattern;


public class Birthdate8DigitValidator implements StringFormatValidator {

	private static final Pattern PATTERN = Pattern.compile("[0-9]{8}");
	
	@Override
	public boolean validateFormat(String value) {

		if(!PATTERN.matcher(value).matches()){
			
			return false;
		}
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		if (Integer.valueOf(value.substring(0, 4)) > year) {

			return false;
		}

		int month = Integer.valueOf(value.substring(4, 6));

		if (month <= 0 || month > 12) {

			return false;
		}

		int day = Integer.valueOf(value.substring(6, 8));

		if (day <= 0 || day > 31) {

			return false;
		}
		
		return true;
	}

}
