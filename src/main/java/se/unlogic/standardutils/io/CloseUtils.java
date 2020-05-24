package se.unlogic.standardutils.io;

import java.io.Closeable;
import java.io.IOException;

import javax.naming.ldap.LdapContext;


public class CloseUtils {

	public static final void close(Closeable closeable){

		if(closeable != null){

			try {
				closeable.close();
			} catch (IOException e) {}
		}
	}

	public static void close(LdapContext connection) {

		if(connection != null){

			try {
				connection.close();
			} catch (Exception e) {}
		}
	}
}
