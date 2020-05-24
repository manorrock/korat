package se.unlogic.standardutils.populators;

/**
 * Allows 12 digit format without samordningsnummer
 * @author exuvo
 */
public class SwedishSocialSecurity12DigitsWithoutMinusPopulator extends SwedishSocialSecurityPopulator {

	private static final SwedishSocialSecurity12DigitsWithoutMinusPopulator POPULATOR = new SwedishSocialSecurity12DigitsWithoutMinusPopulator();

	public SwedishSocialSecurity12DigitsWithoutMinusPopulator() {

		super(false, true, false, true);
	}

	public static SwedishSocialSecurity12DigitsWithoutMinusPopulator getPopulator() {

		return POPULATOR;
	}

}
