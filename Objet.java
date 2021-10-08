
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
        String s = nom + " ; " + poids + " ; " + valeur;
        return s;
    }

    private float rapport() {
        return this.valeur/this.poids;
    }

    @Override
    public int compareTo(Objet o) {
        return Float.compare(this.rapport(), o.rapport());
    }
}
