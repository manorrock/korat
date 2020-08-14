package se.unlogic.standardutils.string;

import java.util.Collections;
import java.util.Set;


public class SingleTagSourceFactory<T> implements TagSourceFactory<T>{

	protected Set<String> tags;
	protected String tag;
	
	public SingleTagSourceFactory(String tag){
		
		this.tags = Collections.singleton(tag);
		this.tag = tag;
	}

	@Override
	public <X extends T> TagSource getTagSource(X value) {

		return new SingleTagSource(tags, value.toString());
	}

	@Override
	public Set<String> getTagsSet() {

		return tags;
	}

	@Override
	public String getAvailableTags() {

		return tag;
	}
}
