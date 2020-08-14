package se.unlogic.standardutils.validation;

import se.unlogic.standardutils.xml.XMLElement;

@XMLElement(name = "validationError")
public class TooShortContentValidationError extends ValidationError {

	private static final long serialVersionUID = -5841386296262981341L;

	@XMLElement
	private final long currentLength;

	@XMLElement
	private final long minLength;

	public TooShortContentValidationError(int currentLength, long minLength) {

		super("TooShortFieldContent");
		this.currentLength = currentLength;
		this.minLength = minLength;
	}

	public TooShortContentValidationError(String fieldName, long currentLength, long minLength) {

		super(fieldName, ValidationErrorType.TooShort);
		this.currentLength = currentLength;
		this.minLength = minLength;
	}

	public long getMinLength() {

		return minLength;
	}

	public long getCurrentLength() {

		return currentLength;
	}
}
