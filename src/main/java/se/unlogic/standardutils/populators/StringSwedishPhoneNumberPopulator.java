/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.validation.StringFormatValidator;
import se.unlogic.standardutils.validation.SwedishPhoneNumberValidator;


public class StringSwedishPhoneNumberPopulator extends BaseStringPopulator<String> implements BeanStringPopulator<String> {

	private static final StringSwedishPhoneNumberPopulator POPULATOR = new StringSwedishPhoneNumberPopulator();
	
	public StringSwedishPhoneNumberPopulator(String populatorID, StringFormatValidator formatValidator) {
		super(populatorID, formatValidator);
	}
	
	public StringSwedishPhoneNumberPopulator(String populatorID) {
		super(populatorID);
	}

	public StringSwedishPhoneNumberPopulator(){
		super();
	}

	@Override
	public Class<? extends String> getType() {
		return String.class;
	}

	@Override
	public String getValue(String value) {
		return value;
	}

	@Override
	public boolean validateDefaultFormat(String value) {
		return SwedishPhoneNumberValidator.getValidator().validateFormat(value);
	}
	
	public static StringSwedishPhoneNumberPopulator getPopulator(){
		return POPULATOR;
	}

}
