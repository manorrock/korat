package se.unlogic.standardutils.validation;

import java.util.ArrayList;
import java.util.List;

import se.unlogic.standardutils.numbers.NumberUtils;

/** Information https://www.postnord.se/globalassets/sverige/pdf/faktablad/postnummersystemet_i_sverige.pdf
 * 
 * @author exuvo */
public class SwedishPostalCodeValidator implements StringFormatValidator {
	
	public static final SwedishPostalCodeValidator INSTANCE = new SwedishPostalCodeValidator();
	
	@Override
	public boolean validateFormat(String value) {
		
		if (value.length() == 6) {
			
			if (value.charAt(2) != ' ' && value.charAt(3) != ' ') {
				return false;
			}
			
			value = value.replace(" ", "");
			
		} else if (value.length() != 5) {
			
			return false;
		}
		
		Integer num = NumberUtils.toInt(value);
		
		if (num == null || num < 10000 || num > 99999) {
			
			return false;
		}
		
		return true;
	}
	
	/** Test code to verify that all valid forms of the populator behaves as intended */
	public static void main(String args[]) {
		
		SwedishPostalCodeValidator validator = new SwedishPostalCodeValidator();
		
		List<PopulatorTest> tests = new ArrayList<PopulatorTest>();
		
		// Invalid
		tests.add(new PopulatorTest("abcdef", false));
		tests.add(new PopulatorTest("1fdgsd", false));
		tests.add(new PopulatorTest("1234", false));
		tests.add(new PopulatorTest("123456", false));
		tests.add(new PopulatorTest("00000", false));
		tests.add(new PopulatorTest("01234", false));
		tests.add(new PopulatorTest("-2345", false));
		tests.add(new PopulatorTest("0x900", false));
		
		// Valid
		tests.add(new PopulatorTest("12345", true));
		tests.add(new PopulatorTest("99999", true));
		tests.add(new PopulatorTest("10000", true));
		
		// Spacing
		tests.add(new PopulatorTest("1 2345", false));
		tests.add(new PopulatorTest("12 345", true));
		tests.add(new PopulatorTest("123 45", true));
		tests.add(new PopulatorTest("1234 5", false));
		
		for (int i = 0; i < tests.size(); i++) {
			
			PopulatorTest test = tests.get(i);
			
			boolean passed = validator.validateFormat(test.test);
			boolean shouldHavePassed = test.expectedResult;
			
			if (passed != shouldHavePassed) {
				
				System.out.println("Test " + i + " \"" + test.test + "\" failed ( should " + shouldHavePassed + " != " + passed + " )");
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
