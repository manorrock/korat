package se.unlogic.standardutils.populators;

import java.util.Calendar;

import se.unlogic.standardutils.date.DateUtils;

public class SocialSecurityMinAge20Populator extends SwedishSocialSecurity12DigitsWithoutMinusPopulator {

	@Override
	public boolean validateDefaultFormat(String citizenIdentifier) {
		
		if (super.validateDefaultFormat(citizenIdentifier)) {
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.valueOf(citizenIdentifier.substring(0, 4)));
			calendar.set(Calendar.MONTH, Integer.valueOf(citizenIdentifier.substring(4, 6)) - 1);
			calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(citizenIdentifier.substring(6, 8)));
			
			int age = DateUtils.getYearsBetween(calendar, Calendar.getInstance());
			
			return (age >= 20);
		}
		
		return false;
	}
}