/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.validation;

import java.io.Serializable;

import se.unlogic.standardutils.numbers.NumberUtils;


public class StringLongValidator extends StringNumberValidator<Long> implements Serializable{

	private static final long serialVersionUID = -7326717405488375362L;

	public StringLongValidator() {
		super(null, null);
	}

	public StringLongValidator(Long minValue,Long maxValue) {
		super(minValue, maxValue);
	}

	@Override
	public boolean validateFormat(String value) {

		Long numberValue = getLongValue(value);

		if(numberValue == null){

			return false;

		}else if(maxValue != null && minValue != null){

			return numberValue <= maxValue && numberValue >= minValue;

		}else if(maxValue != null){

			return numberValue <= maxValue;

		}else if(minValue != null){

			return numberValue >= minValue;
		}
		else{
			return true;
		}
	}
	
	protected Long getLongValue(String value) {

		return NumberUtils.toLong(value);
	}
}
