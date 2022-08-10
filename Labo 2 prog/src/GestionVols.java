import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class GestionVols {
	static final String FICHIER_VOLS = "src/CieAirRelax.txt";
	static ArrayList<Vol> tabVols;
	static BufferedReader tmpVols;
	static JTextArea sortie;
	
	public static int menuGeneral(){
		String contenu="1-Liste des vols\n2-Ajout d'un vol\n3-Retrait d'un vol\n4-Modification de la date de dÈpart\n"
				+ "5-RÈservation d'un vol\n0-Terminer\n\n";
		contenu+="Faites votre choix: ";
		return Integer.parseInt(JOptionPane.showInputDialog(null, contenu, "CIE AIR RELAX",JOptionPane.PLAIN_MESSAGE));
	}
	
	public static void chargerVols() throws Exception {
		try {
			tabVols = new ArrayList<Vol>();
			String ligne;
			String elemsVol[] = new String[4];
			String elemsDate[] = new String[3];
			tmpVols = new BufferedReader(new FileReader(FICHIER_VOLS));
			ligne = tmpVols.readLine();
			while (ligne != null) {
				elemsVol = ligne.split(";");
				elemsDate = elemsVol[2].split(" ");
				Date dateInstance = new Date(Integer.parseInt(elemsDate[0]), Integer.parseInt(elemsDate[1]),
											 Integer.parseInt(elemsDate[2]));
				tabVols.add(new Vol(Integer.parseInt(elemsVol[0]), elemsVol[1], dateInstance, Integer.parseInt(elemsVol[3])));
				ligne = tmpVols.readLine();
			}
		}catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. VÈrifiez le chemin et nom du fichier");
			
		}
		catch(IOException e) {
			System.out.println("Un problËme est arrivÈ lors de la manipulation du fichier.");
			
		}catch(Exception e) {
			System.out.println("Un problËme est arrivÈ lors du chargement du fichier. Contacter l'administrateur.");
		}finally {
			tmpVols.close();
		}
		
		
		
	}
	
	public static void afficherEntete(){
		sortie = new JTextArea();
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));
		sortie.append("\t\tLISTE DES VOLS\n\n");
		sortie.append("NUM…RO\tDESTINATION\t\tDATE\tR…SERVATIONS\n");
	}
	
	public static void listerVols() {		
		afficherEntete();
		tabVols.forEach((unVol) -> {
			sortie.append(unVol.toString());
			
		});
		sortie.append("Nombre de vols = "+tabVols.size());	
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}

	

	public static void main(String[] args) throws Exception {	
		int choix;
		chargerVols();
		do {
			choix = menuGeneral();
			switch(choix) {
				case 1 :
					listerVols();
					break;
				case 2 : 
					//ajouterUnVol();
					break;
				case 3 : 
					//retirerUnVol();
					break;
				case 4 : 
					//modDateDepart();
					break;
				case 5 : 
					//reserVol();
					break;
				case 6 :
					//sauvegarderVols();
					//afficherMessage("Merci d'avoir utilis√© notre syst√®me");
					break;
				default :
					//afficherMessage("Choix invalide. Les option sont [1-6] !");
					break;
			}
		} while(choix != 6);
	
		
	}

}
