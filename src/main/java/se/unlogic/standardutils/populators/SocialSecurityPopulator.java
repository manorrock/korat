package se.unlogic.standardutils.populators;

/**
 * Allows 10 and 12 digit formats without samordningsnummer
 * @author exuvo
 */
public class SocialSecurityPopulator extends SwedishSocialSecurityPopulator {

	private static final SocialSecurityPopulator POPULATOR = new SocialSecurityPopulator();

	public SocialSecurityPopulator() {

		super(true, true, false);
	}

	public static SocialSecurityPopulator getPopulator() {

		return POPULATOR;
	}

}
