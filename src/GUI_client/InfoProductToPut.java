package GUI_client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private boolean VerifFullField() {
		//  generals information
		if(FenetrePrincipal.textNomProduit.getText().isEmpty())	return false;
		else if(FenetrePrincipal.textCoutProduit.getText().isEmpty())	return false;
		else if(FenetrePrincipal.textTypeProduit.getText().isEmpty())	return false;
		else if(FenetrePrincipal.textDateFabrication.getText().isEmpty())	return false;
		else if(FenetrePrincipal.textDateRestockage.getText().isEmpty())	return false;
		else if(FenetrePrincipal.textStock.getText().isEmpty())	return false;
		
		// specifics information
		if(FenetrePrincipal.buttonRadio_Ordinateur.isSelected()) {
			if(FenetrePrincipal.textMarqueO.getText().isEmpty())	return false;
			else if(FenetrePrincipal.textProcesseur.getText().isEmpty())	return false;
			else if(FenetrePrincipal.textVitesseProcesseur.getText().isEmpty())	return false;
			else if(FenetrePrincipal.textTailleEcran.getText().isEmpty())	return false;
			else if(FenetrePrincipal.textMemoire.getText().isEmpty())	return false;
		}
		
		else if(FenetrePrincipal.buttonRadio_PeripheriqueBasique.isSelected()) {
			if(FenetrePrincipal.textMarquePB.getText().isEmpty())	return false;
			else if (FenetrePrincipal.textTypePeripherique.getText().isEmpty())	return false;
		}
		
		else if(FenetrePrincipal.buttonRadio_PeripheriqueAudio.isSelected()) {
			if(!FenetrePrincipal.buttonRadioAppelVrai.isSelected() 
					&& !FenetrePrincipal.buttonRadioAppelFaux.isSelected())	return false;
			else if(FenetrePrincipal.textQualiteSon.getText().isEmpty())	return false;
			else if(!FenetrePrincipal.buttonRadioFilaireVrai.isSelected()
					&& !FenetrePrincipal.buttonRadioFilaireFaux.isSelected())	return false;
			else if(!FenetrePrincipal.buttonRadioBluetoothVrai.isSelected() 
					&& !FenetrePrincipal.buttonRadioBluetoothFaux.isSelected())	return false;
		}
		// Button "Aucun" selected
		else	return false;
		
		return true;
	}
	
	public boolean infoProductHasGoodFormat() {
		if(!VerifFullField()) {
			return false;
		}
		else {
			try {
				Float cout = Float.parseFloat(FenetrePrincipal.textCoutProduit.getText());
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(FenetrePrincipal.textDateFabrication.getText());
				date = new SimpleDateFormat("yyyy-MM-dd").parse(FenetrePrincipal.textDateRestockage.getText());
				Long stock = Long.parseLong(FenetrePrincipal.textStock.getText());
				if(FenetrePrincipal.buttonRadio_Ordinateur.isSelected()){
					cout = Float.parseFloat(FenetrePrincipal.textVitesseProcesseur.getText());
					cout = Float.parseFloat(FenetrePrincipal.textTailleEcran.getText());
					cout = Float.parseFloat(FenetrePrincipal.textMemoire.getText());
				}
			}catch(NumberFormatException nfe) {
				System.err.println("Les caractèristiques du produit ne sont pas "
						+ "sous le bon format, faîtes attention aux dates (année-mois-jour) et aux coûts");
				return false;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.err.println("Les caractèristiques du produit ne sont pas "
						+ "sous le bon format, faîtes attention aux dates et aux coûts");
				return false;
			}
			return true;
		}
	}
}
