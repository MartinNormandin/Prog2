
import javax.swing.*;
import java.util.ArrayList;

public class TestVin {
	
	static ArrayList<Vin> tabVins;
	static JTextArea sortie= new JTextArea();

	public static void main(String[] args) {
		instancieVinsInitials();
		prepareAffichage();
		// MODIFICATION ET AJOUT DE VINS 
		tabVins.get(0).setPrix(tabVins.get(0).getPrix() + 2);
		tabVins.get(1).setPrix(tabVins.get(1).getPrix() + 8.5);
		tabVins.get(1).setOrigine("Italie");
		tabVins.get(2).setNom("Vino verde");
		tabVins.get(2).setType(2);
		tabVins.get(2).setOrigine(tabVins.get(0).getOrigine());
		tabVins.add(new Vin("L'érablière", "Québec", 15));
		//
		sortie.append("\n");
		prepareAffichage();
		JOptionPane.showMessageDialog(null, sortie, "Résultats obtenus",
				JOptionPane.PLAIN_MESSAGE);	
		//
		System.exit(0);}
	
	public static void instancieVinsInitials() {
		tabVins= new ArrayList<>();
		tabVins.add(new Vin("MiamMiam", 2, "Espagne", 8.95));
		tabVins.add(new Vin("Délicieux", "France", 14.5));
		tabVins.add(new Vin("Mystère", 3, "Californie", 10));}
	
	public static void prepareAffichage() {
		sortie.append("Voici les "+Vin.nbVins+" vins\n");
		sortie.append("Le prix total des vins est de "+
			Vin.df.format(Vin.prixTotal)+"\n");
		tabVins.forEach((unVin) -> sortie.append("              "+
			unVin.toString()));}

}
