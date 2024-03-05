package structures.Briandais;

public class BRDNoeud {

    public char caractere;
    public BRDNoeud frere;
    public BRDNoeud fils;

    public BRDNoeud(char caractere){
        this.caractere = caractere;
        this.frere = null;
        this.fils = null;
    }

    public BRDNoeud(char caractere, BRDNoeud fils, BRDNoeud frere) {
        this.caractere = caractere;
        this.fils = fils;
        this.frere = frere;
    }

}