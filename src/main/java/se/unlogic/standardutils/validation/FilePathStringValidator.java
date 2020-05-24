package se.unlogic.standardutils.validation;

import se.unlogic.standardutils.io.FileUtils;


public class FilePathStringValidator implements StringFormatValidator {

	@Override
	public boolean validateFormat(String path) {

		return FileUtils.fileExists(path);
	}

}
