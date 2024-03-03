import structures.TrieHybride.*;

public class Main {
    public static void main(String[] args) {

        Noeud N1 = new Noeud('C', null, null, null, false);

        System.out.println(N1.getGauche());

        TrieHybride TH1 = new TrieHybride(N1);

        System.out.println(TH1.estVide());

    }
}
