package se.unlogic.standardutils.populators;

public class SwedishSocialSecurity12DigitsMinusOptionalPopulator extends CombinedPopulator<String> {

	@SuppressWarnings("unchecked")
	public SwedishSocialSecurity12DigitsMinusOptionalPopulator() {

		super(String.class, SwedishSocialSecurity12DigitsPopulator.getPopulator(), SwedishSocialSecurity12DigitsWithoutMinusPopulator.getPopulator());
	}

}
