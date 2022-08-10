
import java.text.DecimalFormat;

public class Vin {
	
	// attributs de classe
	public static final String TAB_TYPES[]= {"rouge", "blanc", "ros�"};
	public static DecimalFormat df= new DecimalFormat("0.00 $");
	public static int nbVins= 0;
	public static double prixTotal= 0;
	
	// attributs d'instance	
	private int type;
	private double prix;
	private String nom, origine;
	
	// constructeur de vin rouge	
	Vin(String nom, String origine, double prix) { 
		this(nom, 1, origine, prix);}
	
	// constructeur par 4 paramètres	
	Vin(String nom, int type, String origine, double prix) {
		this.nom= nom;
		this.type= type;
		this.origine= origine;
		this.prix= prix;
		++nbVins;
		prixTotal += this.prix;}
	
	// accesseurs
	public String getNom() {
		return this.nom;}
	
	public int getType() {
		return this.type;}
	
	public String getOrigine() {
		return this.origine;}
	
	public double getPrix() {
		return this.prix;}
	
	// mutateurs
	public void setNom(String nouveauNom) {
		this.nom= nouveauNom;}
	
	public void setType(int nouveauType) {
		this.type= nouveauType;}
	
	public void setOrigine(String nouvelleOrigine) {
		this.origine= nouvelleOrigine;}
	
	public void setPrix(double nouveauPrix) {
		prixTotal -= this.prix;
		this.prix= nouveauPrix;
		prixTotal += this.prix;}
	
	// autres méthodes
	private String afficheCouleur() {
		try {
			return TAB_TYPES[this.type-1];
		}catch (ArrayIndexOutOfBoundsException e) {
			return "{type invalide}";}}
	
	public String toString() {
		String couleur = this.afficheCouleur();
		return this.nom+" est un vin "+couleur+" de "+this.origine+
			" et son prix est de "+df.format(this.prix)+"\n";}
}