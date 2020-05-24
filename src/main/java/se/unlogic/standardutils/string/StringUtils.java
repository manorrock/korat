/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.string;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import se.unlogic.standardutils.readwrite.ReadWriteUtils;
import se.unlogic.standardutils.reflection.ReflectionUtils;

public class StringUtils {

	private static final Charset UTF_8_CHARSET = Charset.forName("UTF-8");

	private static final char[] HEX_ARRAY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String toHexString(byte[] bytes) {

		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v / 16];
			hexChars[j * 2 + 1] = HEX_ARRAY[v % 16];
		}
		return new String(hexChars);
	}

	public static String parseUTF8(String encodedString) {
		
		if (encodedString == null) {
			return null;
		}

		//TODO should probably specify fromCharset=ISO-8859-1 to getBytes
		return new String(encodedString.getBytes(), UTF_8_CHARSET);
	}

	public static String parseEncodedString(String encodedString, Charset charset) {
		
		if (encodedString == null) {
			return null;
		}

		return new String(encodedString.getBytes(), charset);
	}	
	
	//TODO move
	public static boolean isValidUUID(String uuidstring) {

		try {
			UUID.fromString(uuidstring);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	//TODO move
	public static boolean isValidURL(String urlstring) {

		try {
			new URL(urlstring);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

	public static String toQuotedCommaSeparatedString(List<? extends Object> list) {

		String arrayString = Arrays.deepToString(list.toArray());
		arrayString = arrayString.substring(1, arrayString.length() - 1);
		arrayString = "\"" + arrayString.replaceAll(", ", "\", \"") + "\"";
		return arrayString;
	}

	public static String toQuotedCommaSeparatedString(Object[] array) {

		String arrayString = Arrays.deepToString(array);
		arrayString = arrayString.substring(1, arrayString.length() - 1);
		arrayString = "\"" + arrayString.replaceAll(", ", "\", \"") + "\"";
		return arrayString;
	}

	public static String toCommaSeparatedString(Collection<? extends Object> list) {

		String arrayString = Arrays.deepToString(list.toArray());
		return arrayString.substring(1, arrayString.length() - 1);
	}

	public static String toCommaSeparatedString(Object[] array) {

		String arrayString = Arrays.deepToString(array);
		return arrayString.substring(1, arrayString.length() - 1);
	}

	public static String toCommaSeparatedString(List<? extends Object> list, Field field) throws IllegalArgumentException, IllegalAccessException {

		ReflectionUtils.fixFieldAccess(field);

		StringBuilder arrayString = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {

			Object value = field.get(list.get(i));

			arrayString.append(value + ",");

		}

		return arrayString.substring(0, arrayString.length() - 1);

	}

	public static String readFileAsString(String filePath) throws java.io.IOException {

		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			fileData.append(buf, 0, numRead);
		}
		reader.close();
		return fileData.toString();
	}

	public static String readFileAsString(File file) throws java.io.IOException {

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			StringWriter stringWriter = new StringWriter();

			ReadWriteUtils.transfer(bufferedReader, stringWriter);

			return stringWriter.toString();

		} finally {

			ReadWriteUtils.closeReader(bufferedReader);
			ReadWriteUtils.closeReader(fileReader);
		}
	}

	public static String readStreamAsString(InputStream inputStream) throws java.io.IOException {

		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			StringWriter stringWriter = new StringWriter();

			ReadWriteUtils.transfer(bufferedReader, stringWriter);

			return stringWriter.toString();

		} finally {

			ReadWriteUtils.closeReader(bufferedReader);
			ReadWriteUtils.closeReader(inputStreamReader);
		}
	}

	public static String readStreamAsString(InputStream inputStream, String charset) throws java.io.IOException {

		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			inputStreamReader = new InputStreamReader(inputStream, charset);
			bufferedReader = new BufferedReader(inputStreamReader);

			StringWriter stringWriter = new StringWriter();

			ReadWriteUtils.transfer(bufferedReader, stringWriter);

			return stringWriter.toString();

		} finally {

			ReadWriteUtils.closeReader(bufferedReader);
			ReadWriteUtils.closeReader(inputStreamReader);
		}
	}
	
	public static String readStreamAsString(InputStream inputStream, Charset charset) throws java.io.IOException {

		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			inputStreamReader = new InputStreamReader(inputStream, charset);
			bufferedReader = new BufferedReader(inputStreamReader);

			StringWriter stringWriter = new StringWriter();

			ReadWriteUtils.transfer(bufferedReader, stringWriter);

			return stringWriter.toString();

		} finally {

			ReadWriteUtils.closeReader(bufferedReader);
			ReadWriteUtils.closeReader(inputStreamReader);
		}
	}
	
	public static boolean isEmpty(String string) {

		if (string == null) {
			return true;
		} else if (string.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static String substring(String string, int maxChars) {

		if (string.length() > maxChars) {
			return string.substring(0, maxChars - 1);
		}

		return string;
	}
	
	public static String substring(String string, int maxChars, String suffix) {

		if (string.length() > maxChars) {
			return string.substring(0, maxChars - 1 - suffix.length()) + suffix;
		}

		return string;
	}

	public static String toLogFormat(String string, int maxLength) {

		if (string != null) {

			String returnString = substring(string, maxLength, "...");

			return returnString.replace("\n", " ").replace("\r", " ").replace("\t", " ");
		}

		return string;
	}

	public static String toSentenceCase(String string) {

		return string.substring(0, 1).toUpperCase() + string.toLowerCase().substring(1);
	}

	public static String toFirstLetterUppercase(String string) {

		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public static String toFirstLetterLowercase(String string) {

		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}

	public static String repeatString(String string, int repetitionCount) {

		StringBuilder stringBuilder = new StringBuilder();

		repeatString(string, repetitionCount, stringBuilder);

		return stringBuilder.toString();
	}

	public static void repeatString(String string, int repetitionCount, StringBuilder stringBuilder) {

		if (repetitionCount >= 1) {

			for (int i = 1; i <= repetitionCount; i++) {

				stringBuilder.append(string);
			}
		}
	}

	/**
	 * Takes a string and splits it on linebreaks (\n) and also removes any linebreak (\n) and carriage return (\r) characters from the string segments
	 * 
	 * @param string the string to be split
	 * @return String array containing the split segments of the string
	 */
	public static ArrayList<String> splitOnLineBreak(String string, boolean removeDuplicates) {

		if (!StringUtils.isEmpty(string)) {

			ArrayList<String> stringList = new ArrayList<String>();

			String[] lines = string.split("\n");

			for (String line : lines) {

				line = line.replace("\n", "");
				line = line.replace("\r", "");

				if (!StringUtils.isEmpty(line) && (!removeDuplicates || !stringList.contains(line))) {

					stringList.add(line);
				}
			}

			if (!stringList.isEmpty()) {

				return stringList;
			}
		}

		return null;
	}

	public static int countOccurrences(String string, String snippet) {

		int count = 0;
		int index = 0;

		while ((index = string.indexOf(snippet, index)) != -1) {
			++index;
			++count;
		}

		return count;
	}

	public static String substringBefore(String string, String snippet) {

		int index = string.indexOf(snippet);

		if (index == -1) {

			return string;

		} else if (index == 0) {

			return "";
		}

		return string.substring(0, index);
	}

	public static String substringAfter(String string, String snippet) {

		return substringAfter(string, snippet, string.indexOf(snippet));
	}

	public static String substringAfterLast(String string, String snippet) {

		return substringAfter(string, snippet, string.lastIndexOf(snippet));
	}

	protected static String substringAfter(String string, String snippet, int index) {

		if (index == -1 || (index + snippet.length()) > string.length()) {

			return "";
		}

		return string.substring(index + snippet.length());
	}

	public static String substringBetween(String string, String startSnippet, String endSnippet) {

		return substringBefore(substringAfter(string, startSnippet), endSnippet);
	}	
	
	public static InputStream getInputStream(String text) {

		return new ByteArrayInputStream(text.getBytes());
	}

	public static InputStream getInputStream(String text, Charset charset) {

		return new ByteArrayInputStream(text.getBytes(charset));
	}
	
	public static String replaceUTF8Quotes(String text) {

		text = text.replace("”", "\"");
		text = text.replace("“", "\"");
		text = text.replace("‘", "'");
		text = text.replace("’", "'");

		return text;
	}

	public static boolean isEmpty(String... strings) {

		if (strings == null) {

			return true;
		}

		for (String string : strings) {

			if (!StringUtils.isEmpty(string)) {

				return false;
			}
		}

		return true;
	}

	public static boolean compare(String string1, String string2) {

		return (string1 == null ? string2 == null : string1.equals(string2));
	}

	public static String trim(String string) {

		if (string == null) {

			return null;
		}
		
		string = string.trim();
		
		if (string.length() == 0) {
			
			return null;
		}
		
		return string;
	}
	
	public static String decodeString(String encodedString, String encoding) throws CharacterCodingException{
		
		Charset charset = Charset.forName(encoding);
		
		CharsetDecoder charsetDecoder = charset.newDecoder();
		
		ByteBuffer byteBuffer = ByteBuffer.wrap(encodedString.getBytes());
		
		CharBuffer charBuffer = charsetDecoder.decode(byteBuffer);
		
		return charBuffer.toString();
	}
	
	public static String toString(Object o){
		
		if(o == null){
			
			return null;
		}
		
		return o.toString();
	}
	
	public static String unescapeJavaString(String st) {
		 
	    StringBuilder sb = new StringBuilder(st.length());
	 
	    for (int i = 0; i < st.length(); i++) {
	        char ch = st.charAt(i);
	        if (ch == '\\') {
	            char nextChar = (i == st.length() - 1) ? '\\' : st
	                    .charAt(i + 1);
	            // Octal escape?
	            if (nextChar >= '0' && nextChar <= '7') {
	                String code = "" + nextChar;
	                i++;
	                if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
	                        && st.charAt(i + 1) <= '7') {
	                    code += st.charAt(i + 1);
	                    i++;
	                    if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
	                            && st.charAt(i + 1) <= '7') {
	                        code += st.charAt(i + 1);
	                        i++;
	                    }
	                }
	                sb.append((char) Integer.parseInt(code, 8));
	                continue;
	            }
	            switch (nextChar) {
	            case '\\':
	                ch = '\\';
	                break;
	            case 'b':
	                ch = '\b';
	                break;
	            case 'f':
	                ch = '\f';
	                break;
	            case 'n':
	                ch = '\n';
	                break;
	            case 'r':
	                ch = '\r';
	                break;
	            case 't':
	                ch = '\t';
	                break;
	            case '\"':
	                ch = '\"';
	                break;
	            case '\'':
	                ch = '\'';
	                break;
	            // Hex Unicode: u????
	            case 'u':
	                if (i >= st.length() - 5) {
	                    ch = 'u';
	                    break;
	                }
	                int code = Integer.parseInt(
	                        "" + st.charAt(i + 2) + st.charAt(i + 3)
	                                + st.charAt(i + 4) + st.charAt(i + 5), 16);
	                sb.append(Character.toChars(code));
	                i += 5;
	                continue;
	            }
	            i++;
	        }
	        sb.append(ch);
	    }
	    return sb.toString();
	}
}
