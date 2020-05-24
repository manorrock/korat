/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.xsl;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

public class FileXSLTransformer extends BaseXSLTransformer {

	private final File file;
	private final URIResolver uriResolver;
	private final boolean useCache;

	public FileXSLTransformer(File f, boolean useCache) throws TransformerConfigurationException {

		this.file = f;
		this.useCache = useCache;
		this.uriResolver = null;
		this.cacheStyleSheet(f);
	}

	public FileXSLTransformer(File file, URIResolver uriResolver, boolean useCache) throws TransformerConfigurationException {

		this.file = file;
		this.useCache = useCache;
		this.uriResolver = uriResolver;
		this.cacheStyleSheet(file);
	}

	@Override
	public void reloadStyleSheet() throws TransformerConfigurationException {

		if(useCache){
			
			this.templates = TemplateCache.getTemplates(new TemplateDescriptor(file.toURI(), uriResolver));
			
		}else{
			
			Source xsltSource = new StreamSource(file);
			TransformerFactory transFact = TransformerFactory.newInstance();

			if (uriResolver != null) {
				transFact.setURIResolver(uriResolver);
			}

			this.templates = transFact.newTemplates(xsltSource);
		}
	}
	
	private void cacheStyleSheet(File f) throws TransformerConfigurationException {

		Source xsltSource = new StreamSource(f);
		TransformerFactory transFact = TransformerFactory.newInstance();

		if (uriResolver != null) {
			transFact.setURIResolver(uriResolver);
		}

		this.templates = transFact.newTemplates(xsltSource);
	}

	@Override
	public String toString() {

		return "CachedXSLTFile: " + file;
	}
}
