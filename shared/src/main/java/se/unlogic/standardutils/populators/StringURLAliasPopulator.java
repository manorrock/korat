/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.validation.StringFormatValidator;
import se.unlogic.standardutils.validation.URLAliasStringFormatValidator;


public class StringURLAliasPopulator extends BaseStringPopulator<String> implements BeanStringPopulator<String> {

	public StringURLAliasPopulator(String populatorID, StringFormatValidator formatValidator) {
		super(populatorID, formatValidator);
	}
	
	public StringURLAliasPopulator(String populatorID) {
		super(populatorID);
	}

	public StringURLAliasPopulator(){
		super("urlalias");
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
		return URLAliasStringFormatValidator.getValidator().validateFormat(value);
	}

}
