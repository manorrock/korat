package se.unlogic.standardutils.string;

import java.nio.charset.Charset;

public class SimpleStringConverter implements StringConverter {
	
	private final Charset fromCharset;
	private final Charset toCharset;

	public SimpleStringConverter(Charset fromCharset, Charset toCharset) {

		this.fromCharset = fromCharset;
		this.toCharset = toCharset;
	}

	@Override
	public String decode(String input) {

		if (input == null) {
			return null;
		}

		return new String(input.getBytes(fromCharset), toCharset);
	}

}
