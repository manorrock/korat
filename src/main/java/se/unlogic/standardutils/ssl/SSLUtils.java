package se.unlogic.standardutils.ssl;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

public class SSLUtils {

	public static final TrustManager[] INSECURE_TRUST_MANGERS = new TrustManager[] { new InsecureTrustManager() };

	private static class InsecureTrustManager extends X509ExtendedTrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {

			return new X509Certificate[0];
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {

		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {

		}

	}

}
