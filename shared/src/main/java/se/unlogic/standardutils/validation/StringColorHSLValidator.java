/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.validation;

import java.io.Serializable;
import java.util.regex.Pattern;

import se.unlogic.standardutils.string.StringUtils;


public class StringColorHSLValidator implements StringFormatValidator, Serializable {

	private static final StringColorHSLValidator INSTANCE = new StringColorHSLValidator();
	private static final long serialVersionUID = 1724653805439617682L;
	
	private static final Pattern PATTERN = Pattern.compile("(?im)^(?:hsv\\(\\d{1,3},[\t ]?(?:100|[1-9]?[0-9]){1,3}%,[\t ]?(?:100|[1-9]?[0-9]){1,3}%\\)|hsva\\(\\d{1,3},[\t ]?(?:100|[1-9]?[0-9]){1,3}%,[\t ]?(?:100|[1-9]?[0-9]){1,3}%,[\t ]?(?:[,.]\\d{1,3}|[01](?:[,.]\\d{1,3})?)\\))$");
	
	public static StringColorHSLValidator getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean validateFormat(String value) {

		if (StringUtils.isEmpty(value)) {
			
			return false;
		}
		
		return PATTERN.matcher(value).matches();
	}
}
