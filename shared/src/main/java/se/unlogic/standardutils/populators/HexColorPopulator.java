package se.unlogic.standardutils.populators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import se.unlogic.standardutils.dao.BeanResultSetPopulator;

public class HexColorPopulator extends BaseStringPopulator<String> implements BeanResultSetPopulator<String>, BeanStringPopulator<String> {

	private static final Pattern PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

	@Override
	public String getValue(String value) {

		return value;
	}

	@Override
	public Class<? extends String> getType() {

		return String.class;
	}

	@Override
	public String populate(ResultSet rs) throws SQLException {

		return rs.getString(1);
	}

	@Override
	protected boolean validateDefaultFormat(String value) {

		return PATTERN.matcher(value).matches();
	}
	
}
