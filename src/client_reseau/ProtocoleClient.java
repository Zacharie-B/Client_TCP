package client_reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import GUI_client.FenetrePrincipal;

/**
 * contenu détaillé pemettant de faire le protocole applicatif du côté client TCP
 * @author Zacharie
 *
 */
public class ProtocoleClient {
    public static HashMap<String, String> products = new HashMap<String, String>();
	
	protected ProtocoleClient(){
		
	}
	
	/**
	 * envoie une hashmap avec les caractéristiques d'un produit à rentrer dans la base de données
	 * @param flux_sortie
	 * @param flux_entree
	 * @return String la réponse du serveur
	 * @throws IOException
	 */
	public static String SendProduct(PrintWriter flux_sortie, BufferedReader flux_entree) throws IOException {
		flux_sortie.write(CodesProtocoles.CODE_SEND_NEW_PRODUCT + " " + products);
		System.out.println(CodesProtocoles.CODE_SEND_NEW_PRODUCT);
//		flux_sortie.println ("[Client] 0007 " + products);
		System.out.println(products);
		String chaine = flux_entree.readLine();
		System.out.println ("[Serveur] : " + chaine);
		FenetrePrincipal.enregistrer = false;
		return chaine;
	}
	
	/**
	 * lit le message d'un utilisateur et si il tape "Salut !", le clent TCP se déconnecte du serveur TCP
	 * @param flux_sortie
	 * @param flux_entree
	 * @return String la réponse du serveur
	 * @throws IOException
	 */
	public static void EndingDialog(PrintWriter flux_sortie, BufferedReader flux_entree, String chaine) throws IOException {
		flux_sortie.println (chaine) ;
		chaine = flux_entree.readLine () ;
		System.out.println ("[Serveur] : " + chaine) ;

	}
	
	/**
	 * envoie un message trop long pour être envoyé au serveur en 1 fois
	 * @param flux_sortie
	 * @param flux_entree
	 * @return String la réponse du serveur
	 * @throws IOException
	 */
	public static String TooLongMessage(PrintWriter flux_sortie, BufferedReader flux_entree) throws IOException {
		String chaine ="";
		for(int i=0;i<3;i++) {
			flux_sortie.println ("[Client] : message n° "+i) ;
			chaine += "\n"+flux_entree.readLine () ;
		}
		System.out.println ("[Serveur] : " + chaine) ;
		return chaine;
	}
	
	/**
	 * envoie un message que le serveur va rejeter car il n'est pas conforme au protocole applicatif
	 * @param flux_sortie
	 * @param flux_entree
	 * @param chaine_entree
	 * @return String la réponse du serveur
	 * @throws IOException
	 */
	public static String MessageToReject(PrintWriter flux_sortie, BufferedReader flux_entree, String chaine_entree) throws IOException {
		flux_sortie.println (chaine_entree);
		String chaine = flux_entree.readLine();
		System.out.println(chaine);
		return chaine;
	}
	
	/**
	 * envoie le message qui test la gestion d'une réponse du serveur trop lente
	 * @param flux_sortie
	 * @param flux_entree
	 * @param socket
	 * @throws IOException
	 */
	public static void ResponseTooSlow(PrintWriter flux_sortie, BufferedReader flux_entree, Socket socket) throws IOException {
		flux_sortie.println ("vous me recevez ?");
		String chaine = flux_entree.readLine();
		System.out.println(chaine);
	}
	
	/**
	 * envoie un message qui test la gestion d'une réponse du client trop lente
	 * @param flux_sortie
	 * @param flux_entree
	 * @param socket
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void  ResponseVerySlow(PrintWriter flux_sortie, BufferedReader flux_entree, Socket socket) throws IOException, InterruptedException {
		flux_sortie.println ("test client");
		String chaine = flux_entree.readLine();
		System.out.println(chaine);
		Thread.sleep(5000);
		flux_sortie.println(chaine);
		chaine = flux_entree.readLine();
		socket.close();
	}
}
