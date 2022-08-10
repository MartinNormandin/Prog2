import javax.swing.JOptionPane;

public class ExempleTableauMethodes {
	
	static String tabNoms[] = new String[10];
	
	static double tabMontants[] = new double[10];
	
	
	static char Est_ceQueJeVeuxContinuer(String msgContinuer){
	    char monDesir;
	    do{
	        monDesir=(JOptionPane.showInputDialog(null, msgContinuer,
					"Saisie des données", JOptionPane.PLAIN_MESSAGE)).toUpperCase().charAt(0);
	    }while(monDesir!='O' && monDesir!='N');

	    return monDesir;
	}
	
	static int saisirLesDonnees() {
		String nom;
		int qte,i=0;
		double prix;
		char continuer='O';

		

		String msgContinuer="Traiter un autre client (O/N) ? ";
		
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
		    continuer=Est_ceQueJeVeuxContinuer(msgContinuer);
		    msgContinuer="Êtes-vous sur de vouloir continuer (O/N) ? ";
		}
		return i;
	}
	
	static void afficherRapport(int nbClientsTraites) {
		
		double total=0;
		
		for(int i=0;i<nbClientsTraites;i++){
		     total+=tabMontants[i];
	    }

		
		for(int i=0;i<nbClientsTraites;i++){
		    System.out.println(tabNoms[i]+" <=====> "+String.format("%.2f", tabMontants[i])+"$");
		}

		System.out.println("\n\nTOTAL = "+String.format("%.2f", total)+"$");
	}

	public static void main(String[] args) {
		
		int nbClients;

		nbClients=saisirLesDonnees();
		
		afficherRapport(nbClients);
		
		//afficherRapport(saisirLesDonnees());
	}

}
