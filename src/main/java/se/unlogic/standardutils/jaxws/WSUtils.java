package se.unlogic.standardutils.jaxws;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.ws.BindingProvider;

import se.unlogic.standardutils.io.CloseUtils;
import se.unlogic.standardutils.time.MillisecondTimeUnits;


public class WSUtils {

	public static void setTimeouts(BindingProvider bindingProvider, int connectionTimeout, int requestTimeout){
		
		Map<String, Object> requestContext = bindingProvider.getRequestContext();
		
		requestContext.put("com.sun.xml.internal.ws.connect.timeout", connectionTimeout * MillisecondTimeUnits.SECOND);
		requestContext.put("javax.xml.ws.client.connectionTimeout", connectionTimeout * MillisecondTimeUnits.SECOND);
		
		requestContext.put("com.sun.xml.internal.ws.request.timeout", requestTimeout * MillisecondTimeUnits.SECOND);
		requestContext.put("javax.xml.ws.client.receiveTimeout", requestTimeout * MillisecondTimeUnits.SECOND);
	}
	
	public static void setUsernamePassword(BindingProvider bindingProvider, String username, String password){
		
		Map<String, Object> requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, username);		
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
	}
	
	public static void setEndpoint(BindingProvider bindingProvider, String url){
		
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
	}
	
	public static void setMaintainSession(BindingProvider bindingProvider, boolean value){
		
		bindingProvider.getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, value);
	}
	
	public static void setKeyStore(BindingProvider bindingProvider, String keyStorePath, String keyStorePass) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {
		
		FileInputStream inputStream = null;
		
		try {
			
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			
			inputStream = new FileInputStream(keyStorePath);
			
			keyStore.load(inputStream, keyStorePass.toCharArray());
			
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(keyStore, keyStorePass.toCharArray());
			
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
			
			SSLSocketFactory socketFactory = sslContext.getSocketFactory();
			
			setSSLSocketFactory(bindingProvider, socketFactory);
			
		} finally {
			
			CloseUtils.close(inputStream);
		}
	}
	
	public static void setSSLSocketFactory(BindingProvider bindingProvider, SSLSocketFactory socketFactory){
		
		bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.transport.https.client.SSLSocketFactory", socketFactory);
		bindingProvider.getRequestContext().put("com.sun.xml.ws.transport.https.client.SSLSocketFactory", socketFactory);
	}
}
