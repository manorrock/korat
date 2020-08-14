package se.unlogic.standardutils.collections;

import java.util.Comparator;

import se.unlogic.standardutils.beans.Named;

public class CaseInsensitiveNameComparator implements Comparator<Named> {

	private static final CaseInsensitiveNameComparator INSTANCE = new CaseInsensitiveNameComparator();
	
	@Override
	public int compare(Named n1, Named n2) {

		return n1.getName().compareToIgnoreCase(n2.getName());
	}

	public static CaseInsensitiveNameComparator getInstance(){
		
		return INSTANCE;
	}
}
