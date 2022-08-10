import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Font;
import java.text.DecimalFormat;

public class GestionRadios {
	
	static final String FICHIER_RADIOS = "src/donnees/radios.txt";
	static ArrayList<Radio> listeRadios; 
	static BufferedReader tmpRadiosRead;
	static BufferedWriter tmpRadiosWrite; 
	static JTextArea sortie;

	public static void main(String[] args) throws Exception {
		chargerRadios();
		String marque;
		int choix;
		do {
			choix = menu();
			switch(choix){
				case 1: 
					listerRadios();
					break;
				case 2:
					marque= JOptionPane.showInputDialog(
						null, "Entrez une marque: ", "LISTE DES RADIOS PAR MARQUE", 
						JOptionPane.PLAIN_MESSAGE);
					listerRadiosParMarque(marque);
					break;
				case 3:
					marque= JOptionPane.showInputDialog(
							null, "Entrez une marque: ", 
							"MODIFIER LE PRIX DES RADIOS D'UNE MARQUE", 
							JOptionPane.PLAIN_MESSAGE);
					modifierPrixRadio(marque);
					break;
				case 4:
					ajouterRadio();
					break;
				case 5:
					chercherRadio();
					break;
				case 6:
					afficherMessage("Merci d'avoir utilisé notre système");
					break;
				default:
					afficherMessage("Choix invalide. Les options sont [1-6]!");
			}
		}while(choix != 6);
		System.exit(0);
	}
	
	private static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", 
			JOptionPane.PLAIN_MESSAGE);
	}
	
	private static void afficherEntete(){
		sortie = new JTextArea();
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));
		sortie.append("\t\tLISTE DES RADIOS\n\n");
		sortie.append("MARQUE\tMOD�LE\tCD\tCASSETTE\tMP3\tPRIX   \n");
	}
	
	public static int menu() {
		String contenu="1. Lister toutes les radios\n"+
	                   "2. Lister les radios par marque\n"+
				       "3. Modifier le prix des radios par marque\n"+
	                   "4. Ajouter une nouvelle radio\n"+
				       "5. Modifier une radio existante\n"+
	                   "6. Terminer\n\n";
		contenu+="Choisissez une option: ";
		return Integer.parseInt(JOptionPane.showInputDialog(
			null, contenu, "MENU", JOptionPane.PLAIN_MESSAGE));
	}
	
	public static void chargerRadios() throws Exception {
		try {
			String ligne;
			String elems[] = new String[6];
			listeRadios = new ArrayList<>();
			tmpRadiosRead = new BufferedReader(new FileReader(FICHIER_RADIOS));
			//
			ligne = tmpRadiosRead.readLine();
			while (ligne != null) {
				elems = ligne.split(";");
				listeRadios.add(new Radio(elems[0], elems[1], 
							              Integer.parseInt(elems[2]), 
							              Integer.parseInt(elems[3]),
							              Integer.parseInt(elems[4]),
							              Double.parseDouble(elems[5])));
				ligne = tmpRadiosRead.readLine();
			}			
		}catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. V�rifiez le chemin et nom du fichier.");
		}catch (IOException e) {
			System.out.println("Un probl�me est arriv� lors de la manipulation du fichier. V�rifiez vos donn�es.");
		}catch (Exception e) { 
			System.out.println("Un probl�me est arriv� lors du chargement du fichier. Contactez l'administrateur.");
		}finally {
			tmpRadiosRead.close();
		}
	}
		
	public static void listerRadios() {
		afficherEntete();
		listeRadios.forEach((uneRadio) -> {
			sortie.append(uneRadio.toString());
		});
		sortie.append("\nNombre de radios = "+listeRadios.size());	
		JOptionPane.showMessageDialog(null, sortie, null, 
			JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void listerRadiosParMarque(String marque) {
		afficherEntete();
		listeRadios.forEach((uneRadio) -> {
			if (uneRadio.getMarque().equals(marque)) {
				sortie.append(uneRadio.toString());
			}
		});
		JOptionPane.showMessageDialog(null, sortie, null, 
			JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void modifierPrixRadio(String marque) {
		DecimalFormat df= new DecimalFormat("0.00");
		String prix;
		double taux= Double.parseDouble(
			JOptionPane.showInputDialog(null, "Entrez le taux de changement "+ 
				"pour les radios de marque "+marque+": ",
				"MODIFIER LE PRIX DES RADIOS D'UNE MARQUE", 
				JOptionPane.PLAIN_MESSAGE));
		for (Radio uneRadio: listeRadios) {
			if (uneRadio.getMarque().equals(marque)) {
				prix= df.format(uneRadio.getPrix() + (uneRadio.getPrix()*taux))
						.replace(",", ".");
				uneRadio.setPrix(Double.parseDouble(prix));
			}
		}	
	}
	
	public static void ajouterRadio() {
		String marque= JOptionPane.showInputDialog(null, "Entrez la marque: ", 
			"AJOUT D'UNE NOUVELLE RADIO", JOptionPane.PLAIN_MESSAGE);
		String modele= JOptionPane.showInputDialog(null, "Entrez le mod�le: ", 
			"AJOUT D'UNE NOUVELLE RADIO", JOptionPane.PLAIN_MESSAGE);
		int cd= Integer.parseInt(
			JOptionPane.showInputDialog(null, "Entrez le code pour lecteur cd: ", 
				"AJOUT D'UNE NOUVELLE RADIO", JOptionPane.PLAIN_MESSAGE));
		int cassette= Integer.parseInt(
			JOptionPane.showInputDialog(null, "Entrez le code pour lecteur cassette: ", 
				"AJOUT D'UNE NOUVELLE RADIO", JOptionPane.PLAIN_MESSAGE));
		int mp3= Integer.parseInt(
			JOptionPane.showInputDialog(null, "Entrez le code pour lecteur mp3: ", 
				"AJOUT D'UNE NOUVELLE RADIO", JOptionPane.PLAIN_MESSAGE));
		double prix= Double.parseDouble(
			JOptionPane.showInputDialog(null, "Entrez le prix: ", 
				"AJOUT D'UNE NOUVELLE RADIO", JOptionPane.PLAIN_MESSAGE));
		listeRadios.add(new Radio(marque, modele, cd, cassette, mp3, prix));				
	}
	
	public static void chercherRadio() {
		int pos;
		Radio RadioBidon = new Radio();
		RadioBidon.setMarque("Sony");
		RadioBidon.setModele("WM823");
		pos = listeRadios.indexOf(RadioBidon);
		Radio RadioTrouve = listeRadios.get(pos);
		//
		RadioTrouve.setMP3(1);
		RadioTrouve.setPrix(169.99);
	}
	
	
	
	
	
	
	

}
