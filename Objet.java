
public class Objet {
    private String nom;
    private float poids;
    private float valeur;

    Objet(String n, float poids, float val){
        nom = n;
        this.poids = poids;
        this.valeur = val;
    }

    public String getNom() {
        return nom;
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

}
