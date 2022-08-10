import java.io.*;
import java.util.*;


public class Exemple {
	
	static String personne = "Marie Curie;Scientifique#73;France";
	static String delimiteurs = ";#";
	
	public static void AvecStringTokenizer() {
		StringTokenizer str = new StringTokenizer(personne,delimiteurs);		
		
		/*while(str.hasMoreTokens()) {
			System.out.println(str.nextToken());
			
		}*/
		
		System.out.println("NOM = " +str.nextToken());
		System.out.println("PROFESSION = " +str.nextToken());
		System.out.println("AGE = " +str.nextToken());
		System.out.println("PAYS = " +str.nextToken());
		
	}
	
	public static void AvecSplit() {
		String tab[];
		tab = personne.split(delimiteurs);
		/*for(String attribut: tab) {
			System.out.println(attribut);
		}*/
		System.out.println("NOM = " +tab[0]);
		System.out.println("PROFESSION = " +tab[1]);
		System.out.println("AGE = " +tab[2]);
		System.out.println("PAYS = " +tab[3]);
	}

	public static void main(String[] args) {
		//AvecStringTokenizer();
		AvecSplit();
	}

}
