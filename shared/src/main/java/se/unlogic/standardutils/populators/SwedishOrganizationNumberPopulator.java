package se.unlogic.standardutils.populators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import se.unlogic.standardutils.dao.BeanResultSetPopulator;
import se.unlogic.standardutils.numbers.NumberUtils;

public class SwedishOrganizationNumberPopulator extends BaseStringPopulator<String> implements BeanResultSetPopulator<String>, BeanStringPopulator<String> {
	
	//https://sv.wikipedia.org/wiki/Organisationsnummer
	private static final Pattern PATTERN = Pattern.compile("([1-3]|[5-9])[0-9]{5}[-][0-9]{4}");
	private static final Pattern PATTERN_NO_HYPHEN = Pattern.compile("([1-3]|[5-9])[0-9]{5}[0-9]{4}");
	
	private static final SwedishOrganizationNumberPopulator POPULATOR = new SwedishOrganizationNumberPopulator();
	
	private final Pattern pattern;
	
	private boolean noHyphen = false;
	
	public SwedishOrganizationNumberPopulator() {
		this(false);
	}
	
	public SwedishOrganizationNumberPopulator(boolean noHyphen) {
		super();
		
		this.noHyphen = noHyphen;
		
		if (noHyphen) {

			pattern = PATTERN_NO_HYPHEN;

		} else {

			pattern = PATTERN;
		}
	}
	
	@Override
	public String populate(ResultSet rs) throws SQLException {
		return rs.getString(1);
	}
	
	public static SwedishOrganizationNumberPopulator getPopulator() {
		return POPULATOR;
	}
	
	@Override
	public String getValue(String value) {
		return value;
	}
	
	@Override
	public boolean validateDefaultFormat(String value) {
		
		if (!pattern.matcher(value).matches()) {
			
			return false;
		}
		
		if (Integer.valueOf(value.substring(2, 4)) < 20) {
			return false;
		}
		
		// Valid checksum by Luhn algorithm?
		return NumberUtils.isValidCC(value.replace("-", ""));
		
	}
	
	public boolean isNoHyphen() {

		return noHyphen;
	}
	
	@Override
	public Class<? extends String> getType() {
		return String.class;
	}
	
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append(this.getClass().getSimpleName());
		builder.append(" (");

		if (isNoHyphen()) {

			builder.append(" noHyphen");
		}

		builder.append(" )");

		return builder.toString();
	}
	
	/** Test code to verify that all valid forms of the populator behaves as intended */
	public static void main(String args[]) {
		
		List<SwedishOrganizationNumberPopulator> validators = new ArrayList<SwedishOrganizationNumberPopulator>();

		validators.add(new SwedishOrganizationNumberPopulator(false));
		validators.add(new SwedishOrganizationNumberPopulator(true));

		List<PopulatorTest> tests = new ArrayList<PopulatorTest>();
		
		tests.add(new PopulatorTest("19930924-8616", false));
		tests.add(new PopulatorTest("930924-8616", false));
		tests.add(new PopulatorTest("550875-1889", false));
		tests.add(new PopulatorTest("199309248616", false));
		tests.add(new PopulatorTest("9309248616", false));
		tests.add(new PopulatorTest("5508751889", false));
		
		// Correct
		tests.add(new PopulatorTest("212000-0142", true));
		tests.add(new PopulatorTest("212000-1355", true));
		tests.add(new PopulatorTest("556036-0793", true));
		tests.add(new PopulatorTest("556815-1889", true));
		tests.add(new PopulatorTest("2120000142", true));
		tests.add(new PopulatorTest("2120001355", true));
		tests.add(new PopulatorTest("5560360793", true));
		tests.add(new PopulatorTest("5568151889", true));
		
		// Ogiltiga gruppnummer
		tests.add(new PopulatorTest("012000-0146", false));
		tests.add(new PopulatorTest("412000-0148", false));
		tests.add(new PopulatorTest("0120000146", false));
		tests.add(new PopulatorTest("4120000148", false));
		
		for (int i = 0; i < tests.size(); i++) {
			PopulatorTest test = tests.get(i);

			for (SwedishOrganizationNumberPopulator validator : validators) {

				boolean passed = validator.validateFormat(test.test);
				boolean shouldHavePassed = test.expectedResult;

				if (test.containsMinus == validator.isNoHyphen()) {

					shouldHavePassed = false;
				}

				if (passed != shouldHavePassed) {

					System.out.println("Test " + i + " \"" + test.test + "\" failed ( should " + shouldHavePassed + " != " + passed + " ) for " + validator.toString());
				}
			}
		}

		System.out.println("Tests completed");
	}
	
	private static class PopulatorTest {

		String test;
		public boolean expectedResult;
		public boolean containsMinus;

		public PopulatorTest(String test, boolean expectedResult) {
			super();

			this.test = test;
			this.expectedResult = expectedResult;

			containsMinus = test.contains("-");
		}
	}
}
