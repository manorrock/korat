package se.unlogic.standardutils.validation;


public class YearStringFormatValidator extends StringIntegerValidator {

	private static final long serialVersionUID = -1592695847693697279L;

	public YearStringFormatValidator(){
		super(1000, 9999);
	}
}
