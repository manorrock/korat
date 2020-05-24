package se.unlogic.standardutils.collections;

import java.util.Comparator;

import se.unlogic.standardutils.beans.Named;

public class NameComparator implements Comparator<Named> {

	private static final NameComparator INSTANCE = new NameComparator();
	
	@Override
	public int compare(Named n1, Named n2) {

		return n1.getName().compareTo(n2.getName());
	}

	public static NameComparator getInstance(){
		
		return INSTANCE;
	}
}
