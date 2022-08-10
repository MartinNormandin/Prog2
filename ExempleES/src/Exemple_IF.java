import java.io.*;

public class Exemple_IF {

	public static void main(String[] args) throws IOException{
		
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

		final int DISQUE=1;
		final int CASSETTE=2;
		final double ESCOMPTE=0.15;
		
		int categorie, quantite; 
		double total=0, rabais=0;
		String reponse, categ=null; 
		
		//Lire au clavier la categorie et la quantité
		System.out.print("Entrer la categorie : ");
		categorie=Integer.parseInt(clavier.readLine());
		System.out.print("Entrer la quantité : ");
		quantite=Integer.parseInt(clavier.readLine());

		if (categorie==DISQUE){
		    categ="disque";
		    if (quantite > 3){
		        total=(3*12.98)+((quantite-3)*10.95);
		    }else{ 
		    	total=quantite*12.98;
		    	}
		}else if(categorie==CASSETTE){
		    categ="cassette";
		    total=quantite*10.98;
		    if (quantite > 4){
		        rabais=total*ESCOMPTE;
		        total=total-rabais;
		    }
		}
		reponse="\n\nVous avez acheté "+quantite+" "+categ+" avec un rabais de "+String.format("%.2f", rabais)+"$";
		reponse+="\npour un total de "+String.format("%.2f", total)+"$";
		System.out.println(reponse);

	}

}
