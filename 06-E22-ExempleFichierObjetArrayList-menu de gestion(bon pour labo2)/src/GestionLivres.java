import java.io.*;
import java.util.*;
import javax.swing.*;
public class GestionLivres {
	static final String FICHIER_LIVRES = "src/donnees/livres.txt";
	static ArrayList<Livre> biblio;
	static BufferedReader tmpLivres;
	static JTextArea sortie;
	
	public static void chargerLivres() throws Exception {
		try {
			
			String ligne;
			String elems[] = new String[3];
			biblio = new ArrayList<Livre>();
			tmpLivres  = new BufferedReader(new FileReader(FICHIER_LIVRES));
			ligne = tmpLivres.readLine();//Lire la première ligne du fichier
			while (ligne != null) {//si ligne == null alors on a atteint la fin du fichier
				elems = ligne.split(";");//elems[0] contient le numéro du livre; elems[1] le titre et elems[2] les pages
				biblio.add(new Livre(Integer.parseInt(elems[0]),elems[1], Integer.parseInt(elems[2])));
				ligne = tmpLivres.readLine();
			}
		
		
		}catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier");
			
		}
		catch(IOException e) {
			System.out.println("Un problème est arrivé lors de la manipulation du fichier.");
			
		}catch(Exception e) {
			System.out.println("Un problème est arrivé lors du chargement du fichier. Contacter l'administrateur.");
		}finally {//Exécuté si erreur ou pas
			tmpLivres.close();
			
		
		}
		
	}
	
	public static void afficherListeLivres() {
		sortie = new JTextArea();
		sortie.append("\tLISTE DES LIVRES\n\n");
		sortie.append("NUMÉRO\tTITRE\t\tPAGES\n");
		biblio.forEach((unLivre) -> sortie.append(unLivre.toString()));
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static int menu() {
		String options = "1-Liste des livres\n2-Ajouter un livre\n3-Retirer un livre\n4-Mettre a jour un livre\n5-Terminer\n"+
						"Entrez votre choix\n";
		return Integer.parseInt(JOptionPane.showInputDialog(null, options, "MENU PRINCIPAL", JOptionPane.PLAIN_MESSAGE));
		
		
	}

	public static void main(String[] args) throws Exception {
		int choix;
		chargerLivres();
		do {
			choix = menu();
			switch(choix) {
				case 1: afficherListeLivres();
						break;
				case 2: System.out.println("En phase de développement");
						break;
				case 3: System.out.println("En phase de développement");
						break;
				case 4: System.out.println("En phase de développement");
						break;
				case 5: System.out.println("Merci d'avoir utiliser notre systeme");
						break;
				default:System.out.println("Votre choix est invalide!");
				
			}
		
			
		}while(choix!=5);
		
		
		
		afficherListeLivres();
		
		
		
	}

}
