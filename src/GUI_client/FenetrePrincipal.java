package GUI_client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
/**
 * 
 * @author Thibaut, Zacharie
 *
 */
public class FenetrePrincipal extends JFrame{
	
	InfoProductToPut infoproducttoput = new InfoProductToPut();
	public static boolean enregistrer = false;
	public static boolean stop = false;

	public static int taille_JTextField = 30;
	
	private JPanel panelFenetre = new JPanel();
	private JPanel panelInformationGeneral = new JPanel(new GridBagLayout());
	private JPanel choixType = new JPanel();
	private JPanel panelchoix = new JPanel();
	private JPanel panelInformationOrdinateur = new JPanel(new GridBagLayout());
	private JPanel panelInformationPeripheriqueBasique = new JPanel(new GridBagLayout());
	private JPanel panelInformationPeripheriqueAudio = new JPanel(new GridBagLayout());
	private JPanel panelBoutton = new JPanel();
	
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	public static JRadioButton buttonRadio_Ordinateur = new JRadioButton("Ordinateur");
	public static JRadioButton buttonRadio_PeripheriqueBasique = new JRadioButton("Périphérique Basique");
    public static JRadioButton buttonRadio_PeripheriqueAudio = new JRadioButton("Périphérique Audio");
    public static JRadioButton buttonRadio_Aucun = new JRadioButton("Aucun");
	
    //information général
	public JLabel labelNomProduit = new JLabel("Nom du produit :");
	public static JTextField textNomProduit = new JTextField(taille_JTextField);
	public JLabel labelCoutProduit = new JLabel("Cout du produit :");
	public static JTextField textCoutProduit = new JTextField(taille_JTextField);
	public JLabel labelTypeProduit = new JLabel("Type du produit :");
	public static JTextField textTypeProduit = new JTextField(taille_JTextField);
	public JLabel labelDateFabrication = new JLabel("Date de fabrication :");
	public static JTextField textDateFabrication = new JTextField(taille_JTextField);
	public JLabel labelDateRestockage = new JLabel("Date de restockage :");
	public static JTextField textDateRestockage = new JTextField(taille_JTextField);
	public JLabel labelStock = new JLabel("Stock disponible :");
	public static JTextField textStock = new JTextField(taille_JTextField);
	
	//information ordinateur
	public JLabel labelMarqueO = new JLabel("Marque du produit :");
	public static JTextField textMarqueO = new JTextField(taille_JTextField);
	public JLabel labelProcesseur = new JLabel("Processeur du produit :");
	public static JTextField textProcesseur = new JTextField(taille_JTextField);
	public JLabel labelVitesseProcesseur = new JLabel("Vitesse du Processeur :");
	public static JTextField textVitesseProcesseur = new JTextField(taille_JTextField);
	public JLabel labelTailleEcran = new JLabel("Taille écran du produit :");
	public static JTextField textTailleEcran = new JTextField(taille_JTextField);
	public JLabel labelMemoire = new JLabel("Mémoire vive du processeur :");
	public static JTextField textMemoire = new JTextField(taille_JTextField);
	
	//information Peripherique Basique
	public JLabel labelMarquePB = new JLabel("Marque du produit :");
	public static JTextField textMarquePB = new JTextField(taille_JTextField);
	public JLabel labelTypePeripherique = new JLabel("Type de périphérique :");
	public static JTextField textTypePeripherique = new JTextField(taille_JTextField);
	
	//information Peripherique Audio
	public JLabel labelGererAppel = new JLabel("Gère l'appel :");
	public static ButtonGroup buttonGroupAppel = new ButtonGroup();
	public static JRadioButton buttonRadioAppelVrai = new JRadioButton("Vrai");
	public static JRadioButton buttonRadioAppelFaux = new JRadioButton("Faux");
	public JLabel labelQualiteSon = new JLabel("Capacité son :");
	public static JTextField textQualiteSon = new JTextField(taille_JTextField);
	public JLabel labelFilaire = new JLabel("Filaire :");
	public static ButtonGroup buttonGroupFilaire = new ButtonGroup();
	public static JRadioButton buttonRadioFilaireVrai = new JRadioButton("Vrai");
	public static JRadioButton buttonRadioFilaireFaux = new JRadioButton("Faux");
	public JLabel labelBluetooth = new JLabel("Bluetooth :");
	public static ButtonGroup buttonGroupBluetooth = new ButtonGroup();
	public static JRadioButton buttonRadioBluetoothVrai = new JRadioButton("Vrai");
	public static JRadioButton buttonRadioBluetoothFaux = new JRadioButton("Faux");
	
	private JButton ajoutduProduit = new JButton("Ajouter produit");
	private JButton clear = new JButton("Clear");
	
	public FenetrePrincipal() {

		//super("Ajouter produit");
		
		informationGeneral();
		panelFenetre.add(panelInformationGeneral);
	    
		buttonchoixtype();
        panelFenetre.add(choixType);
        
        informationOrdinateur();
		informationPeripheriqueBasique();
		informationPeripheriqueAudio();
        panelFenetre.add(panelchoix);
        
        buttonPrincipal();
        panelFenetre.add(panelBoutton);
        
        setContentPane(panelFenetre);
        
		setSize(600, 550);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// place la fenetre au milieu de l'écran
		setLocationRelativeTo(null);
		// redimension de la fenetre interdit
		setAlwaysOnTop(true);
		setResizable(false);
		setVisible(true);
	}
	
	void radioButtons_itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if(source == buttonRadio_Ordinateur) {
        	panelchoix.removeAll();
        	panelchoix.repaint();
            panelchoix.revalidate();
   
            panelchoix.add(panelInformationOrdinateur);
            panelchoix.repaint();       
            panelchoix.revalidate();
        }
        if(source == buttonRadio_PeripheriqueBasique) {
        	panelchoix.removeAll();
        	panelchoix.repaint();
            panelchoix.revalidate();
   
            panelchoix.add(panelInformationPeripheriqueBasique);
            panelchoix.repaint();       
            panelchoix.revalidate();
        }
        if(source == buttonRadio_PeripheriqueAudio) {
        	panelchoix.removeAll();
        	panelchoix.repaint();
            panelchoix.revalidate();
   
            panelchoix.add(panelInformationPeripheriqueAudio);
            panelchoix.repaint();       
            panelchoix.revalidate();
        }
        if(source == buttonRadio_Aucun) {
        	panelchoix.removeAll();
        	panelchoix.repaint();
        	panelchoix.revalidate();
        }
    }
	
	/*
	 * panel pour les informations sur un produit en général
	 */
	public void informationGeneral() {
		
		GridBagConstraints  gridbagP = new GridBagConstraints ();
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 0;
		gridbagP.ipady = 5;
		//marge de chaque composant
		gridbagP.insets = new Insets(5,5,5,5);
		panelInformationGeneral.add(labelNomProduit, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 0;
		panelInformationGeneral.add(textNomProduit, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 1;
		panelInformationGeneral.add(labelCoutProduit, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 1;
		panelInformationGeneral.add(textCoutProduit, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 2;
		panelInformationGeneral.add(labelTypeProduit, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 2;
		panelInformationGeneral.add(textTypeProduit, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 3;
		panelInformationGeneral.add(labelDateFabrication, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 3;
		panelInformationGeneral.add(textDateFabrication, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 4;
		panelInformationGeneral.add(labelDateRestockage, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 4;
		panelInformationGeneral.add(textDateRestockage, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 5;
		panelInformationGeneral.add(labelStock, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 5;
		panelInformationGeneral.add(textStock, gridbagP);
	}
	
	public void buttonchoixtype() {
		//buttonRadio_Aucun est choisi par default
		buttonRadio_Aucun.setSelected( true );
		choixType.add( buttonRadio_Aucun );
        buttonGroup.add( buttonRadio_Aucun );
        buttonRadio_Aucun.addItemListener( this::radioButtons_itemStateChanged );
        
        choixType.add( buttonRadio_Ordinateur );
        buttonGroup.add( buttonRadio_Ordinateur );
        buttonRadio_Ordinateur.addItemListener( this::radioButtons_itemStateChanged );
        
        choixType.add( buttonRadio_PeripheriqueBasique );
        buttonGroup.add( buttonRadio_PeripheriqueBasique );
        buttonRadio_PeripheriqueBasique.addItemListener( this::radioButtons_itemStateChanged );
        
        choixType.add( buttonRadio_PeripheriqueAudio );
        buttonGroup.add( buttonRadio_PeripheriqueAudio );
        buttonRadio_PeripheriqueAudio.addItemListener( this::radioButtons_itemStateChanged );
	}
	
	/*
	 * panel pour les informations sur un produit de type ordinateur
	 */
	public void informationOrdinateur() {
		GridBagConstraints  gridbagP = new GridBagConstraints ();
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 0;
		gridbagP.ipady = 5;
		//marge de chaque composant
		gridbagP.insets = new Insets(5,5,5,5);
		panelInformationOrdinateur.add(labelMarqueO, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 0;
		panelInformationOrdinateur.add(textMarqueO, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 1;
		panelInformationOrdinateur.add(labelProcesseur, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 1;
		panelInformationOrdinateur.add(textProcesseur, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 2;
		panelInformationOrdinateur.add(labelVitesseProcesseur, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 2;
		panelInformationOrdinateur.add(textVitesseProcesseur, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 3;
		panelInformationOrdinateur.add(labelTailleEcran, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 3;
		panelInformationOrdinateur.add(textTailleEcran, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 4;
		panelInformationOrdinateur.add(labelMemoire, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 4;
		panelInformationOrdinateur.add(textMemoire, gridbagP);
	}
	
	/*
	 * panel pour les informations sur un produit de type Peripherique Basique
	 */
	public void informationPeripheriqueBasique() {
		GridBagConstraints  gridbagP = new GridBagConstraints ();
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 0;
		gridbagP.ipady = 5;
		//marge de chaque composant
		gridbagP.insets = new Insets(5,5,5,5);
		panelInformationPeripheriqueBasique.add(labelMarquePB, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 0;
		panelInformationPeripheriqueBasique.add(textMarquePB, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 1;
		panelInformationPeripheriqueBasique.add(labelTypePeripherique, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 1;
		panelInformationPeripheriqueBasique.add(textTypePeripherique, gridbagP);
	}

	/*
	 * panel pour les informations sur un produit de type Peripherique Audio
	 */
	public void informationPeripheriqueAudio() {
		GridBagConstraints  gridbagP = new GridBagConstraints ();
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 0;
		gridbagP.ipady = 5;
		//marge de chaque composant
		gridbagP.insets = new Insets(5,5,5,5);
		panelInformationPeripheriqueAudio.add(labelGererAppel, gridbagP);
		
		JPanel panelVF_1 = new JPanel();
		panelVF_1.add( buttonRadioAppelVrai );
		buttonGroupAppel.add( buttonRadioAppelVrai );
		panelVF_1.add( buttonRadioAppelFaux );
		buttonGroupAppel.add( buttonRadioAppelFaux );
		gridbagP.gridx = 1;
		gridbagP.gridy = 0;
		panelInformationPeripheriqueAudio.add(panelVF_1, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 1;
		panelInformationPeripheriqueAudio.add(labelQualiteSon, gridbagP);
		
		gridbagP.gridx = 1;
		gridbagP.gridy = 1;
		panelInformationPeripheriqueAudio.add(textQualiteSon, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 2;
		panelInformationPeripheriqueAudio.add(labelFilaire, gridbagP);
		
		JPanel panelVF_2 = new JPanel();
		panelVF_2.add( buttonRadioFilaireVrai );
		buttonGroupFilaire.add( buttonRadioFilaireVrai );
		panelVF_2.add( buttonRadioFilaireFaux );
		buttonGroupFilaire.add( buttonRadioFilaireFaux );
		gridbagP.gridx = 1;
		gridbagP.gridy = 2;
		panelInformationPeripheriqueAudio.add(panelVF_2, gridbagP);
		
		gridbagP.gridx = 0;
		gridbagP.gridy = 3;
		panelInformationPeripheriqueAudio.add(labelBluetooth, gridbagP);
		
		JPanel panelVF_3 = new JPanel();
		panelVF_3.add( buttonRadioBluetoothVrai );
		buttonGroupBluetooth.add( buttonRadioBluetoothVrai );
		panelVF_3.add( buttonRadioBluetoothFaux );
		buttonGroupBluetooth.add( buttonRadioBluetoothFaux );
		gridbagP.gridx = 1;
		gridbagP.gridy = 3;
		panelInformationPeripheriqueAudio.add(panelVF_3, gridbagP);
	}
	
	public void buttonPrincipal() {
		ajoutduProduit.addActionListener(new AjoutProduit());
        clear.addActionListener(new Clear());
        panelBoutton.add(ajoutduProduit);
        panelBoutton.add(clear);
	}
	
	private void AddElementText(JTextField jtextfield, String key) {
		if (!jtextfield.getText().isEmpty()) {
			infoproducttoput.AddElement(key,jtextfield.getText());
			jtextfield.setText("");
		}
	}
	
	private void AddElementButton(JRadioButton jradiobutton, String key) {
		if(key.contentEquals("categorie")){
			if(jradiobutton.isSelected()) {
				infoproducttoput.AddElement(key,jradiobutton.getText());
			}
		}
		else {
			if(!jradiobutton.isSelected()) {
				infoproducttoput.AddElement(key,"false");
			}
			else {
				infoproducttoput.AddElement(key,"true");
			}
		}
	}
	private void ClearInformation() {
		textNomProduit.setText("");
		textCoutProduit.setText("");
		textTypeProduit.setText("");
		textDateFabrication.setText("");
		textDateRestockage.setText("");
		textStock.setText("");
		textMarqueO.setText("");
		textProcesseur.setText("");
		textVitesseProcesseur.setText("");
		textTailleEcran.setText("");
		textMemoire.setText("");
		textMarquePB.setText("");
		textTypePeripherique.setText("");
		buttonRadioAppelVrai.setSelected(false);
		textNomProduit.setText("");
		buttonRadioFilaireVrai.setSelected(false);
		buttonRadioBluetoothVrai.setSelected(false);
		
	}
	
	/**
	 * sert à ajouter un produit dans une HashMap
	 * @author Thibaut, Zacharie
	 *
	 */
	private class AjoutProduit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(infoproducttoput.VerifFullField()) {
				AddElementText(textNomProduit,"nom_produit");
				AddElementText(textCoutProduit,"cout_produit");
				AddElementText(textTypeProduit,"type_produit");
				AddElementText(textDateFabrication,"date_fabrication");
				AddElementText(textDateRestockage,"date_restockage");
				AddElementText(textStock,"stock");
				AddElementText(textMarqueO,"marqueO");
				AddElementText(textProcesseur,"processeur");
				AddElementText(textVitesseProcesseur,"vitesse_processeur");
				AddElementText(textTailleEcran,"taille_ecran");
				AddElementText(textMemoire,"memoire");
				AddElementText(textMarquePB,"marquePB");
				AddElementText(textTypePeripherique,"type_peripherique");
				AddElementButton(buttonRadioAppelVrai,"gerer_appel");
				AddElementText(textQualiteSon,"qualite_son");
				AddElementButton(buttonRadioFilaireVrai,"filaire");
				AddElementButton(buttonRadioBluetoothVrai,"bluetooth");
				AddElementButton(buttonRadio_Ordinateur,"categorie");
				AddElementButton(buttonRadio_PeripheriqueBasique,"categorie");
				AddElementButton(buttonRadio_PeripheriqueAudio,"categorie");
				enregistrer =true;
			}
		}
	}
	
	private class Clear implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ClearInformation();
		}
	}
	
}
