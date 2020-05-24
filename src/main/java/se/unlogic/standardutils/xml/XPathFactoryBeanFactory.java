package se.unlogic.standardutils.xml;

import javax.xml.xpath.XPathFactory;

import se.unlogic.standardutils.factory.BeanFactory;


public class XPathFactoryBeanFactory implements BeanFactory<XPathFactory> {

	@Override
	public XPathFactory newInstance() {

		return XPathFactory.newInstance();
	}

}
