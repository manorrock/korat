package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.validation.NonNegativeStringDoubleValidator;


public class NonNegativeStringDoublePopulator extends DoublePopulator {

	public NonNegativeStringDoublePopulator(){
		
		super(null,new NonNegativeStringDoubleValidator());
	}
}
