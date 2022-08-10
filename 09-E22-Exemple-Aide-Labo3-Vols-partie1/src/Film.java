import java.io.Serializable;

public class Film implements Serializable, Comparable<Film> {
		
	private static final long serialVersionUID = 2040890116313738088L;
	
	public static final int MAX_FILMS = 30;
	public static int nbFilms = 0;
	
	private int numFilm;
	private String titre;
	private int duree;
	
	Film(){
		this.numFilm=-1;
		this.titre="Titre inconnu";
		++nbFilms;
	}
	
	Film (int numFilm, String titre, int duree){
		this.numFilm=numFilm;
		this.titre=titre;
		setDuree(duree);
		++nbFilms;
	}
	
	public int getNumFilm() {
		return this.numFilm;
	}
	public String getTitre() {
		return this.titre;
	}
	public int getDuree() {
		return this.duree;
	}
	
	public void setTitre(String titre) {
		this.titre=titre;
	}
	public void setDuree(int duree) {
		if(duree >= 30) {
			this.duree=duree;
		}else {
			System.out.println("Dur�e du film trop courte");
		}
	}
	
	@Override
    public int compareTo(Film leFilm) {
        return (int)(this.numFilm - leFilm.getNumFilm());
    }
	
	public String toString() {
		return "\nNuméro du film = "+this.numFilm+"\nTitre = "+this.titre+"\nDur�e = "+this.duree;
	}
}


