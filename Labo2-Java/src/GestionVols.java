/* TABLE DE REPÉRAGE DES MÉTHODES
 * Méthodes publiques 
 *     main --> ligne 38
 *     chargerVols --> ligne 339
 *     listeVols --> ligne 307
 *     insererVol --> ligne 273
 *     retirerVol --> ligne 246
 *     modifierDate --> ligne 216
 *     reserverVol --> ligne 183
 *     ecrireFichier --> ligne 322
 * Méthodes privées 
 * 	   menu --> ligne 86
 *     afficherAvert --> ligne 81
 *     rechercherVol --> ligne 104
 *     entrerDestination --> ligne 126
 *     entrerDate --> ligne 136
 *     validerDate --> ligne 163
 *     entrerReservation --> 169 	
 */

import java.util.Collections;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.*;
import java.io.*;

public class GestionVols {
	
	static final int MAX_PLACES = 340;
	static final int MAX_VOLS = 36;
	static final String COMPAGNIE = "CIE AIR RELAX";
	static final String FICHIER_VOLS = "src/Donnees/Cie_Air_Relax.txt";
	static BufferedReader tmpVolsRead;
	static BufferedWriter tmpVolsWrite;
	static JTextArea sortie;
	static ArrayList<Vol> tabVols;

	public static void main(String[] args) throws Exception {
		//
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		UIManager.put("OptionPane.noButtonText", "Non");
		UIManager.put("OptionPane.yesButtonText", "Oui");
		//
		int choix;
		chargerVols();
		//
		do {
			choix= menu();
			switch(choix) {
				case 1:
					listeVols();
					break;
				case 2:
					insererVol();
					break;
				case 3:
					retirerVol();
					break;
				case 4:
					modifierDate();
					break;
				case 5:
					reserverVol();
					break;
				case 6:
					ecrireFichier();
					JOptionPane.showMessageDialog(null,
						"Merci d'avoir utilisé le sytème de gestion de vols "+ 
					    "de "+COMPAGNIE+"!", "Au Revoir",
					    JOptionPane.INFORMATION_MESSAGE);
					break;
				default:
					afficherAvert("Choix invalide!", "ATTENTION");
			}
		}while (choix != 6);
		//
		System.exit(0);
	}
	
	// méthodes privées
	private static void afficherAvert(String msg, String titre) {
		JOptionPane.showMessageDialog(null, msg, titre, 
			JOptionPane.WARNING_MESSAGE);
	}
	
	private static int menu() {
		String msg= "                       GESTION DES VOLS\n"+
				    "1. Liste des vols\n"+
				    "2. Ajout d'un vol\n"+
			        "3. Retrait d'un vol\n"+
	                "4. Modification de la date de départ\n"+
          	        "5. Réservation d'un vol\n"+
			        "6. Terminer\n"+
			        "                       Faites votre choix: ";
		try {
			int choix= Integer.parseInt(JOptionPane.showInputDialog(null, msg, 
				COMPAGNIE, JOptionPane.PLAIN_MESSAGE));
			return choix;
		}catch (NumberFormatException e) {
			return -1;
		}
	}
	
	private static int rechercherVol(String titre, int infoRecherchee) {
		int numVol;
		String msg= "";
		if (infoRecherchee == 1) {
			msg+= "Entrez le numéro du vol à 5 chiffres: ";
		}else {
			msg+= "Pour des fins de sécurité, veuillez "+
			      "réentrez le numéro du nouveau vol à "+
		          "5 chiffres: ";
		}
		do {
			numVol= Integer.parseInt(JOptionPane.showInputDialog(null, msg, 
				titre, JOptionPane.QUESTION_MESSAGE));
		}while (String.valueOf(numVol).length() != 5);
		Vol VolBidon= new Vol(numVol);
		int pos= tabVols.indexOf(VolBidon);
		if (infoRecherchee != 1 && pos != -1) {
			return 1;
		}
		return (infoRecherchee == 1) ? pos : numVol;
	}
	
	private static String entrerDestination(String titre) {
		String dest;
		String msg= "Entrez la destination du vol: ";
		do {
			dest= JOptionPane.showInputDialog(null, msg, titre, 
				JOptionPane.QUESTION_MESSAGE);
		}while (dest.chars().allMatch(Character::isDigit));
		return dest;	
	}
	
	private static int[] entrerDate(String infoVol, String titre) {
		int elemsDate[]= new int[3];
		JTextField jour= new JTextField();
		JTextField mois= new JTextField();
		JTextField annee= new JTextField();
		Object champs[]= {
			infoVol,
			"\nJour du départ: ", jour,
			"Mois du départ: ", mois,
			"Année du départ: ", annee };
		do {
			JOptionPane.showConfirmDialog(null, champs, titre, 
				JOptionPane.PLAIN_MESSAGE);
		}while ( (!jour.getText().chars().allMatch(Character::isDigit)
				   || jour.getText().isEmpty())                         
				                                                        ||		                                                       
				 (!mois.getText().chars().allMatch(Character::isDigit)  
				   || mois.getText().isEmpty())		                                               
						                                                ||
				 (!annee.getText().chars().allMatch(Character::isDigit) 
			       || annee.getText().isEmpty()) );
		elemsDate[0]= Integer.parseInt(jour.getText());
		elemsDate[1]= Integer.parseInt(mois.getText());
		elemsDate[2]= Integer.parseInt(annee.getText());
		return elemsDate;
	}
	
	private static String validerDate(int jour, int mois, int annee) {
		boolean etat[]= new boolean[3];
		String msg= Date.validerDate(jour, mois, annee, etat);
		return msg;
	}
	
	private static int entrerReservation(String infoVol, String titre, int p) {
		infoVol+= "\nIl y a présentement "+p+" siège(s) "+
				  "disponible(s) pour ce vol.";
		infoVol+= "\nCombien de sièges voulez-vous réserver? ";
		String res;
		do {
			res= JOptionPane.showInputDialog(null, infoVol, titre, 
				JOptionPane.QUESTION_MESSAGE);
		}while (!res.chars().allMatch(Character::isDigit)
				 || res.isEmpty() );
		return Integer.parseInt(res);
	}
	
	// méthodes publiques
	public static void reserverVol() {
		if (!tabVols.isEmpty()) {
			try {
				String titre= "RÉSERVATION D'UN VOL";
				int pos= rechercherVol(titre, 1);
				if (pos != -1) {
					int places= MAX_PLACES - tabVols.get(pos).getNbRes();
					if (places == 0) {
						afficherAvert("Ce vol est complet!", "ATTENTION");
					}else {
						String infoVol= tabVols.get(pos).toString()
								.replace("\t", "  ");
						int res= entrerReservation(infoVol, titre, places);
						if (places-res < 0) {
							afficherAvert(
								"Nombre de sièges disponibles insuffisant!",
								"ATTENTION");
						}else {
							int nbRes= tabVols.get(pos).getNbRes();
							tabVols.get(pos).setNbRes(nbRes+res);
						}
					}				
				}else {
					afficherAvert("Ce vol n'existe pas!", "ATTENTION");
				}
			}catch (NumberFormatException e) {
				afficherAvert("Numéro de vol invalide!", "ATTENTION");
			}			
		}else {
			afficherAvert("Le tableau des vols est vide!", "ATTENTION");
		}
	}
	
	public static void modifierDate() {
		if (!tabVols.isEmpty()) {
			try {
				String titre= "MODIFICATION DE LA DATE DE DÉPART";
				int pos= rechercherVol(titre, 1);
				if (pos != -1) {
					String infoVol= tabVols.get(pos).toString()
						.replace("\t", "  ");
					int elemsDate[]= new int[3];
					elemsDate= entrerDate(infoVol, titre);
					String msg= validerDate(elemsDate[0], elemsDate[1], 
						elemsDate[2]);
					if (msg.isEmpty()) {
						Date dateInstance= new Date(elemsDate[0], elemsDate[1], 
							elemsDate[2]);
						tabVols.get(pos).setDepart(dateInstance);
					}else {
						afficherAvert(msg, "ATTENTION");
					}					
				}else {
					afficherAvert("Ce vol n'existe pas!", "ATTENTION");
				}
			}catch (NumberFormatException e) {
				afficherAvert("Numéro de vol invalide!", "ATTENTION");
			}			
		}else {
			afficherAvert("Le tableau des vols est vide!", "ATTENTION");
		}
	}		
	
	public static void retirerVol() {
		if (!tabVols.isEmpty()) {
			try {
				String titre= "RETRAIT D'UN VOL";
				int pos= rechercherVol(titre, 1);
				if (pos != -1) {
					String infoVol= tabVols.get(pos).toString()
						.replace("\t", "  ");
					infoVol+= "\nDésirez-vous vraiment retirer ce vol ?";					
					int choix= JOptionPane.showConfirmDialog(null, infoVol, 
						titre, JOptionPane.YES_NO_OPTION, 
						JOptionPane.PLAIN_MESSAGE);		
					if (choix == JOptionPane.YES_OPTION) {
						tabVols.remove(pos);
						Vol.nbVols--;
					}
				}else {
					afficherAvert("Ce vol n'existe pas!", "ATTENTION");		
				}	
			}catch (NumberFormatException e) {
				afficherAvert("Numéro de vol invalide!", "ATTENTION");
			}			
		}else {
			afficherAvert("Le tableau des vols est vide!", "ATTENTION");
		}
	}
		
	public static void insererVol() {
		if (tabVols.size() < MAX_VOLS) {
			try {
				String titre= "AJOUT D'UN VOL";
				int pos= rechercherVol(titre, 1);
				if (pos == -1) {
					String dest= entrerDestination(titre);
					int elemsDate[]= new int[3];
					elemsDate= entrerDate("", titre);
					String msg= validerDate(elemsDate[0], elemsDate[1], 
						elemsDate[2]);
					if (msg.isEmpty()) {
						Date dateInstance= new Date(elemsDate[0], elemsDate[1], 
							elemsDate[2]);
						int numVol= rechercherVol(titre, 0);
						if (numVol == 1) {
							afficherAvert("Ce vol existe déjà!","ATTENTION");
						}else {
							tabVols.add(new Vol(numVol, dest, dateInstance,0));
						}
					}else {
						afficherAvert(msg, "ATTENTION");
					}
				}else {
					afficherAvert("Ce vol existe déjà!", "ATTENTION");		
				}
			}catch (NumberFormatException e) {
				afficherAvert("Numéro de vol invalide!", "ATTENTION");
			}			
		}else {
			afficherAvert("Nombre maximum de vols atteint!", "ATTENTION");
		}
	}

	public static void listeVols() {
		sortie= new JTextArea();
		Font f= new Font("Courier New", Font.PLAIN, 12);
		sortie.setFont(f);
		sortie.append("\t\t\tLISTE DES VOLS\n\n"+
				      "Numéro\tDestination\t\tDépart\t\tRéservations\n");
		Collections.sort(tabVols);
		for (Vol unVol : tabVols) {
			sortie.append(unVol.toString());
		}
		sortie.append("\nNombre de vols: "+Vol.nbVols);
		JOptionPane.showMessageDialog(null, sortie, COMPAGNIE, 
			JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void ecrireFichier() throws Exception {
		try {
			tmpVolsWrite= new BufferedWriter(new FileWriter(FICHIER_VOLS));
			String ligne;
			for (Vol unVol : tabVols) {
				ligne= unVol.ecritureFichier();
				tmpVolsWrite.write(ligne);
			}
		}catch (Exception e) {
			System.out.println("Un problème est subvenu lors de l'écriture "+
		                       "du fichier. Contactez l'administrateur.");
			e.printStackTrace();
		}finally {
			tmpVolsWrite.close();
		}
	}

	public static void chargerVols() throws Exception {
		try {
			tabVols= new ArrayList<Vol>();
			tmpVolsRead= new BufferedReader(new FileReader(FICHIER_VOLS));
			String elemsVol[]= new String[4];
			String elemsDate[]= new String[3];
			String ligne= tmpVolsRead.readLine();
			while (ligne != null) {
				elemsVol= ligne.split(";");
				elemsDate= elemsVol[2].split(" ");
				Date dateInstance= new Date(Integer.parseInt(elemsDate[0]),
						                    Integer.parseInt(elemsDate[1]),
						                    Integer.parseInt(elemsDate[2]));
				tabVols.add(new Vol(Integer.parseInt(elemsVol[0]),
						            elemsVol[1], dateInstance,
						            Integer.parseInt(elemsVol[3])));
				ligne= tmpVolsRead.readLine();
			}
		}catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et "+
		                       "nom du fichier.");
		}catch (IOException e) {
			System.out.println("Un problème est subvenu lors de la "+
		                       "manipulation du fichier. Vérifiez vos "+
					           "données.");
		}catch (Exception e) {
			System.out.println("Un problème est subvenu lors du chargement "+
		                       "du fichier. Contactez l'administrateur.");
		}finally {
			tmpVolsRead.close();
		}
	}

}
