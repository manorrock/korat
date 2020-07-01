/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.json;

public class JsonLeaf implements JsonNode {

	private static final long serialVersionUID = 1198871458505471824L;
	private String value;

	public JsonLeaf(Object value) {

		this(value, true);
	}

	public JsonLeaf(Object value, boolean appendQuotes) {

		if (value == null) {
			this.value = "null";

		} else if (value instanceof Number || value instanceof Boolean) {
			this.value = value.toString();

		} else {
			this.value = getEscapedValue(value.toString(), appendQuotes);
		}
	}

	@Override
	public String toJson() {

		return value;
	}

	@Override
	public void toJson(StringBuilder stringBuilder) {

		stringBuilder.append(value);
	}

	// Code from codehaus/jettision Licensed under Apache 2.0
	// Relevant RFC http://www.ietf.org/rfc/rfc4627.txt
	public static String getEscapedValue(String value, boolean appendQuotes) {

		int len = value.length();

		StringBuilder sb = new StringBuilder(len + (appendQuotes ? 2 : 0));

		if (appendQuotes) {
			sb.append("\"");
		}

		for (int i = 0; i < len; i += 1) {
			char c = value.charAt(i);

			switch (c) {
				case '\\':
				case '"':
				case '/':
					sb.append('\\');
					sb.append(c);
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\r':
					sb.append("\\r");
					break;
				default:
					if (c < ' ') {
						String t = "000" + Integer.toHexString(c);
						sb.append("\\u" + t.substring(t.length() - 4));
					} else {
						sb.append(c);
					}
			}
		}

		if (appendQuotes) {
			sb.append("\"");
		}

		return sb.toString();
	}
}