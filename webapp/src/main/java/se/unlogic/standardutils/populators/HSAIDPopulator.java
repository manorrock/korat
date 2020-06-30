package se.unlogic.standardutils.populators;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import se.unlogic.standardutils.dao.BeanResultSetPopulator;
import se.unlogic.standardutils.numbers.NumberUtils;

public class HSAIDPopulator extends BaseStringPopulator<String> implements BeanResultSetPopulator<String>, BeanStringPopulator<String> {
	
	// https://sv.wikipedia.org/wiki/Organisationsnummer
	// s22 4.1.1 i Handbok för HSA administratörer https://www.inera.se/kundservice/dokument-och-lankar/tjanster/hsa/
	private static final Pattern PATTERN = Pattern.compile("SE(16)?([1-3]|[5-9])[0-9]{9}[-][0-9\\p{IsLatin}]+");
	
	private static final HSAIDPopulator POPULATOR = new HSAIDPopulator();
	
	@Override
	public String populate(ResultSet rs) throws SQLException {
		return rs.getString(1);
	}
	
	public static HSAIDPopulator getPopulator() {
		return POPULATOR;
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
		
		if (value.startsWith("SE16")) {
			value = value.substring(4);
			
		} else {
			value = value.substring(2);
		}
		
		if (Integer.valueOf(value.substring(2, 4)) < 20) {
			return false;
		}
		
		// Valid checksum by Luhn algorithm?
		return NumberUtils.isValidCC(value.substring(0, 10));
	}
	
	@Override
	public Class<? extends String> getType() {
		return String.class;
	}
	
	@Override
	public String toString() {

		return this.getClass().getSimpleName();
	}
	
	/** Test code to verify that all valid forms of the populator behaves as intended */
	public static void main(String args[]) {
		
		List<HSAIDPopulator> validators = new ArrayList<HSAIDPopulator>();

		validators.add(new HSAIDPopulator());

		List<PopulatorTest> tests = new ArrayList<PopulatorTest>();
		
		tests.add(new PopulatorTest("S165565594230-123Abc", false));
		tests.add(new PopulatorTest("EU165565594230-123Abc", false));
		tests.add(new PopulatorTest("SE165565594230-", false));
		tests.add(new PopulatorTest("SE165565594230123Abc", false));
		tests.add(new PopulatorTest("SE165565594230--", false));
		tests.add(new PopulatorTest("SE165565594230-%", false));
		tests.add(new PopulatorTest("SE165565594230-/", false));
		tests.add(new PopulatorTest("SE165565594230-=", false));
		tests.add(new PopulatorTest("SE165565594230-\"", false));
		tests.add(new PopulatorTest("19930924-8616", false));
		tests.add(new PopulatorTest("SE199309248616-a", false));
		
		// Correct
		tests.add(new PopulatorTest("SE165565594230-123Abc", true));
		tests.add(new PopulatorTest("SE165565594230-123AbcHasadsadasdsdfsdf", true));
		tests.add(new PopulatorTest("SE165565594230-123abcåäö", true));
		tests.add(new PopulatorTest("SE165565594230-åäöasddasd", true));
		tests.add(new PopulatorTest("SE165565594230-1234", true));
		
		// Ogiltiga gruppnummer
		tests.add(new PopulatorTest("SE0120000146-123Abc", false));
		tests.add(new PopulatorTest("SE4120000148-123Abc", false));
		
		for (int i = 0; i < tests.size(); i++) {
			PopulatorTest test = tests.get(i);

			for (HSAIDPopulator validator : validators) {

				boolean passed = validator.validateFormat(test.test);
				boolean shouldHavePassed = test.expectedResult;

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

		public PopulatorTest(String test, boolean expectedResult) {
			super();

			this.test = test;
			this.expectedResult = expectedResult;
		}
	}
}
