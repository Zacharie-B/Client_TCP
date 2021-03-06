package client_reseau;

import java.io.IOException;
import java.net.InetAddress;

public class NetworkPing {

	private InetAddress ipServer;

	public NetworkPing(InetAddress ipServer) {
		this.ipServer = ipServer;
	}

	public void pingServer() {
		try {
			if (ipServer.isReachable(1000)) {
				System.out.println(
						ipServer.getHostAddress() + " machine is turned on and can " + "be pinged");
				return;
			} else if (!ipServer.getHostAddress().equals(ipServer.getHostName())) {
				System.out.println(ipServer.toString() + " machine is known in a DNS lookup");
			} else {
				System.out.println(ipServer.toString() + " the host ipServer and host "
						+ "name are equal, meaning the host name could not be resolved");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(
					"Le serveur à l'adresse ipv4 " + ipServer.toString() + "n'est pas atteignable");
		}
	}

}
