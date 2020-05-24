package se.unlogic.standardutils.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import se.unlogic.standardutils.string.StringUtils;

/** Validates Swedish phone numbers, requires a minimum of 8 digits and a maximum of 15 digits
 * https://sv.wikipedia.org/wiki/Telefonnummer
 * https://en.wikipedia.org/wiki/E.123
 * https://en.wikipedia.org/wiki/National_conventions_for_writing_telephone_numbers
 * @author exuvo */
public class SwedishPhoneNumberValidator implements StringFormatValidator {
	
	private static final SwedishPhoneNumberValidator VALIDATOR = new SwedishPhoneNumberValidator();

	//TODO should only accept numbers that start with +xx or 0
	private static final Pattern pattern = Pattern.compile("\\+?[0-9][0-9 -]+[0-9]");

	@Override
	public boolean validateFormat(String value) {

		if (StringUtils.isEmpty(value)) {

			return false;
		}

		int length = value.replaceAll("[+ -]", "").length();

		return pattern.matcher(value).matches() && length >= 8 && length <= 15;
	}
	
	public static SwedishPhoneNumberValidator getValidator(){
		
		return VALIDATOR;
	}

	/** Test code to verify that the validator behaves as intended */
	public static void main(String args[]) {

		SwedishPhoneNumberValidator validator = new SwedishPhoneNumberValidator();

		List<Test> tests = new ArrayList<Test>();

		// Invalid text
		tests.add(new Test("", false));
		tests.add(new Test("sfdgsdfg", false));
		tests.add(new Test("1234f567", false));
		tests.add(new Test("1234§567", false));
		tests.add(new Test("++0732193727", false));
		tests.add(new Test("-0732193727", false));
		tests.add(new Test("0732193727-", false));

		// Spacing
		tests.add(new Test("0732193727", true));
		tests.add(new Test(" 0732193727", true));
		tests.add(new Test("0732193727 ", true));
		tests.add(new Test("073 219 37 27", true));
		tests.add(new Test("073-219-37-27", true));
		tests.add(new Test("073-219-372-7", true));
		tests.add(new Test("073 219 372 7", true));
		tests.add(new Test("+46732193727", true));
		tests.add(new Test("+4673 219 37 27", true));
		tests.add(new Test("+4673 219 372 7", true));
		tests.add(new Test("+4673-219-372-7", true));
		tests.add(new Test("+4673-219-37-27", true));
		tests.add(new Test("+46 73 219 37 27", true));
		tests.add(new Test("+46-73-219-37-27", true));

		// Length
		tests.add(new Test("0123456", false));
		tests.add(new Test("+0123456", false));
		tests.add(new Test("0 12 3-4-56", false));
		tests.add(new Test("12345678", true));
		tests.add(new Test("123456789012345", true));
		tests.add(new Test("1234567890123456", false));

		for (int i = 0; i < tests.size(); i++) {
			Test test = tests.get(i);

			boolean passed = validator.validateFormat(test.test);
			boolean shouldHavePassed = test.expectedResult;

			if (passed != shouldHavePassed) {

				System.out.println("Test " + i + " \"" + test.test + "\" failed ( should " + shouldHavePassed + " != " + passed + " )");
			}
		}

		System.out.println("Tests completed");
	}

	private static class Test {

		String test;
		public boolean expectedResult;

		public Test(String test, boolean expectedResult) {

			this.test = test;
			this.expectedResult = expectedResult;
		}

	}
}
