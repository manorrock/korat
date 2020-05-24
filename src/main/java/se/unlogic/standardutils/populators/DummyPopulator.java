package se.unlogic.standardutils.populators;


public class DummyPopulator implements BeanStringPopulator<Object> {

	@Override
	public boolean validateFormat(String value) {

		return false;
	}

	@Override
	public Object getValue(String value) {

		return null;
	}

	@Override
	public Class<Object> getType() {

		return null;
	}

	@Override
	public String getPopulatorID() {

		return null;
	}
}
