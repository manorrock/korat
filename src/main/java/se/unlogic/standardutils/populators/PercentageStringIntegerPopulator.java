package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.validation.PercentageStringIntegerValidator;


public class PercentageStringIntegerPopulator extends IntegerPopulator {

	private static final PercentageStringIntegerPopulator POPULATOR = new PercentageStringIntegerPopulator();

	public static PercentageStringIntegerPopulator getPopulator(){
		return POPULATOR;
	}

	public PercentageStringIntegerPopulator(){

		super(null, new PercentageStringIntegerValidator());
	}
}
