import javax.swing.*;
import java.text.*;
import java.io.*;

public class ExerciceTableauxFichier {
	
	public static void main(String args[]) throws IOException { 
		final int NB_MAX_PROD = 20;
		int tabNoProd[] = new int[NB_MAX_PROD];
		double tabPrixProd[] = new double[NB_MAX_PROD];
		int tabQte[] = new int[NB_MAX_PROD];
		int nbProd;
		
		nbProd = batirTableaux(tabNoProd, tabPrixProd, NB_MAX_PROD);
				
		traiterLesClients(tabNoProd, tabPrixProd, tabQte, nbProd);
		afficherResultats(tabNoProd, tabQte, nbProd);
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
	
	
	static int batirTableaux(int tabNoProd[], double tabPrixProd[], int tailleTableaux) throws IOException {
		
		final String FICHIER_PRODUITS = "src/donnees/produits.txt";
		BufferedReader ficProd = new BufferedReader(new FileReader(FICHIER_PRODUITS));
		int i = 0;
		String ligne;
		String elems[];
		ligne = ficProd.readLine();
		while(i < tailleTableaux && ligne != null) {
			elems = ligne.split(";");
			tabNoProd[i] = Integer.parseInt(elems[0]);
			tabPrixProd[i] = Double.parseDouble(elems[1]);
			++i;
			ligne = ficProd.readLine();
			
		}
	ficProd.close();	
	return i;
	
	
	}
	
	

} 
