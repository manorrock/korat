/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.populators;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import se.unlogic.standardutils.dao.BeanResultSetPopulator;
import se.unlogic.standardutils.numbers.NumberUtils;
import se.unlogic.standardutils.validation.StringFormatValidator;

public class BigDecimalPopulator extends BaseStringPopulator<BigDecimal> implements BeanResultSetPopulator<BigDecimal>, BeanStringPopulator<BigDecimal> {
	
	private static final BigDecimalPopulator POPULATOR = new BigDecimalPopulator();
	
	public static BigDecimalPopulator getPopulator(){
		return POPULATOR;
	}

	public BigDecimalPopulator() {
		super();
	}

	public BigDecimalPopulator(String populatorID, StringFormatValidator formatValidator) {
		super(populatorID, formatValidator);
	}

	public BigDecimalPopulator(String populatorID) {
		super(populatorID);
	}

	@Override
	public BigDecimal populate(ResultSet rs) throws SQLException {

		return rs.getBigDecimal(1);
	}

	@Override
	public BigDecimal getValue(String value) {

		return new BigDecimal(value);
	}

	@Override
	public boolean validateDefaultFormat(String value) {

		return NumberUtils.isBigDecimal(value);
	}

	@Override
	public Class<? extends BigDecimal> getType() {

		return BigDecimal.class;
	}

}
