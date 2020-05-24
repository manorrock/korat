package se.unlogic.standardutils.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SubnetUtils {

	public static boolean isInSubnet(String network, String ip) {

		String[] parts = network.split("/");
		String networkIp = parts[0];

		int prefix;

		if (parts.length < 2) {
			prefix = 0;
		} else {
			prefix = Integer.parseInt(parts[1]);
		}

		Inet4Address networkAddress = null;
		Inet4Address ipAddress = null;
		try {
			networkAddress = (Inet4Address) InetAddress.getByName(networkIp);
			
			InetAddress inetAddress = InetAddress.getByName(ip);
			
			if(!(inetAddress instanceof Inet4Address)) {
				
				return false;
			}
			
			ipAddress = (Inet4Address) inetAddress;
			
		} catch (UnknownHostException e) {
			
			return false;
			
		}

		byte[] rawNetworkAddress = networkAddress.getAddress();
		int networkInt = ((rawNetworkAddress[0] & 0xFF) << 24) | ((rawNetworkAddress[1] & 0xFF) << 16) | ((rawNetworkAddress[2] & 0xFF) << 8) | ((rawNetworkAddress[3] & 0xFF) << 0);

		byte[] rawIpAddress = ipAddress.getAddress();
		int ipInt = ((rawIpAddress[0] & 0xFF) << 24) | ((rawIpAddress[1] & 0xFF) << 16) | ((rawIpAddress[2] & 0xFF) << 8) | ((rawIpAddress[3] & 0xFF) << 0);

		int mask = ~((1 << (32 - prefix)) - 1);

		if ((networkInt & mask) == (ipInt & mask)) {
			
			return true;
			
		} else {
			
			return false;
		}
	}
}
