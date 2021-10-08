import java.util.ArrayList;

public class ABR {
    private ArrayList<Objet> listeObjets;
    private ABR filsGauche, filsDroit;
    private static float borneInf;
    private float borneSup;
    private int profondeur;

    public ABR(ArrayList<Objet> obj, ArrayList<Objet> objAMettre, float poidsMax, int i) {
        this.listeObjets = obj;
        this.profondeur = i;

        if(profondeur < objAMettre.size()) {
            this.filsGauche = new ABR(listeObjets, objAMettre, poidsMax, profondeur + 1);
            ArrayList<Objet> listObj = new ArrayList<>();
            if(obj != null)
                for(Objet o : obj)
                    listObj.add(o);
            listObj.add(objAMettre.get(profondeur));
            this.filsDroit = new ABR(listObj, objAMettre, poidsMax, profondeur + 1);
        }
    }
}
