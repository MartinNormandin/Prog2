package documents;

import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionLIvres {
	
	static final String FICHIER_LIVRES_TEXTE = "src/donnees/livres.txt";
	static final String FICHIER_LIVRES_OBJ = "src/donnees/livres.obj";
	static JTextArea sortie;
	static ArrayList<Livre> listeLivres;
	static BufferedReader tmpReadTexte;
    static BufferedWriter tmpWriteTexte;
    static ObjectInputStream tmpReadObj;
    static ObjectOutputStream tmpWriteObj;

    static Object biblio;
    static Object obj;
    
    public static int menu() {
		String contenu="1. Lister tous les livres\n"+
	                   "2. Lister en ordre croissant de num�ro\n"+
				       "3. Afficher par num�ro\n"+
	                   "4. Afficher par auteur\n"+
				       "5. Supprimer un livre\n"+
	                   "6. Terminer\n\n";
		contenu+="Choisissez une option: ";
		return Integer.parseInt(JOptionPane.showInputDialog(
			null, contenu, "MENU", JOptionPane.PLAIN_MESSAGE));
	}
    
    public static void chargerLivres() throws Exception {
    	
    	try {
    		String ligne;
    		String elems[] = new String[6];
    		listeLivres = new ArrayList<>();
    		tmpReadTexte = new BufferedReader(new FileReader(FICHIER_LIVRES_TEXTE));
    		ligne = tmpReadTexte.readLine();
    		while (ligne != null) {
    			elems = ligne.split(";");
    			listeLivres.add(new Livre(Integer.parseInt(elems[0]), elems[1], Integer.parseInt(elems[2]), Integer.parseInt(elems[3]), Integer.parseInt(elems[4]), elems[5]))
    			;
    			ligne = tmpReadTexte.readLine();
    		}
    	}catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Verifiez le chemin et nom du fichier.");
		}catch (IOException e) {
			System.out.println("Un probleme est arrive lors de la manipulation du fichier. Verifiez vos donnees.");
		}catch (Exception e) { 
			System.out.println("Un probleme est arrivee lors du chargement du fichier. Contactez l'administrateur.");
		}finally {
			tmpReadTexte.close();
		}
    	
    }
    
    private static void afficherEntete(){
		sortie = new JTextArea();
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));
		sortie.append("\t\tLISTE DES LIVRES\n\n");
		sortie.append("NUMERO\tTITRE\t\tAUTEUR\tANNEE\tPAGES\tCATEGORIE  \n");
	}
    
    public static void listerLivres() {
		afficherEntete();
		listeLivres.forEach((unLivre) -> {
			sortie.append(unLivre.toString());
		});
		sortie.append("\nNombre de livres = "+listeLivres.size());	
		JOptionPane.showMessageDialog(null, sortie, null, 
			JOptionPane.PLAIN_MESSAGE);
	}
    
    public static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", 
			JOptionPane.PLAIN_MESSAGE);
	}
    
    public static void livreTriNum() {
		afficherEntete();
		
		Collections.sort(listeLivres);
		
		listeLivres.forEach((unLivre) -> {
			sortie.append(unLivre.toString());
		});
		sortie.append("Nombre de livres = " + listeLivres.size());
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}
    
    public static void afficherParNumero(int num) {
		afficherEntete();
		listeLivres.forEach((unLivre) -> {
			if (unLivre.getNum() == num) {
				sortie.append(unLivre.toString());
			}
		});
		JOptionPane.showMessageDialog(null, sortie, null, 
			JOptionPane.PLAIN_MESSAGE);
	}
    
    public static void afficherParAuteur(int auteur) {
    	afficherEntete();
    	listeLivres.forEach((unLivre) -> {
    		if (unLivre.getAuteur() == auteur) {
    			sortie.append(unLivre.toString());
    			
    		}
    		
    	});
    	JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
    }
    
    public static void enleverUnLivre() {
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numero du livre a enlever", "ENLEVER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		listeLivres.removeIf((unLivre) -> unLivre.getNum() == num);
	}
    
    public static void sauvegarderFichierObjets(Object listeLivres, String FICHIER_LIVRES_OBJ) throws IOException {
        try {
            tmpWriteObj = new ObjectOutputStream(new FileOutputStream(FICHIER_LIVRES_OBJ));
            tmpWriteObj.writeObject(listeLivres);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
        } catch (IOException e) {
            System.out.println("Un probléme est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
        } catch (Exception e) {
            System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
        } finally {
            tmpWriteObj.close();
        }
    }
	
	public static void chargerLivresObj() throws Exception {
		try {
			tmpReadObj = new ObjectInputStream (new FileInputStream (FICHIER_LIVRES_OBJ));
			listeLivres = (ArrayList<Livre>) tmpReadObj.readObject();
		}catch(FileNotFoundException e)
		{
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		}catch(IOException e)
		{
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		}catch(Exception e)
		{
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		}finally
		{
			tmpReadObj.close();
		}
	}
    
    public static void main(String[] args) throws Exception {
    	int choix;
    	File f = new File(FICHIER_LIVRES_OBJ);
		if (f.exists()) {
			chargerLivresObj();
		}else {
			chargerLivres();
		}
    
		
		do {
			choix = menu();
			switch (choix) {
				case 1:
					listerLivres();
					break;
				case 2:
					livreTriNum();
					break;
				case 3:
					int num = Integer.parseInt(JOptionPane.showInputDialog(
							null, "Entrez un numero: ", "LISTE DES LIVRES PAR NUMERO", 
							JOptionPane.PLAIN_MESSAGE));
						afficherParNumero(num);
					break;
				case 4:
					int auteur = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez un numero d'auteur: ",
							"LISTE DES LIVRES PAR AUTEUR", JOptionPane.PLAIN_MESSAGE));
					afficherParAuteur(auteur);
					break;
				case 5:
					enleverUnLivre();
					break;
				case 6:
					sauvegarderFichierObjets(listeLivres, FICHIER_LIVRES_OBJ);
					afficherMessage("Merci d'avoir utiliser notre systeme");
					break;
				default:
					afficherMessage("Choix invalide. Les options sont [1-6]!");
					break;
			}
		} while (choix != 6);
	}
    
}
    		
    		
    	
	

	

	

