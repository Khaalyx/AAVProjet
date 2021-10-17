
public class Objet implements Comparable <Objet> {
    private String nom;
    private float poids;
    private float valeur;

    public Objet(){}

    Objet(String n, float poids, float val){
        nom = n;
        this.poids = poids;
        this.valeur = val;
    }


    public float getValeur() {
        return valeur;
    }

    public float getPoids() {
        return poids;
    }

    public String toString() {
        String s = nom + " (Poids : " + poids + " ; Valeur : " + valeur + ")";
        return s;
    }

    private float rapport() {
        return this.valeur/this.poids;
    }

    /**
     * Règle de tri pour la méthode Collections.sort()
     * @param o Objet à comparer
     * @return comparaison du rapport de this et de l'Objet o
     */
    @Override
    public int compareTo(Objet o) {
        return Float.compare(this.rapport(), o.rapport());
    }
}
