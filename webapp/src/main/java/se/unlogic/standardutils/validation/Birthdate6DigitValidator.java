package se.unlogic.standardutils.validation;

import java.util.regex.Pattern;


public class Birthdate6DigitValidator implements StringFormatValidator {

	private static final Pattern PATTERN = Pattern.compile("[0-9]{6}");
	
	@Override
	public boolean validateFormat(String value) {

		if(!PATTERN.matcher(value).matches()){
			
			return false;
		}
		
		int month = Integer.valueOf(value.substring(2, 4));

		if (month <= 0 || month > 12) {

			return false;
		}

		int day = Integer.valueOf(value.substring(4, 6));

		if (day <= 0 || day > 31) {

			return false;
		}		
		
		return true;
	}

}
