package se.unlogic.standardutils.validation;


public class SpacedPositiveStringLongValidator extends StringLongValidator {

	private static final long serialVersionUID = 7083413900689895184L;

	public SpacedPositiveStringLongValidator() {

		super(1l, null);
	}

	@Override
	protected Long getLongValue(String value) {

		if(value != null){
			
			value = value.replace(" ", "");
		}
		
		return super.getLongValue(value);
	}	
}
