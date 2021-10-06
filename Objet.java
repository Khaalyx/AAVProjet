
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
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
