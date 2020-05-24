/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import se.unlogic.standardutils.numbers.NumberUtils;
import se.unlogic.standardutils.settings.SettingNode;
import se.unlogic.standardutils.string.StringUtils;

public class XMLParser implements SettingNode {

	private final Element element;
	private final XPath xpath;

	public XMLParser(String path) throws SAXException, IOException, ParserConfigurationException {

		Document doc = XMLUtils.parseXMLFile(path, false,false);
		this.element = doc.getDocumentElement();

		this.xpath = PooledXPathFactory.newXPath();
	}

	public XMLParser(Document doc) {

		this.element = doc.getDocumentElement();

		this.xpath = PooledXPathFactory.newXPath();
	}

	public XMLParser(Element element) {

		this.element = element;

		this.xpath = PooledXPathFactory.newXPath();
	}

	public XMLParser(File xmlFile) throws SAXException, IOException, ParserConfigurationException {

		Document doc = XMLUtils.parseXMLFile(xmlFile, false, false);
		this.element = doc.getDocumentElement();

		this.xpath = PooledXPathFactory.newXPath();
	}

	@Override
	public Boolean getBoolean(String expression) {

		String value = this.getString(expression);
		
		if(StringUtils.isEmpty(value)){
			
			return null;
		}
		
		return Boolean.valueOf(value);

	}

	@Override
	public boolean getPrimitiveBoolean(String expression) {

		return Boolean.parseBoolean(this.getString(expression));

	}

	@Override
	public Double getDouble(String expression) {

		String value = this.getString(expression);

		if(value != null){
			return NumberUtils.toDouble(value);
		}

		return null;

	}

	@Override
	public List<Double> getDoubles(String expression) {

		NodeList nodes = this.getNodeList(expression);

		List<Double> doubles = new ArrayList<Double>();

		for (int i = 0; i < nodes.getLength(); i++) {

			String value = nodes.item(i).getTextContent();

			if(value != null){
				Double numberValue = NumberUtils.toDouble(value);

				if(numberValue != null){
					doubles.add(numberValue);
				}
			}
		}

		return doubles;

	}

	@Override
	public int getInt(String expression) {

		String value = this.getString(expression);

		if(value != null && NumberUtils.isInt(value)){
			return NumberUtils.toInt(value);
		}

		return 0;

	}

	@Override
	public Integer getInteger(String expression) {

		String value = this.getString(expression);

		if(value != null){
			return NumberUtils.toInt(value);
		}

		return null;

	}

	@Override
	public List<Integer> getIntegers(String expression) {

		NodeList nodes = this.getNodeList(expression);

		List<Integer> integers = new ArrayList<Integer>(nodes.getLength());

		for (int i = 0; i < nodes.getLength(); i++) {

			String value = nodes.item(i).getTextContent();

			if(value != null){
				Integer numberValue = NumberUtils.toInt(value);

				if(numberValue != null){
					integers.add(numberValue);
				}
			}
		}

		return integers;

	}

	@Override
	public Long getLong(String expression) {

		String value = this.getString(expression);

		if(value != null){
			return NumberUtils.toLong(value);
		}

		return null;

	}

	@Override
	public List<Long> getLongs(String expression) {

		NodeList nodes = this.getNodeList(expression);

		List<Long> longs = new ArrayList<Long>();

		for (int i = 0; i < nodes.getLength(); i++) {

			String value = nodes.item(i).getTextContent();

			if(value != null){
				Long numberValue = NumberUtils.toLong(value);

				if(numberValue != null){
					longs.add(numberValue);
				}
			}
		}

		return longs;

	}

	@Override
	public XMLParser getNode(String expression) {

		Element element = this.getElement(expression, false);

		if(element == null){

			return null;
		}

		return new XMLParser(element);

	}
	
	public XMLParser getNode(String expression, boolean disconnect) {

		Element element = this.getElement(expression, disconnect);

		if(element == null){

			return null;
		}

		return new XMLParser(element);

	}

	@Override
	public List<XMLParser> getNodes(String expression) {
		
		return getNodes(expression, false);
	}
	
	public List<XMLParser> getNodes(String expression, boolean disconnect) {

		NodeList nodes = this.getNodeList(expression);

		List<XMLParser> settingNodes = new ArrayList<XMLParser>(nodes.getLength());

		for(int i = 0; i < nodes.getLength(); i++){

			Node node = nodes.item(i);
			
			if(disconnect){
				
				node.getParentNode().removeChild(node);
			}
			
			settingNodes.add(new XMLParser((Element)node));
		}

		return settingNodes;

	}
	
	public List<XMLParser> getNodes(XPathExpression expression, boolean disconnect) {

		NodeList nodes = this.getNodeList(expression);

		List<XMLParser> settingNodes = new ArrayList<XMLParser>(nodes.getLength());

		for(int i = 0; i < nodes.getLength(); i++){

			Node node = nodes.item(i);
			
			if(disconnect){
				
				node.getParentNode().removeChild(node);
			}
			
			settingNodes.add(new XMLParser((Element)node));
		}

		return settingNodes;
	}	

	@Override
	public String getString(String expression) {

		try {
			return this.xpath.evaluate(expression, this.element);
		} catch (XPathExpressionException e) {
			return null;
		}

	}
	
	public String getString(XPathExpression expression) {

		try {
			return (String) expression.evaluate(this.element, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			return null;
		}

	}

	@Override
	public List<String> getStrings(String expression) {

		NodeList nodes = this.getNodeList(expression);

		if(nodes != null && nodes.getLength() > 0){

			List<String> strings = new ArrayList<String>(nodes.getLength());

			for (int i = 0; i < nodes.getLength(); i++) {

				strings.add(nodes.item(i).getTextContent());

			}

			return strings;
		}

		return null;
	}

	private NodeList getNodeList(String expression){

		try {
			return (NodeList) this.xpath.evaluate(expression, this.element, XPathConstants.NODESET);
		} catch(XPathExpressionException e) {
			return null;
		}

	}
	
	private NodeList getNodeList(XPathExpression expression){

		try {
			return (NodeList) expression.evaluate(this.element, XPathConstants.NODESET);
		} catch(XPathExpressionException e) {
			return null;
		}

	}	

	private Element getElement(String expression, boolean disconnect){

		try {
			Element element = (Element) this.xpath.evaluate(expression, this.element, XPathConstants.NODE);
			
			if(disconnect && element != null){
				
				element.getParentNode().removeChild(element);
			}
			
			return element;
			
		} catch(XPathExpressionException e) {
			
			return null;
		}
	}

	public String getElementName(){

		return element.getTagName();
	}
}
