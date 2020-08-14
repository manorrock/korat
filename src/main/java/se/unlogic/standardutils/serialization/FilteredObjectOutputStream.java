package se.unlogic.standardutils.serialization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import se.unlogic.standardutils.collections.CollectionUtils;

public class FilteredObjectOutputStream extends ObjectOutputStream {

	private final List<String> allowedClasses;
	private final Map<Class<?>, Class<?>> replacementClasses;

	public FilteredObjectOutputStream(OutputStream out, Map<Class<?>, Class<?>> replacementClasses, List<Class<?>> allowedClasses) throws IOException {

		super(out);

		if (CollectionUtils.isEmpty(allowedClasses)) {

			throw new NullPointerException("Allowed classes cannot be null or empty");
		}

		this.allowedClasses = new ArrayList<String>(allowedClasses.size());

		for (Class<?> clazz : allowedClasses) {

			this.allowedClasses.add(clazz.getName());
		}
		
		this.replacementClasses = replacementClasses;

		this.enableReplaceObject(true);
	}

	public FilteredObjectOutputStream(OutputStream out, Map<Class<?>, Class<?>> replacementClasses, Class<?>... allowedClasses) throws IOException {

		super(out);

		if (allowedClasses == null || allowedClasses.length == 0) {

			throw new NullPointerException("Allowed classes cannot be null or empty");
		}

		this.allowedClasses = new ArrayList<String>(allowedClasses.length);

		for (Class<?> clazz : allowedClasses) {

			this.allowedClasses.add(clazz.getName());
		}
		
		this.replacementClasses = replacementClasses;

		this.enableReplaceObject(true);
	}

	@Override
	protected Object replaceObject(Object obj) throws IOException {
		
		Class<?> objClass = obj.getClass();
		
		if (!allowedClasses.contains(objClass.getName())) {

			if (replacementClasses != null) {
				
				Class<?> replacementKey = objClass;
				Class<?> replacementClass = replacementClasses.get(objClass);
				
				if (replacementClass == null) {
					
					for (Entry<Class<?>, Class<?>> entry : replacementClasses.entrySet()) {
						
						if (entry.getKey().isAssignableFrom(objClass)) {
							
							replacementKey = entry.getKey();
							replacementClass = entry.getValue();
						}
					}
				}
				
				if (replacementClass != null) {
					
//					System.out.println("Replacing " + objClass + " with " + replacementClass);
					
					try {
						Constructor<?> constructor = replacementClass.getDeclaredConstructor(replacementKey);
						
						return constructor.newInstance(obj);
						
					} catch (Exception e) {
						
						throw new RuntimeException("Error replacing " + objClass + " with " + replacementClass, e);
					}
				}
			}
			
//			System.out.println("skipping " + objClass.getName());

			return null;
		}

		return obj;
	}

}
