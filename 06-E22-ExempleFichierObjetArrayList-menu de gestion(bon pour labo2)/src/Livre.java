import java.io.*;
public class Livre {
	
	//Attributs de classe
	public static int nbLivres = 0;

	//Attributs d'instance
	private int num;
	private String titre;
	private int pages;
	
	//Constructeur par default
	Livre(){
		++nbLivres;
	}
	
	/*//Constructeur par parametres
	Livre(int num, String titre, int pages) {
		this.num = num;
		this.titre = titre;
		this.pages = pages;
		++nbLivres;*/
		
	//Constructeur par parametres
	Livre(int num, String titre, int pages) {
		this.setNum(num);
		this.titre = titre;
		this.setPages(pages);
		++nbLivres;
	}
	
	Livre(int num, String titre){
		this(num, titre, 0);//Appel au constructeur à 3 paramètres
	}
	
	//Constructeur de copie
	Livre(Livre unLivre) {
		this.num = unLivre.num;
		this.titre = unLivre.titre; // Constructeur de copie, alternative: utiliser nomObject.clone
		this.pages = unLivre.pages;
		++nbLivres;
	}
	
	//Les accesseurs
	public int getNum() {
		return this.num;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	//Les mutateurs
	public void setNum(int num) {
		if (num > 0) {
			this.num = num;
		}else {
			System.out.println("numero de livre invalide!");
		}
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public void setPages(int pages) {
		if (pages > 0) {
			this.pages = pages;
		}else {
			System.out.println("nombre de pages invalide");
		}
	}
	
	//Retourner le contenu d'un object selon un format voulu
	public String toString() {
		if(this.titre.length() > 17) {
		return this.num+"\t"+this.titre+"\t"+this.pages+"\n";
		}else{
		return this.num+"\t"+this.titre+"\t\t"+this.pages+"\n";}
	}
	
}
