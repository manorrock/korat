package se.unlogic.standardutils.string;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

public class FixedPositionFileWriter implements Closeable {

	private final Writer writer;
	private final String lineSeparator;

	public FixedPositionFileWriter(Writer writer, String lineSeparator) {

		super();
		this.writer = writer;

		if (lineSeparator == null) {

			this.lineSeparator = System.lineSeparator();

		} else {

			this.lineSeparator = lineSeparator;
		}
	}

	public void writePaddingAfter(String value, char padding, int fixedLength) throws IOException {

		if (value.length() > fixedLength) {

			throw new RuntimeException("The length of the value exceeds the fixed length of " + fixedLength);
		}

		writer.write(value);

		int requiredPadding = fixedLength - value.length();

		if (requiredPadding > 0) {

			while (requiredPadding > 0) {

				writer.write(padding);

				requiredPadding--;
			}
		}
	}

	public void writePaddingBefore(String value, char padding, int fixedLength) throws IOException {

		if (value.length() > fixedLength) {

			throw new RuntimeException("The length of the value exceeds the fixed length of " + fixedLength);
		}

		int requiredPadding = fixedLength - value.length();

		if (requiredPadding > 0) {

			while (requiredPadding > 0) {

				writer.write(padding);

				requiredPadding--;
			}
		}		
		
		writer.write(value);
	}
	
	public void write(char padding, int fixedLength) throws IOException {

		while (fixedLength > 0) {

			writer.write(padding);

			fixedLength--;
		}
	}

	public void addLineBreak() throws IOException {

		writer.write(lineSeparator);
	}

	public void write(String str) throws IOException {

		writer.write(str);
	}
	
	@Override
	public void close() throws IOException {

		writer.close();
	}
}
