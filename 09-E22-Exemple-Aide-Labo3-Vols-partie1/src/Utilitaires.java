import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utilitaires {
	static BufferedReader fic;
	static ObjectOutputStream ficObjEcrire;
	static ObjectInputStream ficObjLire;
	
	public static final String MSG1 = " ne peut pas �tre inf�rieure � l'ann�e actuelle, soit ";
	public static final String MSG2 = "Impossible de valider le jour puisque votre mois est invalide";
	public static final String FICHIER_OBJETS_FILM = "src/donnees/films.obj";
	public static final String FICHIER_TEXTE_FILM = "src/donnees/films.txt";
	
	public static void chargerFichierText(String fichier, String delimiteur, Map<Integer, Film> listeFilms) throws IOException {
		String ligne;
		int cptFilms=0;
		String tab[] = new String[3];
		fic = new BufferedReader(new FileReader(fichier));
		
		//Cas adapté pour notre Labo3
		while ((ligne=fic.readLine())!=null && cptFilms < Film.MAX_FILMS) {
			tab=ligne.split(delimiteur);//tab[0] le num�ro du film, tab[1] le titre et tab[2] la dur�e
			int numFilm=Integer.parseInt(tab[0]);
			listeFilms.put(numFilm, new Film(numFilm, tab[1], Integer.parseInt(tab[2])));
			++cptFilms;
		}
		fic.close();
	}
	
	public static void enregistrerObjetsFilms(String fichierObj, Map<Integer, Film> listeFilms) throws IOException {
		ficObjEcrire = new ObjectOutputStream (new FileOutputStream (fichierObj));
		for(Integer cle : listeFilms.keySet()) {
			ficObjEcrire.writeObject(listeFilms.get(cle));
		}
		ficObjEcrire.close();
	}
	
	
	public static void chargerFichierObjet(String fichierObj, Map<Integer, Film> listeFilms) throws IOException {
		
			ficObjLire = new ObjectInputStream (new FileInputStream (fichierObj));
			listeFilms.clear();
			Film unFilm;
			try {
				while(true) {//Pour determiner la fin du ficher. On sert par une IOException
					unFilm = (Film) ficObjLire.readObject();
					listeFilms.put(unFilm.getNumFilm(), unFilm);
				}
			}catch (IOException e) {
				//Rien � faire. Tout le fichier a �t� lu
			}catch (Exception e){
				System.out.println("Erreur d'entr�e/sortie du fichier "+fichierObj);
			}finally{
				ficObjLire.close();
			}
	}
}
