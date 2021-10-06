import java.util.ArrayList;

public class ABR {
    private ArrayList<Objet> listeObjets;
    private ABR filsGauche, filsDroit;

    public ABR() {}

    public ABR(ArrayList<Objet> obj) {
        this.listeObjets = obj;
        this.filsGauche = new ABR();
        this.filsDroit = new ABR();
    }

    public boolean isEmpty() { return listeObjets == null; }

    public void ajouter(ArrayList<Objet> obj) {
        if(this.filsDroit.isEmpty()){
            this.filsDroit = new ABR(obj);
            this.filsGauche = new ABR(this.listeObjets);
        }
        else {
            this.filsDroit.ajouter(obj);
        }
    }


}
