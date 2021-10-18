import java.util.ArrayList;
import java.util.Collections;

public class SacADos {
    private String chemin;
    private float poids_max;
    private float poids;
    private ArrayList<Objet> objets;

    SacADos(){
        chemin = null;
        poids_max = 0;
        poids = 0;
        objets = new ArrayList<>();
    }

    SacADos(String chemin, float poids_maximal){
        this.chemin = chemin;
        poids_max = poids_maximal;
        poids = 0;
        objets = new ArrayList<>();
    }

    public String toString(){
        String s = "- Chemin : " + chemin + "\n";
        String obj = "";
        float val_tot = 0;
        for(Objet o : objets) {
            val_tot += o.getValeur();
            obj = obj + "   > " + o + "\n";
        }
        s = s + "- Poids total : " + String.format("%1$.2f", poids) + "/" + poids_max + "\n";
        s = s + "- Valeur totale : " + val_tot + "\n";
        s = s + "- " + objets.size() + " objets :\n";
        s += obj;
        return s;
    }

    public void resoudre(String methode, ArrayList<Objet> obj) {
        if(methode.equals("gloutonne"))
            gloutonne(obj);
        if(methode.equals("prog. dynamique"))
            dynamique(obj);
        if(methode.equals("pse"))
            pse(obj);
    }

    /* ------------------- GLOUTON ------------------- */

    public void gloutonne(ArrayList<Objet> obj) {
        Collections.sort(obj, Collections.reverseOrder());
        for(Objet o : obj) {
            if(o.getPoids() + poids <= poids_max){
                objets.add(o);
                poids += o.getPoids();
            }
        }
    }
    
    /* ------------------- DYNAMIQUE ------------------- */

    public void dynamique(ArrayList<Objet> obj) {
        // Matrice d'entiers : multiplier les float par 10 pour retomber sur des entiers
        float[][] M = new float[obj.size()][(int)(poids_max * 10 + 1)];

        // remplir la première ligne du tableau
        for(int j = 0; j <= poids_max * 10; ++j) {
            M[0][j] = (obj.get(0).getPoids() * 10 > j) ? 0 : obj.get(0).getValeur();
            // remplir les autres lignes du tableau
            for(int i = 1; i < obj.size(); ++i) {
                M[i][j] = (obj.get(i).getPoids() * 10 > j) ? M[i - 1][j] : Math.max(M[i - 1][j],
                        M[i - 1][(int) (j - obj.get(i).getPoids() * 10)] + obj.get(i).getValeur());
            }
        }

        int i = obj.size() - 1;
        int j = (int)(poids_max * 10);
        while(M[i][j] == M[i][j-1]) {
            --j;
        }
        while(j > 0) {
            while(i > 0 && M[i][j] == M[i-1][j]) {
                --i;
            }
            j = (int) (j - obj.get(i).getPoids() * 10);
            if(j >= 0) {
                objets.add(obj.get(i));
                poids += obj.get(i).getPoids();
            }
            --i;
        }
    }

    /* ------------------- PSE ------------------- */
    
    public void pse(ArrayList<Objet> obj) {
        ABR arbre = new ABR(null, obj, poids_max, 0);
        objets = arbre.getMeilleureSolution();
        for(Objet o : objets)
            poids += o.getPoids();
    }
}
