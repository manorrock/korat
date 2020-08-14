package se.unlogic.standardutils.populators;

/**
 * Allows 10 digit format without samordningsnummer
 * @author exuvo
 */
public class SwedishSocialSecurity10DigitsPopulator extends SwedishSocialSecurityPopulator {

	private static final SwedishSocialSecurity10DigitsPopulator POPULATOR = new SwedishSocialSecurity10DigitsPopulator();

	public SwedishSocialSecurity10DigitsPopulator() {

		super(true, false);
	}

	public static SwedishSocialSecurity10DigitsPopulator getPopulator() {

		return POPULATOR;
	}

}
