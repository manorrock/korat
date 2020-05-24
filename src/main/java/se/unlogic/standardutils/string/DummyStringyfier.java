package se.unlogic.standardutils.string;



public final class DummyStringyfier implements Stringyfier<Object> {

	@Override
	public String format(Object bean) {

		throw new RuntimeException("This is a dummy class and this method should never be called!");
	}
}
