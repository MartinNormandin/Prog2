package documents;
import java.io.*;
import java.text.DecimalFormat;

public class Radio {
	
	public static int nbRadios = 0;
	public static DecimalFormat df= new DecimalFormat("0.00 $");
	
	
	private String marque;
	private String modele;
	private int cd;
	private int cassettes;
	private int mp3;
	private double prix;
	
	Radio(){
		++nbRadios;
	}
	
	Radio(String marque, String modele, int cd, int cassettes, int mp3, double prix){
		this.marque = marque;
		this.modele = modele;
		this.cd = cd;
		this.cassettes = cassettes;
		this.mp3 = mp3;
		this.prix = prix;
		
	}
	
	public String getMarque() {
		return this.marque;
	}
	
	public String getModele() {
		return this.modele;
	}
	
	public int getCd() {
		return this.cd;
	}
	
	public int getCassettes() {
		return this.cassettes;
	}
	
	public int getMp3() {
		return this.mp3;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public void setMarque (String marque) {
		this.marque = marque;
	}
	
		
	public void setModele (String modele) {
		this.modele = modele;
	}
	
	public void setCd(int cd) {
		this.cd = cd;
	}
	
	public void setCassettes(int cassettes) {
		this.cassettes = cassettes;
	}
	
	public void setMp3 (int mp3) {
		this.mp3 = mp3;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	public String toString() {
		return this.marque+"\t"+this.modele+"\t"+this.cd+"\t"+this.cassettes+"\t"+this.mp3+"\t"+df.format(this.prix)+"\n";
	}

}
