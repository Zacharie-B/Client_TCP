package client_reseau;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import GUI_client.FenetrePrincipal;
import log.LoggerUtility;

/**
 * classe permmettant d'assurer le rôle de client TCP qui communique avec le serveur TCP
 * 
 * @author Zacharie
 */
public class ClientTCP extends ProtocoleClient {

	public static void main (String argv []) throws IOException, InterruptedException {
    	Logger logger = LoggerUtility.getLogger(ClientTCP.class, "text");
        Socket socket = null;
        PrintWriter flux_sortie = null;
        BufferedReader flux_entree = null;
        String userCommand = "";
        FenetrePrincipal fenetreprincipal = null;
		boolean put_product = false;
		int portNumber = 5000;
        
        /**
         * connexion avec le serveur réseau
         */
        try {
            // deuxieme argument : le numero de port que l'on contacte
        	if(argv[1] != null)
        		portNumber = Integer.valueOf(argv[1]);
        	socket = new Socket (argv[0], portNumber);
            flux_sortie = new PrintWriter (socket.getOutputStream (), true);
            flux_entree = new BufferedReader (new InputStreamReader (
                                        socket.getInputStream ()));
            String ip = socket.getInetAddress().getHostAddress();
            NetworkPing networkPing = new NetworkPing(socket.getInetAddress());
            networkPing.pingServer();
            
            logger.info("Bienvenue dans notre client TCP");
            logger.info("Je suis connecté au serveur d'adresse IP " + ip);
            logger.info("Il m'écoute sur le port numéro " + portNumber  +"\n");
        }
        catch (UnknownHostException e) {
            System.err.println ("Hote inconnu.") ;
            System.exit (1);
        } 
        catch (ConnectException ce) {
        	System.err.println("La connexion n'a pas pu être établit sur le port " + portNumber  + ".");
        	System.out.println("Soit vous n'utilisez pas le bon port, soit le serveur recherché n'est pas démarré");
        	System.exit(1);
        }
        
        try {
	        /**
	         * boucle permettant de lancer le protocole applicatif côté client
	         */
	    	do {
	    		BufferedReader entree_standard = new BufferedReader(new InputStreamReader(System.in)) ;
	    		/*
	    		 * si la fenêtre pour insérer des produits est ouverte
	    		 */
    			if(put_product) {
    				/*
    				 * on insère les produits dans la BD si l'utilisateur à appuyer sur le bouton "Ajouter produit"
    				 */
    				if(FenetrePrincipal.enregistrer) {
    					userCommand = SendProduct(flux_sortie, flux_entree);
    					put_product = fenetreprincipal.isShowing();
    				}
    			}
	    		/*
	    		 * si la fenêtre est fermée
	    		 */
    			else {
    				if((userCommand = entree_standard.readLine()) != null){
    					/*
    					 * on ouvre la fenêtre pour insérer les produits si l'utilisateur écrit "Ouvrir GUI"
    					 */
    	    			if(userCommand.equals("Ouvrir GUI")) {
    	    				fenetreprincipal = new FenetrePrincipal();
    	    				put_product = fenetreprincipal.isShowing();
    	    			}
    	    			/*
    	    			 * ou on se déconnecte du serveur si il écrit "Salut"
    	    			 */
    	    			else if (userCommand.equals("Salut")) {
    	    				EndingDialog(flux_sortie, flux_entree);
    	    				System.out.println("je me suis deconnecté du serveur");
    	    				break;
    	    			}
    	    			/*
    	    			 * on envoie un message par défaut au serveur lorsqu'il écrit autre chose
    	    			 */
    	    			else if (userCommand.equals("Test dépassement de buffer")){
    	    				SendBufferOverFlow(flux_sortie, flux_entree, userCommand);
    	    			}
    	    			/*
    	    			 * Test pour gestion de la lenteur de renvoi de message par le serveur
    	    			 */
    	    			else if(userCommand.equals("Lenteur serveur")) {
    	    				socket.setSoTimeout(3000);
    	    				ResponseTooSlow(flux_sortie, flux_entree, socket);
    	    			}
    	    			/*
    	    			 * on envoie un message qui ne respecte pas le protocole applicatif
    	    			 */
    	    			else {
    	    				userCommand = MessageToReject(flux_sortie,flux_entree,userCommand);
    	    			}
    				}
    			}
	        } while (userCommand != null);
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
