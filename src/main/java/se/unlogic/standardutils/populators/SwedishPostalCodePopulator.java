/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.validation.StringFormatValidator;
import se.unlogic.standardutils.validation.SwedishPostalCodeValidator;

public class SwedishPostalCodePopulator extends BaseStringPopulator<String> {

	public SwedishPostalCodePopulator() {
		super();
	}

	public SwedishPostalCodePopulator(String populatorID, StringFormatValidator formatValidator) {
		super(populatorID, formatValidator);
	}

	public SwedishPostalCodePopulator(String populatorID) {
		super(populatorID);
	}

	@Override
	protected boolean validateDefaultFormat(String value) {

		return SwedishPostalCodeValidator.INSTANCE.validateFormat(value);
	}

	@Override
	public Class<String> getType() {

		return String.class;
	}

	@Override
	public String getValue(String value) {

		return value;
	}

}
