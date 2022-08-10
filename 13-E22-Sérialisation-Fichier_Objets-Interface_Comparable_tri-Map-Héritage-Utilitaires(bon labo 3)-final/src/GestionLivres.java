import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionLivres {
	static final String FICHIER_LIVRES_TEXTE = "src/donnees/livres.txt";
	static final String FICHIER_LIVRES_OBJ = "src/donnees/livres.obj";
	static JTextArea sortie;
	static List<Livre> biblioList = new ArrayList<Livre>();
	//static Map<Integer, Livre> biblioMap = new TreeMap<Integer, Livre>();
	static Map<Integer, Livre> biblioMap = new HashMap<Integer, Livre>();

	public static int menuGeneral() {
		String contenu = "1-Lister\n2-Ajouter un livre\n3-Enlever un livre\n4-Lister par catégorie\n5-Modifier un livre\n6-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-6] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU GESTION LIVRES", JOptionPane.PLAIN_MESSAGE));
	}

	public static int menuModifier() {
		String contenu = "1-Titre\n2-Pages\n3-Catégorie\n4-Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-4] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU GESTION LIVRES", JOptionPane.PLAIN_MESSAGE));
	}
	

	public static void afficherEntete() {
		sortie = new JTextArea(20, 50);
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));
		sortie.append("\t\tLISTE DES LIVRES\n\n");
		sortie.append("NUMÉRO\tTITRE\t\tPAGES\tCATÉGORIE\n");
	}

	public static void listerLivresList() {
		afficherEntete();
		//biblioList.sort(null);
		Collections.sort(biblioList);
		
		biblioList.forEach((unLivre) -> {
			sortie.append(unLivre.toString());
		});
		sortie.append("Nombre de livres = " + biblioList.size());
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static ArrayList<Integer> trierClesHashMap(Map<Integer, Livre> leMapAtrier){
		ArrayList<Integer> listeCles = new ArrayList<Integer>();
		for(Integer cle : leMapAtrier.keySet()) {
			listeCles.add(cle);
		}
		Collections.sort(listeCles); 
		return listeCles;
	}

	public static void listerLivresMapTrie() {
		ArrayList<Integer> listeClesTriees = new ArrayList<Integer>();
		listeClesTriees = trierClesHashMap(biblioMap);
		afficherEntete();
		for(Integer cle : listeClesTriees){
			sortie.append(biblioMap.get(cle).toString());
		}

		sortie.append("Nombre de livres = " + biblioMap.size());
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static void listerLivresMap() {
		afficherEntete();
		for(Integer cle : biblioMap.keySet()) {
			sortie.append(biblioMap.get(cle).toString());
		}

		sortie.append("Nombre de livres = " + biblioMap.size());
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}

	public static void ajouterUnLivreList() {
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		String titre = JOptionPane.showInputDialog(null, "Entrez le titre", "AJOUTER UN LIVRE",
				JOptionPane.PLAIN_MESSAGE);
		int pages = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre de pages", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		int categorie = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez la catégorie", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		biblioList.add(new Livre(num, titre, pages, categorie));
	}

	public static void ajouterUnLivreMap() {
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		String titre = JOptionPane.showInputDialog(null, "Entrez le titre", "AJOUTER UN LIVRE",
				JOptionPane.PLAIN_MESSAGE);
		int pages = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre de pages", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		int categorie = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez la catégorie", "AJOUTER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		biblioMap.put(num, new Livre(num, titre, pages, categorie));
	}

	public static void enleverUnLivreList() {
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "ENLEVER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		biblioList.removeIf((unLivre) -> unLivre.getNum() == num);
	}

	public static void enleverUnLivreMap() {
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "ENLEVER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		if(biblioMap.containsKey(num)){
			biblioMap.remove(num);
		} else {
			afficherMessage("Livre introuvable");
		}
	}

	static int cptCategs = 0;
	
	public static void listerLivresParCategorieList() {
		int categorie = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez la catégorie", "LISTER DES LIVRES PAR CATÉGORIE",
						JOptionPane.PLAIN_MESSAGE));
		afficherEntete();
		
		biblioList.forEach((unLivre) -> {
			if (unLivre.getCategorie() == categorie) {
				sortie.append(unLivre.toString());
				++cptCategs;
			}
		});
		sortie.append("Nombre de livres = " + cptCategs);
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static void listerLivresParCategorieMap() {
		cptCategs = 0;
		int categorie = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez la catégorie", "LISTER DES LIVRES PAR CATÉGORIE",
						JOptionPane.PLAIN_MESSAGE));
		afficherEntete();
		for (Integer cle : biblioMap.keySet()) {
			if(biblioMap.get(cle).getCategorie() == categorie){
				sortie.append(biblioMap.get(cle).toString());
				++cptCategs;
			}
		}
		
		sortie.append("Nombre de livres = " + cptCategs);
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static Livre rechercherLivreList(int num) {
		int pos;
		Livre livreBidon = new Livre();
		livreBidon.setNum(num);
		pos = biblioList.indexOf(livreBidon);// -1 si pas trouvé
		if (pos == -1) {
			afficherMessage("Le numéro du livre est introuvable.");
			return null;
		}
		Livre livreTrouve = biblioList.get(pos);
		return livreTrouve;
	}
public static void modifierUnLivreMap() {
	int num = Integer
			.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "MODIFIER UN LIVRE",
					JOptionPane.PLAIN_MESSAGE));
	if(biblioMap.containsKey(num)){
		modifierUnLivre(biblioMap.get(num));
	}else {
		afficherMessage("Livre introuvable");
	}
}
	public static void modifierUnLivreList() {	
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "MODIFIER UN LIVRE",
						JOptionPane.PLAIN_MESSAGE));
		Livre livreTrouve = rechercherLivreList(num);
		if (livreTrouve == null) {
			afficherMessage("Livre introuvable");
		}else {
			modifierUnLivre(livreTrouve);
		}
	}
		
	public static void modifierUnLivre(Livre livreTrouve) {
		int choix;
		do {
			choix = menuModifier();
			switch (choix) {
				case 1:
					String titre = JOptionPane.showInputDialog(null, "Entrez le nouveau titre", "AJOUTER UN LIVRE",
							JOptionPane.PLAIN_MESSAGE);
					livreTrouve.setTitre(titre);
					break;
				case 2:
					int pages = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Entrez le nouveau nombre de pages",
									"MODIFIER UN LIVRE", JOptionPane.PLAIN_MESSAGE));
					livreTrouve.setPages(pages);
					break;
				case 3:
					int categorie = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Entrez la nouvelle catégorie",
									"MODIFIER UN LIVRE", JOptionPane.PLAIN_MESSAGE));
					livreTrouve.setCategorie(categorie);
					break;
				case 4:
					break;
				default:
					afficherMessage("Choix invalide. Les option sont [1-4] !");
					break;

			}
		} while (choix != 4);
	}

	static String ligne = "";

	public static void chargerFichierPourUneList(){
		try{
			File f = new File(FICHIER_LIVRES_OBJ);
			
			if (f.exists()) {
				biblioList =  (ArrayList<Livre>) Utilitaires.chargerFichierObjets(FICHIER_LIVRES_OBJ);
			}else {
				ArrayList<ArrayList<String>> listeAttributs = Utilitaires.chargerFichierTexte(FICHIER_LIVRES_TEXTE, ";");
				listeAttributs.forEach((donneesLivre) -> {
					int num;
					String titre;
					int pages;
					int categorie;
					num = Integer.parseInt(donneesLivre.get(0));
					titre = donneesLivre.get(1);
					pages = Integer.parseInt(donneesLivre.get(2));
					categorie = Integer.parseInt(donneesLivre.get(3));
					biblioList.add(new Livre(num, titre, pages, categorie));
				});
			}
		} catch(Exception e){
			System.out.println("Problème");
		}
	}

	public static void chargerFichierPourUnMap() {
		try{
			File f = new File(FICHIER_LIVRES_OBJ);

			if (f.exists()) {
				biblioMap = (HashMap<Integer, Livre>) Utilitaires.chargerFichierObjets(FICHIER_LIVRES_OBJ);
			} else {
				ArrayList<ArrayList<String>> listeAttributs = Utilitaires.chargerFichierTexte(FICHIER_LIVRES_TEXTE, ";");
				listeAttributs.forEach((donneesLivre) -> {
					int num;
					String titre;
					int pages;
					int categorie;
					num = Integer.parseInt(donneesLivre.get(0));
					titre = donneesLivre.get(1);
					pages = Integer.parseInt(donneesLivre.get(2));
					categorie = Integer.parseInt(donneesLivre.get(3));
					biblioMap.put(num, new Livre(num, titre, pages, categorie));
				});
				biblioMap = ((TreeMap<Integer, Livre>) biblioMap).descendingMap();//Notre Map en ordre décroissant
			}
		}catch(Exception e){
			System.out.println("Problème");
		}
	}

	public static void main(String[] args) throws Exception {
		int choix;
		chargerFichierPourUnMap();
		do {
			choix = menuGeneral();
			switch (choix) {
				case 1:
					listerLivresMap();
					//listerLivresMapTrie();
					break;
				case 2:
					ajouterUnLivreMap();
					break;
				case 3:
					enleverUnLivreMap();
					break;
				case 4:
					listerLivresParCategorieMap();
					break;
				case 5:
					modifierUnLivreMap();
					break;
				case 6:
					Utilitaires.sauvegarderFichierObjets(biblioMap, FICHIER_LIVRES_OBJ);
					afficherMessage("Merci d'avoir utilisé notre système");
					break;
				default:
					afficherMessage("Choix invalide. Les option sont [1-6] !");
					break;
			}
		} while (choix != 6);
	}
}
