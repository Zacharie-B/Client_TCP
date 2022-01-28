package client_reseau;

import java.io.IOException ;
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.io.PrintWriter ;
import java.net.ConnectException;
import java.net.Socket ;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException ;

import org.apache.log4j.Logger;

import GUI_client.FenetrePrincipal;
import log.LoggerUtility;

/**
 * classe permmettant d'assurer le rôle de client TCP qui communique avec le serveur TCP
 * @author Zacharie
 *
 */
public class ClientTCP extends ProtocoleClient{
	
    public static void main (String argv []) throws IOException, InterruptedException {
    	Logger logger = LoggerUtility.getLogger(ClientTCP.class, "text");
    	boolean beginning = false ;
        Socket socket = null ;
        PrintWriter flux_sortie = null ;
        BufferedReader flux_entree = null ;
        String chaine = "";
        FenetrePrincipal fenetreprincipal = null;
		boolean put_product = false;
        
        /**
         * connexion avec le serveur réseau
         */
        try {
            // deuxieme argument : le numero de port que l'on contacte
            socket = new Socket (argv[0], Integer.valueOf(argv [1])) ;
            flux_sortie = new PrintWriter (socket.getOutputStream (), true) ;
            flux_entree = new BufferedReader (new InputStreamReader (
                                        socket.getInputStream ())) ;
            String ip = socket.getInetAddress().toString();
            String ip_client = ip.substring(1);
            int port_number= socket.getPort();
            logger.info("Bienvenue dans notre client TCP");
            logger.info("Je suis connecté au serveur d'adresse IP "+ip_client);
            logger.info("Il m'écoute sur le port numéro "+port_number +"\n");
        }
        catch (UnknownHostException e ) {
            System.err.println ("Hote inconnu.") ;
            System.exit (1) ;
        } 
        catch (ConnectException ce) {
        	System.err.println("La connexion n'a pas pu être établit sur le port " + argv[1] +".");
        	System.out.println("Soit vous n'utilisez pas le bon port, soit le serveur recherché n'est pas démarré");
        	System.exit(1);
        }
        
        try {
	        /**
	         * boucle permettant de lancer le protocole applicatif côté client
	         */
	    	do {
	    		BufferedReader entree_standard = new BufferedReader ( new InputStreamReader ( System.in)) ;
	    		/*
	    		 *  on commence le protocole applicatif par un message test de commmunication
	    		 */
	    		if(!beginning) {
	    			chaine = BeginningDialog(flux_sortie, flux_entree);
	    			if(chaine!=null) {
	    				beginning = true;
	    			}
	    		}
	    		/*
	    		 * si la fenêtre pour insérer des produits est ouverte
	    		 */
    			else if(put_product) {
    				/*
    				 * on insère les produits dans la BD si l'utilisateur à appuyer sur le bouton "Ajouter produit"
    				 */
    				if(FenetrePrincipal.enregistrer) {
    					chaine = SendProduct(flux_sortie, flux_entree);
    					put_product = fenetreprincipal.isShowing();
    				}
    				/*
    				 * ou on envoie un message par défaut chaque seconde,
    				 * le temps que l"utilisateur nous donnent les données de ce produit
    				 */
    				else {
    					chaine = "to be continued";
	    				put_product = fenetreprincipal.isShowing();
    				}
    			}
	    		/*
	    		 * si la fenêtre est fermée
	    		 */
    			else {
    				/*
    				 * on attends que l'utilisateur tape quelque chose dans la console
    				 */
    				if((chaine = entree_standard.readLine()) != null){
    					/*
    					 * on ouvre la fenêtre pour insérer les produits si l'utilisateur écrit "Ouvrir GUI"
    					 */
    	    			if(chaine.equals("Ouvrir GUI")) {
    	    				fenetreprincipal = new FenetrePrincipal();
    	    				put_product = fenetreprincipal.isShowing();
    	    			}
    	    			/*
    	    			 * ou on se déconnecte du serveur si il écrit "Salut"
    	    			 */
    	    			else if (chaine.equals("Salut")) {
    	    				EndingDialog(flux_sortie, flux_entree, chaine);
    	    				System.out.println("je me suis deconnecté du serveur");
    	    				break;
    	    			}
    	    			/*
    	    			 * on envoie un message par défaut au serveur lorsqu'il écrit autre chose
    	    			 */
    	    			else if (chaine.equals("Test ordre paquet")){
    	    				chaine = TooLongMessage(flux_sortie, flux_entree);
    	    			}
    	    			/*
    	    			 * Test pour gestion de la lenteur de renvoi de message par le serveur
    	    			 */
    	    			else if(chaine.equals("Lenteur serveur")) {
    	    				socket.setSoTimeout(3000);
    	    				ResponseTooSlow(flux_sortie, flux_entree, socket);
    	    			}
    	    			/*
    	    			 * Test pour gestion de la lenteur de renvoi de message par le client
    	    			 */
    	    			else if (chaine.equals("Lenteur client")) {
    	    				ResponseVerySlow(flux_sortie, flux_entree, socket);
    	    			}
    	    			/*
    	    			 * on envoie un message qui ne respecte pas le protocole applicatif
    	    			 */
    	    			else {
    	    				chaine = MessageToReject(flux_sortie,flux_entree,chaine);
    	    			}
    				}
    			}
	        } while (chaine != null);
    	}
        catch (SocketException e) {
        	String socket_error = "Le serveur s'est brutalement déconnecté.\n";
        	System.err.println(socket_error);
        	logger.info(socket_error);
        }catch (SocketTimeoutException e) {
        	String socket_time_out = "Le serveur a mis trop de temps pour répondre, je me suis déconnecté.\n";
        	System.err.println(socket_time_out);
        	logger.info(socket_time_out);
        }
	    flux_sortie.close() ;
	    flux_entree.close() ;
	    socket.close() ;
    }
        
}
