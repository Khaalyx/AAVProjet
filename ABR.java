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
        calculBorneSup(obj);
        calculBorneInf(obj);

        // pas atteint la fin de la liste d'objets à mettre
        if (profondeur < objAMettre.size()) {
            this.filsGauche = new ABR(listeObjets, objAMettre, poidsMax, profondeur + 1);
            ArrayList<Objet> listObj = new ArrayList<>();
            if(obj != null) // si la liste d'objets n'est pas vide
                for(Objet o : obj)
                    listObj.add(o); // on les copie dans la new list
            listObj.add(objAMettre.get(profondeur)); // on y ajoute les objets à mettre au noeud courant
            // poids de listObj (objets + objets à mettre?) < poidsMax
            if (poidsListeObjets(listObj) <= poidsMax && borneSup > borneInf)
                this.filsDroit = new ABR(listObj, objAMettre, poidsMax, profondeur + 1);
        }
    }
    
    public ABR getFilsGauche() {
        return filsGauche;
    }

    public ABR getFilsDroit() {
        return filsDroit;
    }
    
    /**
     * Recherche la meilleure solution
    */
    public void meilleureSolution() {

    }

    /**
     * Actualiser borneInf lorsqu’est trouvée une solution réalisable
     * qui possède une valeur plus grande
    */
    public void calculBorneInf(ArrayList<Objet> objets) {
        if (valeurListeObjets(objets) > ABR.borneInf) // ABR car borneInf est static
            ABR.borneInf = valeurListeObjets(objets); // mise à jour de sa valeur
    }

    /**
     * Calcule la somme de toutes les valeurs de tous les objets déjà mis dans le sac
     * + la somme des valeurs des objets restants dont on ne sait pas encore s’ils seront dans le sac
    */
    public void calculBorneSup(ArrayList<Objet> objets) {
        float val = 0;
		val += valeurListeObjets(listeObjets); // valeur totale du noeud courant
		for (int i = profondeur; i < objets.size(); ++i){
			val += objets.get(i).getValeur(); // ajouter les valeurs des objets restants
		}
		borneSup = val;
    }

    /**
     * Retourne la valeur totale de listeObjets
     * @return valTotale
     */
    public float valeurListeObjets(ArrayList<Objet> obj) {
        float valTotale = 0;
        for (int i = 0; i < obj.size(); ++i)
            valTotale += obj.get(i).getValeur();
        return valTotale;
    }

    /**
     * Retourne le poids total d'une liste d'objets
     * @return poidsTotal
     */
    public float poidsListeObjets(ArrayList<Objet> obj) {
        float poidsTotal = 0;
        for (int i = 0; i < obj.size(); ++i)
            poidsTotal += obj.get(i).getPoids();
        return poidsTotal;
    }
}
