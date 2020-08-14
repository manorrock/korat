package se.unlogic.standardutils.validation;

import se.unlogic.standardutils.io.FileUtils;

public class HTTPFilenameValidator implements StringFormatValidator {

	@Override
	public boolean validateFormat(String value) {

		if (value == null) {

			return false;
		}

		return FileUtils.toValidHttpFilename(value).equals(value);
	}

}
