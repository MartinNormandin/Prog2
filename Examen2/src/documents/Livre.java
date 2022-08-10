package documents;

import java.io.*;

public class Livre implements Serializable, Comparable<Livre> {
	
	private static final long serialVersionUID = 2040890116313738088L;
	
	public static int nbLivres = 0;
	
	private int num;
	private String titre;
	private int auteur;
	private int annee;
	private int pages;
	private String categorie;
	
	Livre() {
		++nbLivres;
	}
	
	Livre(int num, String titre, int auteur, int annee, int pages, String categorie) {
		this.num = num;
		this.titre = titre;
		this.auteur = auteur;
		this.annee = annee;
		this.pages = pages;
		this.categorie = categorie;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getAuteur() {
		return this.auteur;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	public String getCategorie() {
		return this.categorie;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public void setAuteur(int auteur) {
		this.auteur = auteur;
	}
	
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	

	public int compareTo(Livre unLivre) {
		// Par num livre
		return (int) (this.num - unLivre.num);// En ordre croissant
		// return (int) (unLivre.num - this.num);//En ordre décroissant
		// Par titre
		// return this.titre.compareTo(unLivre.titre);
	}
	
	public String toString() {
		
			return (this.titre.length() > 15)
					? this.num + "\t" + this.titre.substring(0,15)+"..."+ "\t" + this.auteur + "\t" + this.annee + "\t" + this.pages + "\t" + this.categorie + "\n"
					: this.num + "\t" + this.titre+"\t\t" + this.auteur + "\t" + this.annee + "\t" + this.pages + "\t" + this.categorie + "\n";
	}

}
