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


public class StringColorHSVValidator implements StringFormatValidator, Serializable {

	private static final StringColorHSVValidator INSTANCE = new StringColorHSVValidator();
	private static final long serialVersionUID = 1724653805439617682L;
	
	private static final Pattern PATTERN = Pattern.compile("(?im)^(?:hsl\\(\\d{1,3},[\t ]?(?:100|[1-9]?[0-9]){1,3}%,[\t ]?(?:100|[1-9]?[0-9]){1,3}%\\)|hsla\\(\\d{1,3},[\t ]?(?:100|[1-9]?[0-9]){1,3}%,[\t ]?(?:100|[1-9]?[0-9]){1,3}%,[\t ]?(?:[,.]\\d{1,3}|[01](?:[,.]\\d{1,3})?)\\))$");
	
	public static StringColorHSVValidator getInstance() {
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
