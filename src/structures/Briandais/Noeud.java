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


}