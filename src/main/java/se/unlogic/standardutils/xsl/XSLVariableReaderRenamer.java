package se.unlogic.standardutils.xsl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XSLVariableReaderRenamer extends XSLVariableReader {
	
	private final XSLVariableReader xslVariableReader;
	private final Map<String, String> replacements;
	
	public XSLVariableReaderRenamer(XSLVariableReader xslVariableReader, Map<String, String> getValueNameReplacements) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, URISyntaxException {
		super((Document) null);
		
		this.xslVariableReader = xslVariableReader;
		this.replacements = getValueNameReplacements;
	}
	
	@Override
	protected List<Document> getSubDocuments(Document doc, List<Document> subDocuments) throws SAXException, IOException, ParserConfigurationException, URISyntaxException, XPathExpressionException {
		
		return null;
	}
	
	@Override
	public String getValue(String name) {
		
		if (replacements.containsKey(name)) {
			
			return xslVariableReader.getValue(replacements.get(name));
		}
		
		return xslVariableReader.getValue(name);
	}
}
