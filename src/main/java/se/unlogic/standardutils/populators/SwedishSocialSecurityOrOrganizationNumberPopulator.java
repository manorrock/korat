package se.unlogic.standardutils.populators;


public class SwedishSocialSecurityOrOrganizationNumberPopulator extends CombinedPopulator<String> {

	private static final SwedishSocialSecurity10DigitsPopulator SOCIAL_SECURITY_POPULATOR = new SwedishSocialSecurity10DigitsPopulator();
	private static final SwedishOrganizationNumberPopulator ORGANIZATION_NUMBER_POPULATOR = new SwedishOrganizationNumberPopulator();
	
	@SuppressWarnings("unchecked")
	public SwedishSocialSecurityOrOrganizationNumberPopulator() {

		super(String.class, SOCIAL_SECURITY_POPULATOR, ORGANIZATION_NUMBER_POPULATOR);
	}

}
