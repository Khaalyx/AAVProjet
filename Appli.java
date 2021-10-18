import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Appli {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String s = "";
        while(!s.equals("exit")) { // pour continuer à résoudre au lieu de re-run
            System.out.print("$>resoudre-sac-a-dos ");
            s = sc.nextLine();

            // tant que la chaine de caract saisie n'est pas valide
            while (s.equals("") || !estValide(s)) {
                System.out.print("$>sresoudre-sac-a-dos ");
                s = sc.nextLine();
            }

            // stock les saisies dans un tableau
            String[] str = s.split(" ");
            String chemin = str[0];
            if(!chemin.equals("exit")) {
                // la liste des objets qu'on doit mettre dans le sac
                ArrayList<Objet> obj = lire(chemin);
                SacADos sac = new SacADos(chemin, Float.parseFloat(str[1]));
                String methode = str[2];
                sac.resoudre(methode, obj);
                System.out.println(sac); // affichage de la solution
            }
        }
        sc.close();
    }

    /**
     * Vérifie que la saisie est valide
     * @param s La chaîne de caractères saisie
     * @return true si la saisie est valide
     */
    private static boolean estValide(String s) {
        String[] str = s.split(" ");

        // si c'est la cmd pour arreter le prog
        if(str.length == 1 && str[0].equals("exit"))
            return true;

        if(str.length == 3) { // s'il y a le bon nomrbe d'arg
            File f = new File(str[0]);
            if (!f.exists()) { // vérifaction de l'existence du fichier
                System.out.println("Fichier inexistant\n");
                return false;
            }
            // s'il s'agit bien d'un nombre décimal
            if (!str[1].matches("-?\\d+(\\.\\d+)?")) {
                System.out.println("Le poids entré n'est pas valide\n");
                return false;
            }
            // vérification de la méthode demandée
            if (!(str[2].equals("gloutonne") || str[2].equals("pse")
                    || str[2].equals("dynamique"))) {
                System.out.println("Methode inexistante\n");
                return false;
            }
            return true;
        }
        // s'il y a trop ou pas assez d'arg
        System.out.println("Erreur dans le nombre de paramètre\n");
        return false;
    }

    /**
     * Lis les lignes d'un fichier
     * et stock les infos des objets dans une ArrayList
     * @param file Le nom du fichier
     * @return une ArrayList des objets
     */
    private static ArrayList<Objet> lire(String file) throws FileNotFoundException {
        ArrayList<Objet> obj = new ArrayList<>();
        Scanner scan = new Scanner(new FileInputStream(file));
        while(scan.hasNextLine()){
            // on stock dans un tableau les données d'une ligne du fichier
            String[] objet = scan.nextLine().split(";");
            if(objet != null) {
                String nom = objet[0];
                float poids = Float.parseFloat(objet[1]);
                float val = Float.parseFloat(objet[2]);
                obj.add(new Objet(nom, poids, val));
            }
        }
        return obj;
    }
}
