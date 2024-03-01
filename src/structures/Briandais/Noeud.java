package structures.Briandais;

public class Noeud {

    public char caractere;
    public Noeud frere;
    public Noeud fils;

    public Noeud(char caractere){
        this.caractere = caractere;
        this.frere = null;
        this.fils = null;
    }

    public Noeud(char caractere, Noeud fils, Noeud frere) {
        this.caractere = caractere;
        this.fils = fils;
        this.frere = frere;
    }

}