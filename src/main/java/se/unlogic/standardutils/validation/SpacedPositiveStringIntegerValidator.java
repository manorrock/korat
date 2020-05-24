package se.unlogic.standardutils.validation;


public class SpacedPositiveStringIntegerValidator extends StringIntegerValidator {

	private static final long serialVersionUID = 7083413900689895184L;

	public SpacedPositiveStringIntegerValidator() {

		super(1, null);
	}

	@Override
	protected Integer getIntegerValue(String value) {

		if(value != null){
			
			value = value.replace(" ", "");
		}
		
		return super.getIntegerValue(value);
	}	
}
