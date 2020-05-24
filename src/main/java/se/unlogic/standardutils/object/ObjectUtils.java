package se.unlogic.standardutils.object;

public class ObjectUtils {

	public static boolean compare(Object o1, Object o2) {
	    
		return (o1 == null ? o2 == null : o1.equals(o2));
	}

	public static boolean isNull(Object... objects) {

		if (objects == null) {
			
			return true;
		}
		
		for (Object object : objects) {
			
			if (object != null) {
				
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean containsNull(Object... objects) {

		if (objects == null) {
			
			return true;
		}
		
		for (Object object : objects) {
			
			if (object == null) {
				
				return true;
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getFirstNotNull(T... objects) {

		if (objects == null) {
			
			return null;
		}
		
		for (T object : objects) {
			
			if (object != null) {
				
				return object;
			}
		}
		
		return null;
	}
}
