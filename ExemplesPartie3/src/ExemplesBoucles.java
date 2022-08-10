import java.io.*;

public class ExemplesBoucles {

	public static void main(String[] args) throws IOException{
		
		final char DISQUE='D';
		final char CASSETTE='C';
		final double ESCOMPTE=0.15;

		char categorie=' ';
		
		int quantite=0;
		
		double total=0, rabais=0; 
		String reponse, categ=null, msgErreur=" ";
		boolean donnees_invalides=false;          
		
		//Lire au clavier la categorie et la quantité
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			System.out.print("\n"+msgErreur+"Entrer une catégorie (parmi D, C) : ");
			categorie = clavier.readLine().toUpperCase().charAt(0);
			msgErreur="Attention mauvaise donnée";
		}while(categorie != DISQUE && categorie != CASSETTE);
			
		do {
			System.out.print("\nEntrer la quantité [1,100] : ");
			quantite = Integer.parseInt(clavier.readLine());
		}while(quantite < 1 || quantite > 100);
		
		/*if (categorie==DISQUE){
		    categ="disque";
		    if (quantite > 3){
		        total=(3*12.98)+((quantite-3)*10.95);
		    }else{ total=quantite*12.98;}
		}else if(categorie==CASSETTE){
		    categ="cassette";
		    total=quantite*10.98;
		    if (quantite > 4){
		        rabais=total*ESCOMPTE;
		        total=total-rabais;
		    }
		}
		else {donnees_invalides=true;}*/
		
		switch (categorie) {
			case DISQUE : 
				categ="disque";
			    if (quantite > 3){
			        total=(3*12.98)+((quantite-3)*10.95);
			    }else{ total=quantite*12.98;}
			    break;
			case CASSETTE :
				categ="cassette";
			    total=quantite*10.98;
			    if (quantite > 4){
			        rabais=total*ESCOMPTE;
			        total=total-rabais;
			    }
			    break;
		}//switch
		
		
	    reponse="Vous avez acheté "+quantite+" "+categ+" avec un rabais de "+String.format("%.2f", rabais)+"$";
	    reponse+="\npour un total de "+String.format("%.2f", total)+"$";
		
		System.out.println(reponse);

	}

}
