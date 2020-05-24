package se.unlogic.standardutils.populators;

public class SwedishOrganizationNumberWithoutMinusPopulator extends SwedishOrganizationNumberPopulator {

	private static final SwedishOrganizationNumberWithoutMinusPopulator POPULATOR = new SwedishOrganizationNumberWithoutMinusPopulator();

	public SwedishOrganizationNumberWithoutMinusPopulator() {

		super(true);
	}

	public static SwedishOrganizationNumberWithoutMinusPopulator getPopulator() {

		return POPULATOR;
	}

}
