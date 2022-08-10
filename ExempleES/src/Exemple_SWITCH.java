import java.io.*;

public class Exemple_SWITCH {

	public static void main(String[] args) throws IOException{
		
		  String jour=null;
		  
		  BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		  
		  String debut = "Vous avez entré un jour de début de semaine ouvrable";

		  String fin = "Vous avez entré un jour de fin de semaine ouvrable";

		  String weekEnd = "Vous avez entré un jour de fin de semaine";
		  
		  System.out.print("Entrer un jour de la semaine : ");
		  
		  jour = clavier.readLine().toUpperCase();
		  
	      switch(jour){
	        case "LUNDI" :
	          System.out.println(debut);
	          break;
	       case "MARDI" :
	          System.out.println(debut);
	          break;
	        case "MERCREDI" :
	          System.out.println("Vous avez entré un jour de millieu de semaine");
	          break;
	        case "JEUDI" :
	          System.out.println(fin);
	          break;
	        case "VENDREDI" :
	          System.out.println(fin);
	          break;
	        case "SAMEDI" :
	          System.out.println(weekEnd);
	           break;
	        case "DIMANCHE" :
	          System.out.println(weekEnd);
	          break;
	        default :
	          System.out.println("Jour invalide");
	      }

	}

}
