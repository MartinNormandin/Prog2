package documents;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
public class GestionRadios {
	
	static final String FICHIER_RADIOS = "src/donnees/radios.txt";
	static ArrayList<Radio> listeRadios;
	static BufferedReader tmpRadiosRead;
	static BufferedWriter tmpRadiosWrite;
	static JTextArea sortie;
	
	public static int menuGeneral(){
		String contenu="Option A. Lister\nOption B. Lister par marque\nOption C. Modifier prix\nOption D. Ajouter\nOption E. Chercher et modifier\nOption F. Terminer\n\n";
		contenu+="Entrez votre choix parmis[1 pour A, 2 pour B, ETC...] : ";
		return Integer.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU GESTION RADIOS",JOptionPane.PLAIN_MESSAGE));
	}
	
	public static int menuModifier() {
		String contenu = "1-Ajouter mp3\n2-Modifier prix\n";
		contenu += "Entrez votre choix parmis[1-2] : ";
		return Integer
				.parseInt(JOptionPane.showInputDialog(null, contenu, "MENU GESTION RADIOS", JOptionPane.PLAIN_MESSAGE));
	}
	
	
	
	public static void chargerRadios() throws Exception {
		try {
			String ligne;
			String elems[] = new String[6];
			listeRadios = new ArrayList<Radio>();
			tmpRadiosRead = new BufferedReader(new FileReader(FICHIER_RADIOS));
			ligne = tmpRadiosRead.readLine();
			while (ligne != null) {
				elems = ligne.split(";");
				listeRadios.add(new Radio (elems[0], elems[1], Integer.parseInt(elems[2]), Integer.parseInt(elems[3]), Integer.parseInt(elems[4]), Double.parseDouble(elems[5])));
				ligne = tmpRadiosRead.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Verifiez le chemin et nom du fichier.");
		}
		catch (IOException e) {
			System.out.println("Un probleme est arrive lors de la manipulation du fichier. Verifiez vos donnees.");
		}catch (Exception e) { 
			System.out.println("Un probleme est arrive lors du chargement du fichier. Contactez l'administrateur.");
		}finally {
			tmpRadiosRead.close();
		}
	}
	
	public static void afficherEntete(){
		sortie = new JTextArea();
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));	
		sortie.append("\t\tLISTE DES RADIOS\n\n");
		sortie.append("MARQUE\tMODELE\tCD\tCASSETTES\tMP3\tPRIX\n");
	}
	
	public static void listerRadios() {
		afficherEntete();
		listeRadios.forEach((unRadio) -> sortie.append(unRadio.toString()));
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void afficherMessage(String msg){
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void listerRadiosParMarque() {
		String marque = JOptionPane.showInputDialog(null, "Entrez la marque", "LISTER DES RADIOS PAR MARQUE",
						JOptionPane.PLAIN_MESSAGE);
		afficherEntete();

		listeRadios.forEach((unRadio) -> {
			if(marque.equals(unRadio.getMarque())){
				sortie.append(unRadio.toString());}
				
		});	
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}
	
	/*public static void chercherRadio(){
		String marque = "Sony";
		String modele = "WM823";
		int choix;
		int num = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro", "MODIFIER UN LIVRE", JOptionPane.PLAIN_MESSAGE));
		Radio livreTrouve = rechercherRadio(num);
		if(livreTrouve == null){
			return;
		}
		do{
			choix = menuModifier();
			switch(choix){
				case 1 :
				   int mp3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez si mp3 (1 ou 0)", "MODIFIER RADIO",
						JOptionPane.PLAIN_MESSAGE));
				
					break;
				case 2 :
					
			}
		} while(choix != 2);
	}

*/
	

			
	
	

	public static void ajouterRadio() {
		String marque = JOptionPane.showInputDialog(null, "Entrez la marque", "AJOUTER UNE RADIO", JOptionPane.PLAIN_MESSAGE);
		String modele = JOptionPane.showInputDialog(null, "Entrez le modele", "AJOUTER UNE RADIO",
						JOptionPane.PLAIN_MESSAGE);
		int cd = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez cd (1 ou 0)", "AJOUTER UNE RADIO", JOptionPane.PLAIN_MESSAGE));
		int cassettes = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Entrez cassettes (1 ou 0)", "AJOUTER UNE RADIO", JOptionPane.PLAIN_MESSAGE));
		int mp3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez mp3 (1 ou 0)", "AJOUTER UNE RADIO"));
		double prix = Double.parseDouble(JOptionPane.showInputDialog(null, "Entrez le prix", "AJOUTER UNE RADIO"));
		listeRadios.add(new Radio(marque, modele, cd, cassettes, mp3, prix));
	
} 
		

	public static void main(String[] args) throws Exception {
		int choix;
		chargerRadios();
		do {
			choix = menuGeneral();
			switch(choix){
				case 1 : 
					listerRadios();
					break;
				case 2 : 
					listerRadiosParMarque();
					break;
				case 3 : 
					//modifierPrixRadio();
					break;
				case 4 : 
					ajouterRadio();
					break;
				case 5 :
					//chercherRadio();
					break;
				case 6 :
					//sauvegarderRadios();
					break;				
				default :
					afficherMessage("Choix invalide. Les option sont [1-6] !");
					break;
			}
		} while(choix != 6);
	
}
	

	

}
