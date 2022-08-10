import javax.swing.*;
import java.text.*;
import java.io.*;


public class Exercice1 {

	public static void main(String[] args) {		
		final int NB_PROD = 8;
		int tabNoProd[] = { 234, 125, 657, 987, 213, 934, 678, 776};
		double tabPrixProd[] = {45.99,9.50,5.75,12.35,9.75,87.45,56.99,76.56};
		int tabQteTotale[] = new int[NB_PROD];
		
		traiterLesClients(tabNoProd, tabPrixProd, tabQteTotale, NB_PROD);
		afficherResultats(tabNoProd, tabQteTotale, NB_PROD);
		System.exit(0);
	}
	
	static void traiterLesClients(int tabNoProd[], double tabPrix[],
			int tabQteTotale[], int nbProd) {
		DecimalFormat cash = new DecimalFormat("0.00 $");
		int numero,
			qte,
			posiProd;
		double cout;
		char reponse;
		
		do {
			numero = Integer.parseInt(JOptionPane.showInputDialog(
					"Entrez le numéro du produit à acheter :"));
			qte = Integer.parseInt(JOptionPane.showInputDialog(
					"Entrez la quantité désirée :"));
			
			posiProd = rechercher(tabNoProd, nbProd, numero);
			
			if (posiProd != -1)
			{
				tabQteTotale[posiProd]+=qte;
				cout = tabPrix[posiProd]*qte;
				JOptionPane.showMessageDialog(null, 
						"Le cout de cet achat est de " + cash.format(cout));
				
			}
			
			else 
				JOptionPane.showMessageDialog(null, "No de produit erroné");
			
			reponse = JOptionPane.showInputDialog(
					"Avez-vous un autre client à traiter O/N?").charAt(0);
			reponse = Character.toUpperCase(reponse);
		} while (reponse == 'O');
	}
	
	static int rechercher(int tab[], int nbEl, int valeurCherchee)
	{
		int posi = -1;
		boolean trouve = false;
				
		for (int i = 0; i < nbEl && !trouve; i++) {
			if (tab[i] == valeurCherchee)
			{
				posi = i;
				trouve = true;
			}
		}
		return posi;	
		
	}
	
	static void afficherResultats(int tabNoProd[], int tabQteTotale[] , int nbProd) {
		
		JTextArea sortie = new JTextArea();
		sortie.append("Numéro du\t\tQuantité\n");
		sortie.append("produit\t\ttotale\n\n");
		for (int i = 0; i < nbProd; i++) {
			sortie.append(tabNoProd[i]+"\t\t"+ tabQteTotale[i]+"\n");}
			
		JOptionPane.showMessageDialog(null, sortie, "Résultats de la journée", JOptionPane.PLAIN_MESSAGE);
	}
	
}
	
	
	
	
