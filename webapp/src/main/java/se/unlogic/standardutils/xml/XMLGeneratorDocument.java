package se.unlogic.standardutils.xml;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;


/**
 * This class is used to wrap a {@link Document} in order to add listeners for the {@link XMLGenerator} class in order to override the output from {@link Elementable} classes. 
 * 
 * @author Robert "Unlogic" Olofsson
 *
 */
public class XMLGeneratorDocument implements Document{

	protected final Document document;
	
	@SuppressWarnings("rawtypes")
	protected HashMap<Class, ElementableListener> elementableListenerMap;
		
	@SuppressWarnings("rawtypes")
	protected LinkedHashMap<Class, ElementableListener> assignableElementableListenerMap;
	
	protected List<Field> ignoredFields;
	
	public XMLGeneratorDocument(Document document) {

		super();
		this.document = document;
	}

	@SuppressWarnings("rawtypes")
	public <T extends Elementable> void addElementableListener(Class<T> targetClass, ElementableListener<? super T> listener){
		
		if(elementableListenerMap == null){
			
			elementableListenerMap = new HashMap<Class, ElementableListener>();
		}
		
		elementableListenerMap.put(targetClass, listener);
	}
	
	@SuppressWarnings("rawtypes")
	public <T extends Elementable> void addAssignableElementableListener(Class<T> targetClass, ElementableListener<? super T> listener){
		
		if(assignableElementableListenerMap == null){
			
			assignableElementableListenerMap = new LinkedHashMap<Class, ElementableListener>();
		}
		
		assignableElementableListenerMap.put(targetClass, listener);
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> ElementableListener<T> getElementableListener(Class<T> targetClass){
		
		if(assignableElementableListenerMap != null){
			
			for(Entry<Class, ElementableListener> entry : assignableElementableListenerMap.entrySet()){
				
				if(entry.getKey().isAssignableFrom(targetClass)){
					
					return entry.getValue();
				}
			}
		}
		
		if(elementableListenerMap == null){
			
			return null;
		}
		
		return elementableListenerMap.get(targetClass);
	}
	
	public void addIgnoredField(Field field){
		
		if(this.ignoredFields == null){
			
			this.ignoredFields = new ArrayList<Field>();
		}
		
		this.ignoredFields.add(field);
	}
	
	public void addIgnoredField(List<Field> fields) {

		if(this.ignoredFields == null){
			
			this.ignoredFields = new ArrayList<Field>();
		}
		
		ignoredFields.addAll(fields);
	}
	
	public void addIgnoredFields(Field... fields){
		
		if(this.ignoredFields == null){
			
			this.ignoredFields = new ArrayList<Field>();
		}
		
		this.ignoredFields.addAll(Arrays.asList(fields));
	}
	
	public boolean isIgnoredField(Field field){
		
		return ignoredFields != null && ignoredFields.contains(field);
	}
	
	public Document getDocument() {
		
		return document;
	}	
	
	@Override
	public DocumentType getDoctype() {

		return document.getDoctype();
	}

	@Override
	public DOMImplementation getImplementation() {

		return document.getImplementation();
	}

	@Override
	public Element getDocumentElement() {

		return document.getDocumentElement();
	}

	@Override
	public Element createElement(String tagName) throws DOMException {

		return document.createElement(tagName);
	}

	@Override
	public DocumentFragment createDocumentFragment() {

		return document.createDocumentFragment();
	}

	@Override
	public Text createTextNode(String data) {

		return document.createTextNode(data);
	}

	@Override
	public Comment createComment(String data) {

		return document.createComment(data);
	}

	@Override
	public CDATASection createCDATASection(String data) throws DOMException {

		return document.createCDATASection(data);
	}

	@Override
	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {

		return document.createProcessingInstruction(target, data);
	}

	@Override
	public Attr createAttribute(String name) throws DOMException {

		return document.createAttribute(name);
	}

	@Override
	public String getNodeName() {

		return document.getNodeName();
	}

	@Override
	public String getNodeValue() throws DOMException {

		return document.getNodeValue();
	}

	@Override
	public EntityReference createEntityReference(String name) throws DOMException {

		return document.createEntityReference(name);
	}

	@Override
	public void setNodeValue(String nodeValue) throws DOMException {

		document.setNodeValue(nodeValue);
	}

	@Override
	public short getNodeType() {

		return document.getNodeType();
	}

	@Override
	public Node getParentNode() {

		return document.getParentNode();
	}

	@Override
	public NodeList getChildNodes() {

		return document.getChildNodes();
	}

	@Override
	public Node getFirstChild() {

		return document.getFirstChild();
	}

	@Override
	public NodeList getElementsByTagName(String tagname) {

		return document.getElementsByTagName(tagname);
	}

	@Override
	public Node getLastChild() {

		return document.getLastChild();
	}

	@Override
	public Node getPreviousSibling() {

		return document.getPreviousSibling();
	}

	@Override
	public Node getNextSibling() {

		return document.getNextSibling();
	}

	@Override
	public NamedNodeMap getAttributes() {

		return document.getAttributes();
	}

	@Override
	public Node importNode(Node importedNode, boolean deep) throws DOMException {

		return document.importNode(importedNode, deep);
	}

	@Override
	public Document getOwnerDocument() {

		return document.getOwnerDocument();
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {

		return document.insertBefore(newChild, refChild);
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {

		return document.replaceChild(newChild, oldChild);
	}

	@Override
	public Node removeChild(Node oldChild) throws DOMException {

		return document.removeChild(oldChild);
	}

	@Override
	public Node appendChild(Node newChild) throws DOMException {

		return document.appendChild(newChild);
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {

		return document.createElementNS(namespaceURI, qualifiedName);
	}

	@Override
	public boolean hasChildNodes() {

		return document.hasChildNodes();
	}

	@Override
	public Node cloneNode(boolean deep) {

		return document.cloneNode(deep);
	}

	@Override
	public void normalize() {

		document.normalize();
	}

	@Override
	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {

		return document.createAttributeNS(namespaceURI, qualifiedName);
	}

	@Override
	public boolean isSupported(String feature, String version) {

		return document.isSupported(feature, version);
	}

	@Override
	public String getNamespaceURI() {

		return document.getNamespaceURI();
	}

	@Override
	public String getPrefix() {

		return document.getPrefix();
	}

	@Override
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {

		return document.getElementsByTagNameNS(namespaceURI, localName);
	}

	@Override
	public void setPrefix(String prefix) throws DOMException {

		document.setPrefix(prefix);
	}

	@Override
	public Element getElementById(String elementId) {

		return document.getElementById(elementId);
	}

	@Override
	public String getInputEncoding() {

		return document.getInputEncoding();
	}

	@Override
	public String getXmlEncoding() {

		return document.getXmlEncoding();
	}

	@Override
	public boolean getXmlStandalone() {

		return document.getXmlStandalone();
	}

	@Override
	public String getLocalName() {

		return document.getLocalName();
	}

	@Override
	public void setXmlStandalone(boolean xmlStandalone) throws DOMException {

		document.setXmlStandalone(xmlStandalone);
	}

	@Override
	public boolean hasAttributes() {

		return document.hasAttributes();
	}

	@Override
	public String getBaseURI() {

		return document.getBaseURI();
	}

	@Override
	public String getXmlVersion() {

		return document.getXmlVersion();
	}

	@Override
	public short compareDocumentPosition(Node other) throws DOMException {

		return document.compareDocumentPosition(other);
	}

	@Override
	public void setXmlVersion(String xmlVersion) throws DOMException {

		document.setXmlVersion(xmlVersion);
	}

	@Override
	public String getTextContent() throws DOMException {

		return document.getTextContent();
	}

	@Override
	public boolean getStrictErrorChecking() {

		return document.getStrictErrorChecking();
	}

	@Override
	public void setStrictErrorChecking(boolean strictErrorChecking) {

		document.setStrictErrorChecking(strictErrorChecking);
	}

	@Override
	public void setTextContent(String textContent) throws DOMException {

		document.setTextContent(textContent);
	}

	@Override
	public String getDocumentURI() {

		return document.getDocumentURI();
	}

	@Override
	public void setDocumentURI(String documentURI) {

		document.setDocumentURI(documentURI);
	}

	@Override
	public Node adoptNode(Node source) throws DOMException {

		return document.adoptNode(source);
	}

	@Override
	public boolean isSameNode(Node other) {

		return document.isSameNode(other);
	}

	@Override
	public String lookupPrefix(String namespaceURI) {

		return document.lookupPrefix(namespaceURI);
	}

	@Override
	public boolean isDefaultNamespace(String namespaceURI) {

		return document.isDefaultNamespace(namespaceURI);
	}

	@Override
	public String lookupNamespaceURI(String prefix) {

		return document.lookupNamespaceURI(prefix);
	}

	@Override
	public boolean isEqualNode(Node arg) {

		return document.isEqualNode(arg);
	}

	@Override
	public DOMConfiguration getDomConfig() {

		return document.getDomConfig();
	}

	@Override
	public void normalizeDocument() {

		document.normalizeDocument();
	}

	@Override
	public Object getFeature(String feature, String version) {

		return document.getFeature(feature, version);
	}

	@Override
	public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {

		return document.renameNode(n, namespaceURI, qualifiedName);
	}

	@Override
	public Object setUserData(String key, Object data, UserDataHandler handler) {

		return document.setUserData(key, data, handler);
	}

	@Override
	public Object getUserData(String key) {

		return document.getUserData(key);
	}

	
	public List<Field> getIgnoredFields() {
	
		return ignoredFields;
	}

	
	public void setIgnoredFields(List<Field> ignoredFields) {
	
		this.ignoredFields = ignoredFields;
	}
}
