import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GestionLivres {
	static final String FICHIER_LIVRES = "src/donnees/livres.txt";
	static List<Object> biblio; 
	static BufferedReader tmpLivres; 
	static JTextArea sortie;
	
	/* public static void chargerLivres() throws Exception {
		try {
			String ligne;
			String elems[] = new String[3];
			biblio = new ArrayList<Livre>();
			tmpLivres = new BufferedReader(new FileReader(FICHIER_LIVRES));
			ligne = tmpLivres.readLine();//Lire la première ligne du fichier
			while (ligne != null) {//Si ligne == null alors ont a atteint la fin du fichier
				elems = ligne.split(";");//elems[0] contient le numéro du livre;elems[1] le titre et elems[2] les pages
				biblio.add(new Livre(Integer.parseInt(elems[0]), elems[1], Integer.parseInt(elems[2])));
				ligne = tmpLivres.readLine();
			}//fin while
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		}
		catch (IOException e) {
			System.out.println("Un problème est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
		}catch (Exception e) { 
			System.out.println("Un problème est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		}finally {//Exécuté si erreur ou pas
			tmpLivres.close();
		}
	}*/
	
	public static void afficherListeLivres() {
		String espaces = Utilitaires.ajouterEspacesDebut(50, "TITRE");
		String titre=Utilitaires.ajouterEspacesDebut(50, "LISTE DES LIVRES")+"LISTE DES LIVRES";
		String colonnes = "NUMÉRO"+espaces+"TITRE"+espaces+"PAGES";
		sortie = new JTextArea();
		sortie.append(titre+"\n\n");
		sortie.append(colonnes+"\n");
		biblio.forEach((unLivre) ->{sortie.append(unLivre.toString()); sortie.append("\n");});	
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static int menu() {
		String options="1-Lister les livres\n2-Ajouter un livre\n3-Retirer un livre\n";
		options+="4-Mettre à jour un livre\n5-Terminer\nEntrez votre choix\n";
		return Integer.parseInt(JOptionPane.showInputDialog(null,options,"MENU PRINCIPAL",JOptionPane.PLAIN_MESSAGE));
	}
	
	public static void montrerMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);	
	}
    
	public static void main(String[] args) throws Exception {
		int choix;
		boolean tab[]= new boolean[3];
		biblio = new ArrayList<Object>();
		Utilitaires.chargerFichierText(Utilitaires.FICHIER_DONNEES, ";", biblio, 'L');
		do {
			choix = menu();
			switch(choix) {
				case 1: 	afficherListeLivres();
								break;
				case 2: 	//montrerMessage("En phase de développement");
					montrerMessage(Date.validerDate(31, 6, 2022,tab));
								
								break;
				case 3: 	montrerMessage("En phase de développement");
								break;
				case 4: 	montrerMessage("En phase de développement");
								break;
				case 5: 	montrerMessage("Merci d'avoir utilisé notre système");
								break; 	
				default :   montrerMessage("Votre choix est invalide !");
								break;				
			}
		} while(choix != 5);

	}

}
