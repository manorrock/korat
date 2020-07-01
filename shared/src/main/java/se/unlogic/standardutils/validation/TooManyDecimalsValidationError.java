package se.unlogic.standardutils.validation;

import se.unlogic.standardutils.xml.XMLElement;

@XMLElement(name = "validationError")
public class TooManyDecimalsValidationError extends ValidationError {

	private static final long serialVersionUID = 5609224211802862783L;

	@XMLElement
	private final long currentLength;

	@XMLElement
	private final long maxLength;

	public TooManyDecimalsValidationError(int currentLength, long maxLength) {

		super("TooManyDecimals");
		this.currentLength = currentLength;
		this.maxLength = maxLength;
	}

	public TooManyDecimalsValidationError(String fieldName, long currentLength, long maxLength) {

		super("TooManyDecimals", "", fieldName);
		this.currentLength = currentLength;
		this.maxLength = maxLength;
	}

	public long getMaxLength() {

		return maxLength;
	}

	public long getCurrentLength() {

		return currentLength;
	}
}
