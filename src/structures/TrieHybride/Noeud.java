package structures.TrieHybride;

public class Noeud {

    public char caractere;
    public Noeud droit, centre, gauche;
    public boolean finMot;

    public Noeud(char caractere) {
        this.caractere = caractere;
        this.gauche = null;
        this.centre = null;
        this.droit = null;
        this.finMot = false;
    }

    public Noeud(char caractere, Noeud gauche, Noeud centre, Noeud droit, Boolean finMot) {
        this.caractere = caractere;
        this.gauche = gauche;
        this.centre = centre;
        this.droit = droit;
        this.finMot = finMot;
    }
    
}
