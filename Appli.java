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

            while (s.equals("") || !estValide(s)) {
                System.out.print("$>sresoudre-sac-a-dos ");
                s = sc.nextLine();
            }

            Scanner scs = new Scanner(s);
            String chemin = scs.next();
            if(!chemin.equals("exit")) {
                ArrayList<Objet> obj = lire(chemin);
                SacADos sac = new SacADos(chemin, Float.parseFloat(scs.next()));
                String methode = scs.next();
                if (scs.hasNext())
                    methode = methode + " " + scs.next();
                sac.resoudre(methode, obj);
                scs.close();
                System.out.println(sac);
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
        Scanner scs = new Scanner(s);
        String mot = scs.next();

        if(mot.equals("exit")) {
            scs.close();
            return true;
        }

        File f = new File(mot);
        if(!f.exists()) {
            scs.close();
            System.out.println("Fichier inexistant\n");
            return false;
        }

        if(scs.hasNext()) {
            mot = scs.next();
            if (!mot.matches("-?\\d+(\\.\\d+)?")) {
                scs.close();
                System.out.println("Le poids entré n'est pas valide\n");
                return false;
            }
            if (scs.hasNext()) {
                mot = scs.next();
                if (!(mot.equals("gloutonne") || mot.equals("pse"))) {
                    if (mot.equals("prog.")) {
                        mot = scs.next();
                        if (!mot.equals("dynamique")) {
                            System.out.println("Methode inexistante\n");
                            scs.close();
                            return false;
                        }
                    } else {
                        System.out.println("Methode inexistante\n");
                        scs.close();
                        return false;
                    }
                }

                scs.close();
                return true;
            }
        }
        scs.close();
        System.out.println("Paramètre manquant\n");
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
