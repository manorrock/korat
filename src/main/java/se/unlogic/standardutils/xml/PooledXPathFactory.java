package se.unlogic.standardutils.xml;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import se.unlogic.standardutils.pool.GenericObjectPool;

public class PooledXPathFactory {

	private static final GenericObjectPool<XPathFactory> FACTORY_POOL = new GenericObjectPool<XPathFactory>(new XPathFactoryBeanFactory());
	
	public static XPath newXPath() {

		XPathFactory factory = null;
		
		try{
			factory = FACTORY_POOL.borrowObject();

			return factory.newXPath();

		}finally{

			FACTORY_POOL.returnObject(factory);
		}	
	}
}
