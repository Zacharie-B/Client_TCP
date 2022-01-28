package GUI_client;

import client_reseau.ClientTCP;

/**
 * 
 * @author Zacharie
 *
 */
public class InfoProductToPut extends ClientTCP{
	
	public InfoProductToPut() {
		
	}
	
	/**
	 * remplit la HashMap avec les données de l'interface graphique
	 * @param key
	 * @param element
	 */
	public void AddElement(String key, String element) {
			products.put(key, element);
	}
	
	/**
	 * sert à savoir si l'utilisateur a remplit tous les champs nécessaires pour rentrer le produit
	 * @return faux si il manque une seule information dans produit ou le détail de ce produit
	 */
	public boolean VerifFullField() {
		boolean allfill = true;
		if(FenetrePrincipal.textNomProduit.getText().isEmpty()) {
			allfill =false;
		}
		else if(FenetrePrincipal.textCoutProduit.getText().isEmpty()) {
			allfill =false;
		}
		else if(FenetrePrincipal.textTypeProduit.getText().isEmpty()) {
			allfill =false;
		}
		else if(FenetrePrincipal.textDateFabrication.getText().isEmpty()) {
			allfill =false;
		}
		else if(FenetrePrincipal.textDateRestockage.getText().isEmpty()) {
			allfill =false;
		}
		else if(FenetrePrincipal.textStock.getText().isEmpty()) {
			allfill =false;
		}
		
		if(FenetrePrincipal.buttonRadio_Ordinateur.isSelected()) {
			if(FenetrePrincipal.textMarqueO.getText().isEmpty()) {
				allfill =false;
			}
			else if(FenetrePrincipal.textProcesseur.getText().isEmpty()) {
				allfill =false;
			}
			else if(FenetrePrincipal.textVitesseProcesseur.getText().isEmpty()) {
				allfill =false;
			}
			else if(FenetrePrincipal.textTailleEcran.getText().isEmpty()) {
				allfill =false;
			}
			else if(FenetrePrincipal.textMemoire.getText().isEmpty()) {
				allfill =false;
			}
		}
		
		else if(FenetrePrincipal.buttonRadio_PeripheriqueBasique.isSelected()) {
			if(FenetrePrincipal.textMarquePB.getText().isEmpty()) {
				allfill =false;
			}
			else if (FenetrePrincipal.textTypePeripherique.getText().isEmpty()) {
				allfill =false;
			}
		}
		
		else if(FenetrePrincipal.buttonRadio_PeripheriqueAudio.isSelected()) {
			if(!FenetrePrincipal.buttonRadioAppelVrai.isSelected() 
					&& !FenetrePrincipal.buttonRadioAppelFaux.isSelected()) {
				allfill =false;
			}
			else if(FenetrePrincipal.textQualiteSon.getText().isEmpty()) {
				allfill =false;
			}
			else if(!FenetrePrincipal.buttonRadioFilaireVrai.isSelected()
					&& !FenetrePrincipal.buttonRadioFilaireFaux.isSelected()) {
				allfill =false;
			}
			else if(!FenetrePrincipal.buttonRadioBluetoothVrai.isSelected() 
					&& !FenetrePrincipal.buttonRadioBluetoothFaux.isSelected()) {
				allfill =false;
			}
		}
		else {
			allfill=false;
		}
		return allfill;
	}
}
