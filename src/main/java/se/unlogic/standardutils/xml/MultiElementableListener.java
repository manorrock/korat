package se.unlogic.standardutils.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MultiElementableListener<T> implements ElementableListener<T> {

	private ArrayList<ElementableListener<T>> listeners;
	
	public MultiElementableListener() {
		
		listeners = new ArrayList<>();
	}
	
	public MultiElementableListener(int size) {
		
		listeners = new ArrayList<>(size);
	}
	
	public void addElementableListener(ElementableListener<T> listener) {
		
		listeners.add(listener);
	}
	
	@Override
	public void elementGenerated(Document doc, Element element, T object) {

		for(ElementableListener<T> listener : listeners) {
			
			listener.elementGenerated(doc, element, object);
		}
	}

}
