package structures.TrieHybride;

public class HYBNoeud {

    public char caractere;
    public HYBNoeud droit, centre, gauche;
    public boolean finMot;

    public HYBNoeud(char caractere) {
        this.caractere = caractere;
        this.gauche = null;
        this.centre = null;
        this.droit = null;
        this.finMot = false;
    }

    public HYBNoeud(char caractere, HYBNoeud gauche, HYBNoeud centre, HYBNoeud droit, Boolean finMot) {
        this.caractere = caractere;
        this.gauche = gauche;
        this.centre = centre;
        this.droit = droit;
        this.finMot = finMot;
    }
    
}
