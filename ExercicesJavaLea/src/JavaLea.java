import java.io.*;
import javax.swing.*;

public class JavaLea {

	public static void main(String[] args) {
		
		char statut;
		char sexe;
		
						
		String saisie;
		
		//
		
		saisie = JOptionPane.showInputDialog("Entrer votre statut (M(mari�)/C(c�libataire): ");
		statut = charAt(saisie);
		
		saisie = JOptionPane.showInputDialog("Entrer votre sexe (M(masculin)/F(feminin");
		sexe = charAt(saisie);
		
		if (statut == 'M') && (sexe == 'F') {
			JOptionPane.showMessageDialog(null, "Femme mari�e");
			
			
		}
			
		
			
		
		
				
		
					
			   
		
		System.exit(0);
				
		
	

	}

	private static char charAt(String saisie) {
		// TODO Auto-generated method stub
		return 0;
	}

}
