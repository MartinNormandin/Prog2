import java.io.*;
import java.util.*;

public class GestionFilms {
	
	static BufferedReader fic;
	
	static Map<Integer, Film> listeFilms = new HashMap<Integer, Film>();
	//Représente listeFilms triée selon le compareTo d�finit dans la classe Film
	static TreeMap<Integer, Film> listeFilmsTriee = new TreeMap<Integer, Film>(listeFilms);
	
	public static void listerFilms() {
		for(Integer cle : listeFilms.keySet()) {
			System.out.println(listeFilms.get(cle));
			System.out.println("******************");
		}
		System.out.println("Vous avez au total "+Film.nbFilms+" films");
	} 
	
	public static void chargerListeFilms() throws IOException {
		File f = new File(Utilitaires.FICHIER_OBJETS_FILM);
		if (!f.exists()) {
			Utilitaires.chargerFichierText(Utilitaires.FICHIER_TEXTE_FILM, ";", listeFilms);
		}else {
			Utilitaires.chargerFichierObjet(Utilitaires.FICHIER_OBJETS_FILM, listeFilms);
		}
	}

	public static boolean trouverFilm(int filmTrouver)
	{
		return listeFilms.containsKey(filmTrouver);
	}
	
	public static void main(String[] args)throws IOException {
		chargerListeFilms();
		fic= new BufferedReader(new InputStreamReader(System.in));
		
		boolean continuer=true;
		while(continuer) {
			//Traitement
			listerFilms();
			System.out.print("Voulez-vous continuer O-Oui autre Non : ");
			continuer= (fic.readLine().toUpperCase().charAt(0)=='O');
		}
		Utilitaires.enregistrerObjetsFilms(Utilitaires.FICHIER_OBJETS_FILM, listeFilms);
		listeFilms.clear(); //vider la liste des films
		System.out.print("Fin de l'enregistrement du fichier d'objets");
	}
}
