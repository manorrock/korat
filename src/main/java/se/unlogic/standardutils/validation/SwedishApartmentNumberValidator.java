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

/**
 * Accepts 0001 to 9999 with leading zeros required
 * @author exuvo
 */
public class SwedishApartmentNumberValidator implements StringFormatValidator, Serializable {

	private static final SwedishApartmentNumberValidator INSTANCE = new SwedishApartmentNumberValidator();
	private static final long serialVersionUID = 1724653805439617682L;
	
	private static final Pattern PATTERN = Pattern.compile("^(?:000[1-9]|00[1-9][0-9]|0[1-9][0-9]{2}|[1-9][0-9]{3})$");
	
	public static SwedishApartmentNumberValidator getInstance() {
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
