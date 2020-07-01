/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.validation;

import java.util.regex.Pattern;

public class URLAliasStringFormatValidator implements StringFormatValidator {
	
	private static final URLAliasStringFormatValidator VALIDATOR = new URLAliasStringFormatValidator();
	
	private static final Pattern PATTERN = Pattern.compile("[a-zA-Z0-9_-]+");

	@Override
	public boolean validateFormat(String value) {

		return PATTERN.matcher(value).matches();
	}
	
	public static URLAliasStringFormatValidator getValidator(){
		
		return VALIDATOR;
	}
}
