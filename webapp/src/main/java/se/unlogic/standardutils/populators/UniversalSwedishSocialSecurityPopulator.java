package se.unlogic.standardutils.populators;


public class UniversalSwedishSocialSecurityPopulator extends CombinedPopulator<String> {

	private static final SwedishSocialSecurityPopulator WITH_HYPHEN = new SwedishSocialSecurityPopulator(true, true, false, false);
	private static final SwedishSocialSecurityPopulator WITHOUT_HYPHEN = new SwedishSocialSecurityPopulator(true, true, false, true);
	
	@SuppressWarnings("unchecked")
	public UniversalSwedishSocialSecurityPopulator() {

		super(String.class, WITH_HYPHEN, WITHOUT_HYPHEN);
	}

}
