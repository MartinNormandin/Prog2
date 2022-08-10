import java.io.*;
public class Exemple {
	
	public static void afficher(int T[]) {
		System.out.println("\nVotre age est : "+T);
		
	} 
	
	public static void main(String[] args) {
		int tab[]= {2,4,6,8};
		afficher(tab);
		
		
		
		int taille = tab.length;
		
		int j =0;
		for(int valeur : tab) {
			System.out.println("tab["+j+"] = "+ valeur);
			++j;
		}
		
		int i =0;
		while(i < taille){
			System.out.println("tab["+i+"] = "+tab[i]);
			i++;
		}
		String ch=null;
		
		String nom="Antonio";
		int taille1 = nom.length();
		System.out.println(taille);
		ch = nom.substring(0,4);
		System.out.println(ch);
		ch = nom.substring(2,6);
		System.out.println(ch);
		
		
		
	}

}

