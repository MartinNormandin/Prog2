import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utilitaires {
    static BufferedReader tmpFic;
    static ObjectOutputStream ficObjEcrire;
    static ObjectInputStream ficObjLire;

    public static final String MSG1 = " ne peut pas être inférieure à l'année actuelle, soit ";
    public static final String MSG2 = "Impossible de valider le jour puisque votre mois est invalide";

    public static String ajouterEspacesFin(int tailleColMax, String chaine) {
        int nbEspaces = tailleColMax - chaine.length();
        String espaces = "";
        for (int i = 0; i < nbEspaces; i++) {
            espaces += " ";
        }
        System.out.println(espaces.length() + "==>" + chaine.length() + "==>" + (espaces.length() + chaine.length()));
        return espaces;
    }

    public static String ajouterEspacesDebut(int tailleColMax, String chaine) {
        int nbEspaces = (tailleColMax - chaine.length()) / 2;
        String espaces = "";
        for (int i = 0; i < nbEspaces; i++) {
            espaces += " ";
        }
        return espaces;
    }

    public static String ajouterCaractereGauche(char car, int longueur, String ch) {
        String rep = "";
        int nbCar = longueur - ch.length();
        for (int i = 1; i <= nbCar; i++) {
            rep += car;
        }
        return rep + ch;
    }

}