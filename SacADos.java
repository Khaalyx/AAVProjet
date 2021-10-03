import java.util.ArrayList;

public class SacADos {
    private String chemin;
    private float poids_max;
    private ArrayList<Objet> objets;

    SacADos(){
        chemin = null;
        poids_max = 0;
        objets = new ArrayList<Objet>();
    }

    SacADos(String chemin, float poids_maximal){
        this.chemin = chemin;
        poids_max = poids_maximal;
        objets = new ArrayList<Objet>();
    }

    public String toString(){
        String s = "- Chemin : " + chemin + "\n";
        String obj = "";
        float poids_tot = 0;
        float val_tot = 0;
        for(int i = objets.size() - 1; i >= 0; --i) {
            poids_tot += objets.get(i).getPoids();
            val_tot += objets.get(i).getValeur();
            obj += "   > ";
            obj += objets.get(i).getNom();
            obj += " ; ";
            obj += objets.get(i).getPoids();
            obj += " ; ";
            obj += objets.get(i).getValeur();
            obj += "\n";
        }
        s = s + "- Poids total : " + poids_tot + "/" + poids_max + "\n";
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

    public void gloutonne(ArrayList<Objet> obj) {

    }


    public void dynamique(ArrayList<Objet> obj) {
        float[][] M = new float[obj.size()][(int) (poids_max + 1)];

        for(int j = 0; j <= poids_max; ++j) {
            if(obj.get(0).getPoids() > j) {
                M[0][j] = 0;
            }
            else {
                M[0][j] = obj.get(0).getValeur();
            }
            for(int i = 1; i < obj.size(); ++i) {
                if(obj.get(i).getPoids() > j)
                    M[i][j] = M[i-1][j];
                else {
                    if(M[i-1][j] > M[i-1][(int) (j-obj.get(i).getPoids())] + obj.get(i).getValeur())
                        M[i][j] = M[i-1][j];
                    else
                        M[i][j] = M[i-1][(int) (j-obj.get(i).getPoids())] + obj.get(i).getValeur();
                }
            }
        }

        int i = M.length - 1;
        int j = M[0].length - 1;
        while(M[i][j] == M[i][j-1]) {
            --j;
        }
        while(j > 0) {
            while(i > 0 && M[i][j] == M[i-1][j]) {
                --i;
            }
            j = (int) (j - obj.get(i).getPoids());
            if(j >= 0)
                objets.add(obj.get(i));
            --i;
        }
    }

    public void pse(ArrayList<Objet> obj) {

    }
}
