import java.io.*;
import javax.swing.*;
public class ExempleTableaux {

	public static void main(String[] args) {
		

		String nom,rep="";
		int qte,i=0, nbClientsTraites;
		double prix,total=0;
		char continuer='O';

		String tabNoms[] = new String[10];
		
		double tabMontants[] = new double[10];

		JTextArea sortie = new JTextArea(10, 30);
		
		//Lire des données des ventes
		while(continuer=='O'){
			nom=JOptionPane.showInputDialog(null, "Entrer le nom",
					"Écran de saisie du nom", JOptionPane.PLAIN_MESSAGE);

			qte=Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer la quantité",
					"Écran de saisie de la quantité", JOptionPane.PLAIN_MESSAGE));
			
			prix=Double.parseDouble(JOptionPane.showInputDialog(null, "Entrer le prix",
					"Écran de saisie du prix", JOptionPane.PLAIN_MESSAGE));
			
		    
		   tabNoms[i]=nom;

		    tabMontants[i]=qte*prix;
		    
		    ++i;

		    continuer=JOptionPane.showInputDialog(null, "Traiter un autre client (O/N) ?",
					"Écran de continuation", JOptionPane.PLAIN_MESSAGE).toUpperCase().charAt(0);
		}
		
		nbClientsTraites=i;
		//Calculer le total des achats
		 for(i=0;i<nbClientsTraites;i++){
			 total+=tabMontants[i];
		 }

		
		//Afficher les résultats
		int taille=tabMontants.length;
		rep+="\nNOM\t\tMONTANT";
		for(i=0;i<nbClientsTraites;i++){
		    rep+="\n\n"+tabNoms[i]+"\t\t"+String.format("%.2f",tabMontants[i])+"$";
		}
		
		rep+="\n\nTOTAL = "+String.format("%.2f",total)+"$";
		
		//Placer les résultats dans le JTextArea sortie
		sortie.setText(rep);
		
		//Placer le JTextArea dans le JOptionPane.showMessageDialog
        JOptionPane.showMessageDialog(null, sortie,
         "LISTE DES ACHATS", JOptionPane.PLAIN_MESSAGE);

        System.exit(0);

	}

}
