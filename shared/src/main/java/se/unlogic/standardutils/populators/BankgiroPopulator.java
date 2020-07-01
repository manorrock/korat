package se.unlogic.standardutils.populators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import se.unlogic.standardutils.dao.BeanResultSetPopulator;
import se.unlogic.standardutils.numbers.NumberUtils;

/** Simple validation of bankgiro numbers
 * 
 * @author exuvo */
public class BankgiroPopulator extends BaseStringPopulator<String> implements BeanResultSetPopulator<String>, BeanStringPopulator<String> {

	private static final Pattern PATTERN = Pattern.compile("\\d{7,8}");

	@Override
	public String populate(ResultSet rs) throws SQLException {

		return rs.getString(1);
	}

	@Override
	public String getValue(String value) {

		return value;
	}

	@Override
	public boolean validateDefaultFormat(String value) {

		if (!PATTERN.matcher(value).matches()) {
			return false;
		}

		// Valid checksum by Luhn algorithm
		return NumberUtils.isValidCC(value);
	}

	@Override
	public Class<? extends String> getType() {

		return String.class;
	}

}
