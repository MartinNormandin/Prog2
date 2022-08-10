import javax.swing.*;
import java.io.*;

public class TestProduit {
	
	static final String FICHIER_PRODUITS= "src/data/produits.txt";
	static final int NB_MAX_PRODUITS= 100;
	static Produit tabProduits[];
	static BufferedReader tmpProd;
	static JTextArea sortie;
	static int tabProdQte[];
	static double profitJournee= 0;

	public static void main(String[] args) throws IOException {
		chargerProduits();
		//
		tabProdQte= new int[Produit.nbProd];
		char reponse;
		do {
			reponse= traiterClients();
		}while (reponse == 'O');
		//
		afficherResultats();
		System.exit(0);}
	
	//
	public static void chargerProduits() throws IOException {
		int i=0;
		String ligne;
		String elems[]= new String[2];
		tabProduits= new Produit[NB_MAX_PRODUITS];
		tmpProd= new BufferedReader(new FileReader(FICHIER_PRODUITS));
		//
		ligne= tmpProd.readLine();
		while (i < NB_MAX_PRODUITS && ligne != null) { 
			elems= ligne.split(";");
			tabProduits[i++]= new Produit(Integer.parseInt(elems[0]), 
				Double.parseDouble(elems[1]));
			ligne= tmpProd.readLine();}			
		tmpProd.close();}
	
	//
	public static char traiterClients() {
		int position;
		double cout;
		char rep;
		JTextField num= new JTextField();
		JTextField qte= new JTextField();
		Object[] fields= {
			"numéro du produit acheté", num,
			"quantité achetée", qte};
		//
		JOptionPane.showConfirmDialog(null, fields, "Fenêtre d'achat",
			JOptionPane.PLAIN_MESSAGE);
		//
		position= rechercheTableau(num.getText());
		if (position != -1) {
			tabProdQte[position] += Integer.parseInt(qte.getText());
			cout= tabProduits[position].getPrixProd() * 
				  Double.parseDouble(qte.getText());
			profitJournee += cout;
			JOptionPane.showMessageDialog(null, 
				"Le coût de cet achat est de "+Produit.df.format(cout),
				"Fenêtre de facturation", JOptionPane.PLAIN_MESSAGE);			
		}else {
			JOptionPane.showMessageDialog(null, "Numéro de produit invalide",
				"Erreur", JOptionPane.PLAIN_MESSAGE);}
		//
		rep= JOptionPane.showInputDialog(
			"Avez-vous un autre client a traiter O/N ?").charAt(0);
		rep= Character.toUpperCase(rep);
		return rep;}
	
	//
	public static int rechercheTableau(String numStr) {
		int num = Integer.parseInt(numStr);
		int pos= -1;
		boolean trouve= false;
		//
		for (int i=0; i < Produit.nbProd && !trouve; i++) {
			if (tabProduits[i].getNumProd() == num) {
				pos= i;
				trouve= true;}}
		return pos;}
	
	//
	public static void afficherResultats() {
		sortie= new JTextArea();
		sortie.append("Profits de la journée: "+
			Produit.df.format(profitJournee)+"\n\n");
		sortie.append("Numéro du\t\tQuantité\n");
		sortie.append("produit\t\ttotale\n");
		sortie.append("vendu\t\tvendue\n\n");
		for (int i=0; i < Produit.nbProd; i++) {
			sortie.append(tabProduits[i].getNumProd()
				+"\t\t"+tabProdQte[i]+"\n");
		}
		//
		JOptionPane.showMessageDialog(null, sortie, "RÉSULTATS DE LA JOURNÉE", 
			JOptionPane.PLAIN_MESSAGE);} 
	
}
