import java.util.ArrayList;

public class ABR {
    private ArrayList<Objet> listeObjets;
    private ABR filsGauche, filsDroit;
    private static float borneInf;
    private float borneSup;
    private int profondeur;
    private static ArrayList<Objet> meilleureSolution;

    public ABR(ArrayList<Objet> obj, ArrayList<Objet> objAMettre, float poidsMax, int i) {
        this.listeObjets = obj; // objets du sac au noeud courant
        this.profondeur = i;
        calculBorneSup(objAMettre);
        calculBorneInf();

        // pas atteint la fin de la liste d'objets à mettre
        if(profondeur < objAMettre.size()) {
            this.filsGauche = new ABR(listeObjets, objAMettre, poidsMax, profondeur + 1);
            ArrayList<Objet> listObj = new ArrayList<>();
            if(obj != null) // si la liste d'objets du noeud courant n'est pas vide
                for (Objet o : obj)
                    listObj.add(o); // on copie les objets dans la new list
            listObj.add(objAMettre.get(profondeur)); // on y ajoute l'objet à l'index i = profondeur
            // poids de listObj (objets + nouvel objet) <= poidsMax
            if(poidsListeObjets(listObj) <= poidsMax && borneSup > borneInf)
                this.filsDroit = new ABR(listObj, objAMettre, poidsMax, profondeur + 1);
        }
    }

    /**
     * Actualiser borneInf lorsqu’est trouvée une solution réalisable
     * qui possède une valeur plus grande, et enregistrer cette liste d'objets
     * comme meilleure solution
     */
    private void calculBorneInf(){
        if(valeurListeObjets() > ABR.borneInf) {
            borneInf = valeurListeObjets(); // mise à jour de sa valeur
            meilleureSolution = this.listeObjets;
        }
    }

    /**
     * Calcule la somme de toutes les valeurs de tous les objets déjà mis dans le sac
     * + la somme des valeurs des objets restants dont on ne sait pas encore s’ils seront dans le sac
     */
    private void calculBorneSup(ArrayList<Objet> obj){
        float val = valeurListeObjets(); // valeur totale du noeud courant
        if(obj!= null) {
            for (int i = this.profondeur; i < obj.size(); ++i)
                val += obj.get(i).getValeur(); // ajouter les valeurs des objets restants
        }
        borneSup = val;
    }

    /**
     * Retourne la valeur totale de listeObjets
     * @return valTotale
     */
    private float valeurListeObjets(){
        float valTotale = 0;
        if(this.listeObjets != null) {
            for (Objet obj : this.listeObjets)
                valTotale += obj.getValeur();
        }
        return valTotale;
    }

    /**
     * Retourne le poids total d'une liste d'objets
     * @return poidsTotal
     */
    private float poidsListeObjets(ArrayList<Objet> obj) {
        float poidsTotal = 0;
        if(obj != null) {
            for (Objet o : obj) {
                poidsTotal += o.getPoids();
            }
        }
        return poidsTotal;
    }

    /**
     * Retourne la meilleure solution
     * @return meilleureSolution une liste d'objets correspondant à la solution optimale
     */
    public ArrayList<Objet> getMeilleureSolution() {
        return meilleureSolution;
    }

}
