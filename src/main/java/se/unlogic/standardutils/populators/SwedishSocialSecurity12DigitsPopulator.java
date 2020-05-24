package se.unlogic.standardutils.populators;

/**
 * Allows 12 digit format with minus sign and without samordningsnummer
 * @author exuvo
 */
public class SwedishSocialSecurity12DigitsPopulator extends SwedishSocialSecurityPopulator {

	private static final SwedishSocialSecurity12DigitsPopulator POPULATOR = new SwedishSocialSecurity12DigitsPopulator();

	public SwedishSocialSecurity12DigitsPopulator() {

		super(false, true);
	}

	public static SwedishSocialSecurity12DigitsPopulator getPopulator() {

		return POPULATOR;
	}

}
