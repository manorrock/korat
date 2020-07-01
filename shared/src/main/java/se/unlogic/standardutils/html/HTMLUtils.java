package se.unlogic.standardutils.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import se.unlogic.standardutils.io.CloseUtils;
import se.unlogic.standardutils.string.StringUtils;


public class HTMLUtils {

	private static final LinkedHashMap<String, String> HTML4_ESCAPE_CHARACTER_MAP = new LinkedHashMap<String, String>();
	
	static {
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		
		try {
			inputStream = HTMLUtils.class.getResourceAsStream("html4-character-entities.properties");
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			String line;
			String[] split;
			
			while((line = bufferedReader.readLine()) != null){
				
				split = line.split("=");
				
				HTML4_ESCAPE_CHARACTER_MAP.put(StringUtils.unescapeJavaString(split[1]), split[0]);
			}
			
		} catch (IOException e) {
			
			throw new RuntimeException(e);
			
		} finally {
			CloseUtils.close(bufferedReader);
			CloseUtils.close(inputStream);
		}
	}

	public static String escapeHTML(String sequence) {

		return replaceCharacters(sequence, HTML4_ESCAPE_CHARACTER_MAP, true);
	}


	public static String unEscapeHTML(String sequence) {

		return replaceCharacters(sequence, HTML4_ESCAPE_CHARACTER_MAP, false);
	}

	private static String replaceCharacters(String sequence, Map<String, String> map, boolean reverse) {

		if (reverse) {

			for (Entry<String, String> entry : map.entrySet()) {
				
				sequence = sequence.replace(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}

		} else {

			for (Entry<String, String> entry : map.entrySet()) {
				
				sequence = sequence.replace(String.valueOf(entry.getValue()), String.valueOf(entry.getKey()));
			}
		}

		return sequence;
	}

	public static String removeHTMLTags(String sequence) {

		if (sequence == null) {

			return null;
		}

		return sequence.replaceAll("<[^>]*>", "");
	}
	
	public static String replaceLineBreaksWithParagraph(String string) {
		
		return replaceLineBreaksWithParagraph(string, false, false);
	}
	
	public static String replaceLineBreaksWithParagraph(String string, boolean replaceLinks, boolean targetBlank) {
		
		if (!StringUtils.isEmpty(string)) {
			ArrayList<String> lines = StringUtils.splitOnLineBreak(string, false);
			
			if (lines != null) {
				StringBuilder sb = new StringBuilder();
				
				for (String line : lines) {
					sb.append("<p>");
					
					if (replaceLinks) {
						sb.append(replaceURLsInString(line, targetBlank));
					}
					else {
						sb.append(line);
					}
					
					sb.append("</p>");
				}
				
				return sb.toString();
			}
		}
		
		return null;
	}
	
	public static String replaceURLsInString(String text, boolean setTargetBlank) {
		
		if (text == null) {
			return null;
		}
		
		if (!text.contains("http://") && !text.contains("www.") && !text.contains("https://") && !text.contains("ftp://")) {
			return text;
		}
		
		String[] parts = text.split("\\s+");
		
		StringBuilder sb = new StringBuilder();

		for (String part : parts) {
			if (part.startsWith("http://") || part.startsWith("www.") || part.startsWith("https://") || part.startsWith("ftp://")) {
				sb.append("<a href=\"" + part + "\"");
				
				if (setTargetBlank) {
					sb.append(" target=\"_blank\"");
				}
				
				sb.append(">" + part + "</a> ");
			}
			else {
				sb.append(part + " ");
			}
		}

		return sb.toString();
	}
}
