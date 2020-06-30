package se.unlogic.standardutils.populators;


public class CombinedPopulator<T> implements BeanStringPopulator<T> {

	private final Class<T> type;
	private final BeanStringPopulator<T>[] populators;
	
	@SuppressWarnings("unchecked")
	public CombinedPopulator(Class<T> type, BeanStringPopulator<T>... populators) {

		super();
		this.type = type;
		this.populators = populators;
	}

	@Override
	public boolean validateFormat(String value) {

		for(BeanStringPopulator<T> populator : populators){
			
			if(populator.validateFormat(value)){
				
				return true;
			}
		}
		
		return false;
	}

	@Override
	public T getValue(String value) {

		for(BeanStringPopulator<T> populator : populators){
			
			if(populator.validateFormat(value)){
				
				return populator.getValue(value);
			}
		}
		
		return null;
	}

	@Override
	public Class<? extends T> getType() {

		return type;
	}

	@Override
	public String getPopulatorID() {

		return null;
	}
}
