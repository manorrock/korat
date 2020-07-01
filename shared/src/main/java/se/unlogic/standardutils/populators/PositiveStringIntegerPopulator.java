package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.validation.PositiveStringIntegerValidator;


public class PositiveStringIntegerPopulator extends IntegerPopulator {

	private static final PositiveStringIntegerPopulator POPULATOR = new PositiveStringIntegerPopulator();

	public static PositiveStringIntegerPopulator getPopulator(){
		return POPULATOR;
	}

	public PositiveStringIntegerPopulator(){

		super(null,new PositiveStringIntegerValidator());
	}
}
