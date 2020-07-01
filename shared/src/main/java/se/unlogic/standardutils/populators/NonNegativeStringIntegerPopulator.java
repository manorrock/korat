package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.validation.NonNegativeStringIntegerValidator;


public class NonNegativeStringIntegerPopulator extends IntegerPopulator {

	private static final NonNegativeStringIntegerPopulator POPULATOR = new NonNegativeStringIntegerPopulator();

	public static NonNegativeStringIntegerPopulator getPopulator(){
		return POPULATOR;
	}

	public NonNegativeStringIntegerPopulator(){

		super(null,new NonNegativeStringIntegerValidator());
	}
}
