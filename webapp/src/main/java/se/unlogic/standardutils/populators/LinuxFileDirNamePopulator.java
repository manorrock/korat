package se.unlogic.standardutils.populators;

import se.unlogic.standardutils.populators.BeanStringPopulator;


public class LinuxFileDirNamePopulator implements BeanStringPopulator<String> {

	private static final String[] RESERVED_CHARACTERS = {"/",">","<","|",":","&"};
	
	@Override
	public boolean validateFormat(String value) {

		for(String character : RESERVED_CHARACTERS){
			
			if(value.contains(character)){
				
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String getValue(String value) {

		return value;
	}

	@Override
	public Class<? extends String> getType() {

		return String.class;
	}

	@Override
	public String getPopulatorID() {

		return null;
	}
}
