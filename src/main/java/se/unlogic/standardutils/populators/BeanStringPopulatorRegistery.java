package se.unlogic.standardutils.populators;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;


public class BeanStringPopulatorRegistery {

	private final static HashMap<Class<?>, BeanStringPopulator<?>> TYPE_POPULATORS = new HashMap<Class<?>, BeanStringPopulator<?>>();

	static{
		addTypePopulator(new UUIDPopulator());
		addTypePopulator(BooleanPopulator.getPopulator());
		addTypePopulator(DoublePopulator.getPopulator());
		addTypePopulator(FloatPopulator.getPopulator());
		addTypePopulator(IntegerPopulator.getPopulator());
		addTypePopulator(LongPopulator.getPopulator());
		addTypePopulator(PrimitiveBooleanPopulator.getPopulator());
		addTypePopulator(PrimitiveIntegerPopulator.getPopulator());
		addTypePopulator(new PrimitiveLongPopulator());
		addTypePopulator(new PrimitiveFloatPopulator());
		addTypePopulator(StringPopulator.getPopulator());
		addTypePopulator(DatePopulator.getPopulator());
		addTypePopulator(TimeStampPopulator.getPopulator());
		addTypePopulator(BigDecimalPopulator.getPopulator());
	}

	private static void addTypePopulator(BeanStringPopulator<?> typePopulator){

		TYPE_POPULATORS.put(typePopulator.getType(), typePopulator);
	}

	@SuppressWarnings("unchecked")
	public static <T> BeanStringPopulator<T> getBeanStringPopulator(Class<T> clazz){

		return (BeanStringPopulator<T>) TYPE_POPULATORS.get(clazz);
	}

	public static Collection<BeanStringPopulator<?>> getBeanStringPopulators(){

		return Collections.unmodifiableCollection(TYPE_POPULATORS.values());
	}
}
