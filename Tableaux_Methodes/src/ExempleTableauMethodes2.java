import javax.swing.JOptionPane;

public class ExempleTableauMethodes2 {
	
	static int menu(){
	    int choix;
	   
	    String contenu="\n***** MENU *****";
	    contenu+="\n1-Exercice 1";
	    contenu+="\n2-Exercice 2";
	    contenu+="\n3-Exercice 3";
	    contenu+="\n4-Quitter";
	    contenu+="\n\nEntrer votre choix : ";
	    choix=Integer.parseInt(JOptionPane.showInputDialog(null, contenu,
				"GESTION DES CLIENTS", JOptionPane.PLAIN_MESSAGE));
	    return choix;
	}
	
	static char Est_ceQueJeVeuxContinuer(String msgContinuer){
	    char monDesir;
	    do{
	        monDesir=(JOptionPane.showInputDialog(null, msgContinuer,
					"Saisie des données", JOptionPane.PLAIN_MESSAGE)).toUpperCase().charAt(0);
	    }while(monDesir!='O' && monDesir!='N');

	    return monDesir;
	}
	
	//Code pour un exercice1
	static void exercice1(){
		String nom;
		int qte,i=0, nbClients;
		double prix,total=0, montant;
		char continuer='O';

		String tabNoms[] = new String[10];
		
		double tabMontants[] = new double[10];

		var msgContinuer="Traiter un autre client (O/N) ? ";
		
		while(continuer=='O'){

			nom=JOptionPane.showInputDialog(null, "Entrer le nom",
					"Écran de saisie du nom", JOptionPane.PLAIN_MESSAGE);

			qte=Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer la quantité",
					"Écran de saisie de la quantité", JOptionPane.PLAIN_MESSAGE));
			
			prix=Double.parseDouble(JOptionPane.showInputDialog(null, "Entrer le prix",
					"Écran de saisie du prix", JOptionPane.PLAIN_MESSAGE));

		    tabNoms[i]=nom;
		   
		    montant=qte*prix;
		    tabMontants[i]=montant;
		    ++i;
		    continuer=Est_ceQueJeVeuxContinuer(msgContinuer);
		    msgContinuer="Êtes-vous sur de vouloir continuer (O/N) ? ";
		}
		nbClients=i;
		
		//Total des achats
		for(i=0;i<tabMontants.length;i++){
		     total+=tabMontants[i];
	    }

		
		for(i=0;i<nbClients;i++){
		    System.out.println(tabNoms[i]+" <=====> "+String.format("%.2f", tabMontants[i])+"$");
		}

		System.out.println("\n\nTOTAL = "+String.format("%.2f", total)+"$");

	}

	static void exercice2(){
		System.out.println("\nVous avez choisi exercice 2");
	}

	static void exercice3(){
		System.out.println("\nVous avez choisi exercice 3");
	}


	//Méthode principale. Par ici débute l'exécution.
	public static void main(String[] args) {
	
		int choixUtilisateur;
		
		    do{
		        choixUtilisateur=menu();
		        
		        switch(choixUtilisateur){
		            case 1 :
		                exercice1();
		            break;
		            case 2 :
		                exercice2();
		            break;
		            case 3 :
		                exercice3();
		            break;
		            case 4 :
		            	System.out.println("\nMERCI D'AVOIR UTILISÉ NOTRE PROGRAMME.");
		            break;
		            default:
		            	System.out.println("\nVotre choix est invalide");
		        }
		        
		    }while(choixUtilisateur!=4);
		}
}
