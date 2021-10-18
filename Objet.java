public class Objet implements Comparable <Objet> {
    private String nom;
    private float poids;
    private float valeur;

    /**
     * contructeur d'Objet
     * @param n le nom
     * @param poids le poids
     * @param val la valeur
     */
    Objet(String n, float poids, float val){
        nom = n;
        this.poids = poids;
        this.valeur = val;
    }

    /**
     * Retourne la valeur de l'objet
     * @return valeur
     */
    public float getValeur() {
        return valeur;
    }

    /**
     * Retourne le poids de l'objet
     * @return poids
     */
    public float getPoids() {
        return poids;
    }

    /**
     * Affiche les objets, leur poids et leur valeur
     * @return s
     */
    public String toString() {
        String s = nom + " (Poids : " + poids + " ; Valeur : " + valeur + ")";
        return s;
    }

    /**
     * Retourne le rapport valeur/poids d'un objet
     * @return valeur/poids
     */
    private float rapport() {
        return this.valeur/this.poids;
    }

    /**
     * Permet de définir sur mesure une règle de triage que Collections.sort devra suivre
     * Implémenter l'interface de comparateur "Comparable"
     * @param o Objet spécifié
     * @return -1, 0 ou 1
     */
    @Override
    public int compareTo(Objet o) {
        return Float.compare(this.rapport(), o.rapport());
    }
}
