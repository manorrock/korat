package se.unlogic.standardutils.populators;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import se.unlogic.standardutils.dao.BeanResultSetPopulator;
import se.unlogic.standardutils.numbers.NumberUtils;

/** Validates all in use formats of Swedish social security numbers with options on which kinds to allow.
 * 
 * @author exuvo */
public class SwedishSocialSecurityPopulator extends BaseStringPopulator<String>implements BeanResultSetPopulator<String>, BeanStringPopulator<String> {

	// http://sv.wikipedia.org/wiki/Personnummer#Sverige
	private static final Pattern PATTERN_10 = Pattern.compile("[0-9]{6}[-+][0-9]{4}");
	private static final Pattern PATTERN_10_NO_HYPHEN = Pattern.compile("[0-9]{6}[0-9]{4}");
	private static final Pattern PATTERN_12 = Pattern.compile("(19|20)[0-9]{6}-[0-9]{4}");
	private static final Pattern PATTERN_12_NO_HYPHEN = Pattern.compile("(19|20)[0-9]{10}");

	private final Pattern patternFor10;
	private final Pattern patternFor12;

	private boolean allow10Digits = true;
	private boolean allow12Digits = true;
	private boolean allowSamordningsnummer = true;
	private boolean noHyphen = false;

	public SwedishSocialSecurityPopulator() {
		
		this(true, true, false, false);
	}
	
	public SwedishSocialSecurityPopulator(boolean allow10Digits, boolean allow12Digits) {

		this(allow10Digits, allow12Digits, false, false);
	}

	public SwedishSocialSecurityPopulator(boolean allow10Digits, boolean allow12Digits, boolean allowSamordningsnummer) {

		this(allow10Digits, allow12Digits, allowSamordningsnummer, false);
	}

	public SwedishSocialSecurityPopulator(boolean allow10Digits, boolean allow12Digits, boolean allowSamordningsnummer, boolean noHyphen) {
		super();

		if (!allow10Digits && !allow12Digits) {

			throw new InvalidParameterException("You need to accept at least either of 10 or 12 digits formats");
		}

		this.allow10Digits = allow10Digits;
		this.allow12Digits = allow12Digits;
		this.allowSamordningsnummer = allowSamordningsnummer;
		this.noHyphen = noHyphen;

		if (noHyphen) {

			patternFor10 = PATTERN_10_NO_HYPHEN;
			patternFor12 = PATTERN_12_NO_HYPHEN;

		} else {

			patternFor10 = PATTERN_10;
			patternFor12 = PATTERN_12;
		}
	}

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

		int year = Calendar.getInstance().get(Calendar.YEAR);

		boolean checkFuture = true;
		
		// Syntax check
		if (!allow12Digits || (!patternFor12.matcher(value).matches())) {

			if (allow10Digits && patternFor10.matcher(value).matches()) {

				if(noHyphen){
					
					checkFuture = false;
					value = "20" + value;
					
				}else{
					
					value = addCentury(value);
				}

			} else {

				return false;
			}
		}

		// Not in the future checks

		if (checkFuture && Integer.valueOf(value.substring(0, 4)) > year) {

			return false;
		}

		int month = Integer.valueOf(value.substring(4, 6));

		if (month <= 0 || month > 12) {

			return false;
		}

		int day = Integer.valueOf(value.substring(6, 8));

		if (day <= 0) {

			return false;
		}

		if (allowSamordningsnummer) {

			// Supports samordningsnummer, see https://sv.wikipedia.org/wiki/Samordningsnummer
			if ((day > 31 && day < 61) || day > 91) {

				return false;
			}

		} else if (day > 31) {

			return false;
		}

		// Valid checksum by Luhn algorithm?
		return NumberUtils.isValidCC(this.toTenDigitFormat(value));
	}

	@Override
	public Class<? extends String> getType() {

		return String.class;
	}

	/** Converts 12 digit "personnummer" to 10 digit personnummer
	 * Strips the dash character if present
	 * 
	 * @param value
	 * @return */
	protected String toTenDigitFormat(String value) {

		String formattedValue;

		if ((formattedValue = value.replace("-", "")).length() == 12) {

			return formattedValue.substring(2);
		}

		return formattedValue;
	}

	public boolean isAllow10Digits() {

		return allow10Digits;
	}

	public boolean isAllow12Digits() {

		return allow12Digits;
	}

	public boolean isAllowSamordningsnummer() {

		return allowSamordningsnummer;
	}

	public boolean isNoHyphen() {

		return noHyphen;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append(this.getClass().getSimpleName());
		builder.append(" (");

		if (isAllow10Digits()) {

			builder.append(" 10d");
		}

		if (isAllow12Digits()) {

			builder.append(" 12d");
		}

		if (isNoHyphen()) {

			builder.append(" noHyphen");
		}

		if (isAllowSamordningsnummer()) {

			builder.append(" samordning");
		}

		builder.append(" )");

		return builder.toString();
	}

	/** Test code to verify that all valid forms of the populator behaves as intended */
	public static void main(String args[]) {

		List<SwedishSocialSecurityPopulator> validators = new ArrayList<SwedishSocialSecurityPopulator>();

		validators.add(new SwedishSocialSecurityPopulator(true, true, true, true));
		validators.add(new SwedishSocialSecurityPopulator(true, true, true, false));
		validators.add(new SwedishSocialSecurityPopulator(true, true, false, true));
		validators.add(new SwedishSocialSecurityPopulator(true, true, false, false));
		validators.add(new SwedishSocialSecurityPopulator(true, false, true, true));
		validators.add(new SwedishSocialSecurityPopulator(true, false, true, false));
		validators.add(new SwedishSocialSecurityPopulator(true, false, false, true));
		validators.add(new SwedishSocialSecurityPopulator(true, false, false, false));
		validators.add(new SwedishSocialSecurityPopulator(false, true, true, true));
		validators.add(new SwedishSocialSecurityPopulator(false, true, true, false));
		validators.add(new SwedishSocialSecurityPopulator(false, true, false, true));
		validators.add(new SwedishSocialSecurityPopulator(false, true, false, false));

		List<PopulatorTest> tests = new ArrayList<PopulatorTest>();

		// Invalid text, months, days, checksums
		tests.add(new PopulatorTest("sfdgsdfg", false, false, false));
		tests.add(new PopulatorTest("000000-0000", false, false, false));
		tests.add(new PopulatorTest("00000000-0000", false, false, false));
		tests.add(new PopulatorTest("19930924-8617", false, false, false));
		tests.add(new PopulatorTest("20930924-8616", false, false, false));
		tests.add(new PopulatorTest("930924+-8616", false, false, false));
		tests.add(new PopulatorTest("930924-+8616", false, false, false));
		tests.add(new PopulatorTest("930924++8616", false, false, false));
		tests.add(new PopulatorTest("930924--8616", false, false, false));
		tests.add(new PopulatorTest("930984-8612", false, false, true));
		tests.add(new PopulatorTest("19930984-8612", false, false, true));
		tests.add(new PopulatorTest("0000000000", false, false, false));
		tests.add(new PopulatorTest("000000000000", false, false, false));
		tests.add(new PopulatorTest("199309248617", false, false, false));
		tests.add(new PopulatorTest("209309248616", false, false, false));
		tests.add(new PopulatorTest("9309848612", false, false, true));
		tests.add(new PopulatorTest("199309848612", false, false, true));

		// Invalid in the future birthdates
		tests.add(new PopulatorTest("20930924-8616", false, false, false));
		tests.add(new PopulatorTest("20930984-8613", false, false, true));
		tests.add(new PopulatorTest("209309248616", false, false, false));
		tests.add(new PopulatorTest("209309848613", false, false, true));

		// Valid social security numbers
		tests.add(new PopulatorTest("930924-8616", true, false, false));
		tests.add(new PopulatorTest("19930924-8616", false, true, false));
		tests.add(new PopulatorTest("930924+8616", true, false, false));
		tests.add(new PopulatorTest("9309248616", true, false, false));
		tests.add(new PopulatorTest("199309248616", false, true, false));
		tests.add(new PopulatorTest("9309248616", true, false, false));

		// Valid samordningsnummer
		tests.add(new PopulatorTest("930984-8613", true, false, true));
		tests.add(new PopulatorTest("19930984-8613", false, true, true));
		tests.add(new PopulatorTest("930984+8613", true, false, true));
		tests.add(new PopulatorTest("9309848613", true, false, true));
		tests.add(new PopulatorTest("199309848613", false, true, true));
		tests.add(new PopulatorTest("9309848613", true, false, true));

		// Valid organisation numbers
		tests.add(new PopulatorTest("556815-1889", false, false, false));
		tests.add(new PopulatorTest("212000-0142", false, false, false));
		tests.add(new PopulatorTest("556036-0793", false, false, false));
		tests.add(new PopulatorTest("5568151889", false, false, false));
		tests.add(new PopulatorTest("2120000142", false, false, false));
		tests.add(new PopulatorTest("5560360793", false, false, false));

		for (int i = 0; i < tests.size(); i++) {
			PopulatorTest test = tests.get(i);

			for (SwedishSocialSecurityPopulator validator : validators) {

				boolean passed = validator.validateFormat(test.test);
				boolean shouldHavePassed = (validator.isAllow10Digits() && test.expectedResult10) || (validator.isAllow12Digits() && test.expectedResult12);

				if (test.containsMinus == validator.isNoHyphen()) {

					shouldHavePassed = false;
				}

				if (test.isSamordningsNummer && !validator.isAllowSamordningsnummer()) {

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
		public boolean expectedResult10;
		public boolean expectedResult12;
		public boolean isSamordningsNummer;
		public boolean containsMinus;

		public PopulatorTest(String test, boolean expectedResult10, boolean expectedResult12, boolean isSamordningsNummer) {
			super();

			this.test = test;
			this.expectedResult10 = expectedResult10;
			this.expectedResult12 = expectedResult12;
			this.isSamordningsNummer = isSamordningsNummer;

			containsMinus = test.contains("-") || test.contains("+");
		}
	}
	
	public static String addCentury(String citizenIdentifier) {
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		int currentCentury = year / 100;
		int currentDec = year % 100;
		int decennium = Integer.valueOf(citizenIdentifier.substring(0, 2));
		
		if (decennium > currentDec) {
			currentCentury -= 1;
		}
		
		if (citizenIdentifier.contains("+")) {
			currentCentury -= 1;
			citizenIdentifier = citizenIdentifier.replace("+", "-");
		}
		
		return Integer.toString(currentCentury) + citizenIdentifier;
	}
}
