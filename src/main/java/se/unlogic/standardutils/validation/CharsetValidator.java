package se.unlogic.standardutils.validation;

import java.nio.charset.Charset;

public class CharsetValidator implements StringFormatValidator {

	@Override
	public boolean validateFormat(String value) {

		try {
			Charset.forName(value);
			
			return true;
			
		} catch (Exception e) {

			return false;
		}
	}
}
