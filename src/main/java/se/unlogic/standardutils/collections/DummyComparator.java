package se.unlogic.standardutils.collections;

import java.util.Comparator;


public class DummyComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {

		throw new RuntimeException("Operation not suppored");
	}

}
