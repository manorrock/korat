package se.unlogic.standardutils.populators;


public class UniversalSwedishOrganizationNumberPopulator extends CombinedPopulator<String> {

	@SuppressWarnings("unchecked")
	public UniversalSwedishOrganizationNumberPopulator() {

		super(String.class, SwedishOrganizationNumberPopulator.getPopulator(), SwedishOrganizationNumberWithoutMinusPopulator.getPopulator());
	}
}
