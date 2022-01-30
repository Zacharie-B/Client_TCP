package client_reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import GUI_client.FenetrePrincipal;

/**
 * contenu détaillé pemettant de faire le protocole applicatif du côté client TCP
 * 
 * @author Zacharie
 */
public class ProtocoleClient implements Runnable {
	public static HashMap<String, String> products = new HashMap<String, String>();
	protected static PrintWriter flux_sortie;
	protected static BufferedReader flux_entree;

	protected ProtocoleClient() {
		
	}

	/**
	 * envoie une hashmap avec les caractéristiques d'un produit à rentrer dans la base de données
	 * 
	 * @param flux_sortie
	 * @param flux_entree
	 * @return String la réponse du serveur
	 * @throws IOException
	 */
	public String SendProduct(PrintWriter flux_sortie, BufferedReader flux_entree)
			throws IOException {
		flux_sortie.write(CodesProtocoles.CODE_SEND_NEW_PRODUCT + " " + products);
		System.out.println(CodesProtocoles.CODE_SEND_NEW_PRODUCT);
		System.out.println(products);
		String chaine = flux_entree.readLine();
		System.out.println("[Client] Réponse du serveur : " + chaine);
		FenetrePrincipal.enregistrer = false;
		return chaine;
	}

	/**
	 * lit le message d'un utilisateur et si il tape "Salut", le clent TCP se déconnecte du serveur
	 * TCP
	 * 
	 * @param flux_sortie
	 * @param flux_entree
	 * @return String la réponse du serveur
	 * @throws IOException
	 */
	public void EndingDialog(PrintWriter flux_sortie, BufferedReader flux_entree)
			throws IOException {
		flux_sortie.println(CodesProtocoles.QUIT_APP + " ");
		String chaine = flux_entree.readLine();
		System.out.println("[Client] Réponse du serveur : " + chaine);
	}

	/**
	 * envoie un message que le serveur va rejeter car il n'est pas conforme au protocole applicatif
	 * 
	 * @param flux_sortie
	 * @param flux_entree
	 * @param chaine_entree
	 * @return String la réponse du serveur
	 * @throws IOException
	 */
	public String MessageToReject(PrintWriter flux_sortie, BufferedReader flux_entree,
			String chaine_entree) throws IOException {
		flux_sortie.println(chaine_entree);
		String chaine = flux_entree.readLine();
		return chaine;
	}

	/**
	 * envoie le message qui test la gestion d'une réponse du serveur trop lente
	 * 
	 * @param flux_sortie
	 * @param flux_entree
	 * @param socket
	 * @throws IOException
	 */
	public void ResponseTooSlow(PrintWriter flux_sortie, BufferedReader flux_entree,
			Socket socket) {
		flux_sortie.println(CodesProtocoles.TEST_LATENCE_SERVER + " ");
		try {
			String chaine = flux_entree.readLine();
			System.out.println(chaine);
		} catch (IOException ioe) {
			System.err.println("[Client] Le serveur a répondu trop lentement.");
		}
	}

	public void SendBufferOverFlow(PrintWriter flux_sortie, BufferedReader flux_entree,
			String chaine_entree) {
		String a = "Error 404 ";
		StringBuilder bof = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			bof.append(a);
		}
		try {
			System.out.println("Envoie d'un paquet de 100 000 octets");
			flux_sortie.println(CodesProtocoles.TEST_BUFFER_OVERFLOW + " " + bof.toString());
			chaine_entree = flux_entree.readLine();
			System.out.println(chaine_entree);
		} catch (IOException ioe) {
			System.err.println("[Client] Le serveur n'a pas répondu ! ");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.sleep(50000);
				System.out.println("On garde contact");
				flux_sortie.println(CodesProtocoles.CHECK_CONNECTION + " ");
				String chaine = flux_entree.readLine();
				if (chaine == null) {
					throw (new IOException());
				}
				System.out.println("[Client] Réponse du serveur : " + chaine);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(
					"[Client] Le serveur ne répond pas à mes messages, je me déconnecte !");
			System.exit(1);
		}

	}
}
