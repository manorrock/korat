package se.unlogic.standardutils.populators;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows all formats including samordningsnummer
 * @author exuvo
 */
public class UniversalSwedishSocialSecurityCoordinationPopulator extends CombinedPopulator<String> {

	private static final SwedishSocialSecurityPopulator POPULATOR = new SwedishSocialSecurityPopulator(true, true, true);
	private static final SwedishSocialSecurityPopulator POPULATOR_NO_HYPEN = new SwedishSocialSecurityPopulator(true, true, true, true);

	@SuppressWarnings("unchecked")
	public UniversalSwedishSocialSecurityCoordinationPopulator() {

		super(String.class, POPULATOR, POPULATOR_NO_HYPEN);
	}

	/** Test code to verify that all valid forms of the populator behaves as intended */
	public static void main(String args[]) {

		UniversalSwedishSocialSecurityCoordinationPopulator validator = new UniversalSwedishSocialSecurityCoordinationPopulator();

		List<PopulatorTest> tests = new ArrayList<PopulatorTest>();

		// Invalid text, months, days, checksums
		tests.add(new PopulatorTest("sfdgsdfg", false));
		tests.add(new PopulatorTest("000000-0000", false));
		tests.add(new PopulatorTest("00000000-0000", false));
		tests.add(new PopulatorTest("19930924-8617", false));
		tests.add(new PopulatorTest("20930924-8616", false));
		tests.add(new PopulatorTest("930924+-8616", false));
		tests.add(new PopulatorTest("930924-+8616", false));
		tests.add(new PopulatorTest("930924++8616", false));
		tests.add(new PopulatorTest("930924--8616", false));
		tests.add(new PopulatorTest("930984-8612", false));
		tests.add(new PopulatorTest("19930984-8612", false));
		tests.add(new PopulatorTest("0000000000", false));
		tests.add(new PopulatorTest("000000000000", false));
		tests.add(new PopulatorTest("19930924-8617", false));
		tests.add(new PopulatorTest("20930924-8616", false));
		tests.add(new PopulatorTest("9309848612", false));
		tests.add(new PopulatorTest("199309848612", false));

		// Invalid in the future birthdates
		tests.add(new PopulatorTest("20930924-8616", false));
		tests.add(new PopulatorTest("20930984-8613", false));
		tests.add(new PopulatorTest("209309248616", false));
		tests.add(new PopulatorTest("209309848613", false));

		// Valid social security numbers
		tests.add(new PopulatorTest("930924-8616", true));
		tests.add(new PopulatorTest("19930924-8616", true));
		tests.add(new PopulatorTest("930924+8616", true));
		tests.add(new PopulatorTest("9309248616", true));
		tests.add(new PopulatorTest("199309248616", true));
		tests.add(new PopulatorTest("9309248616", true));

		// Valid samordningsnummer
		tests.add(new PopulatorTest("930984-8613", true));
		tests.add(new PopulatorTest("19930984-8613", true));
		tests.add(new PopulatorTest("930984+8613", true));
		tests.add(new PopulatorTest("9309848613", true));
		tests.add(new PopulatorTest("199309848613", true));
		tests.add(new PopulatorTest("9309848613", true));
		
		// Valid organisation numbers
		tests.add(new PopulatorTest("556815-1889", false));
		tests.add(new PopulatorTest("212000-0142", false));
		tests.add(new PopulatorTest("556036-0793", false));
		tests.add(new PopulatorTest("5568151889", false));
		tests.add(new PopulatorTest("2120000142", false));
		tests.add(new PopulatorTest("5560360793", false));
		
		for (int i = 0; i < tests.size(); i++) {
			PopulatorTest test = tests.get(i);
			
			boolean passed = validator.validateFormat(test.test);
			boolean shouldHavePassed = test.expectedResult;
			
			if (passed != shouldHavePassed) {
				
				System.out.println("Test " + i + " \"" + test.test + "\" failed ( should " + shouldHavePassed + " != " + passed + " ) for " + validator.toString());
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
