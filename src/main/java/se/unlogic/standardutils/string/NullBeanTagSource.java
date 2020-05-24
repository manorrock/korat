package se.unlogic.standardutils.string;

import java.util.Set;


public class NullBeanTagSource implements TagSource {

	private final Set<String> tagSet;
	
	public NullBeanTagSource(Set<String> tagSet) {

		super();
		this.tagSet = tagSet;
	}

	@Override
	public Set<String> getTags() {

		return tagSet;
	}

	@Override
	public String getTagValue(String tag) {

		return null;
	}

}
