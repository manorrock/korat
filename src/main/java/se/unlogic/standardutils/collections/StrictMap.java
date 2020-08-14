package se.unlogic.standardutils.collections;

import java.util.Map;


public interface StrictMap<Key, Value> extends Map<Key, Value> {

	@Override
	public Value put(Key key, Value value) throws KeyAlreadyCachedException;

	public Value update(Key key, Value value) throws KeyNotCachedException;

	@Override
	public Value remove(Object key) throws KeyNotCachedException;
}
