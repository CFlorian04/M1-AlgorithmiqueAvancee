import java.io.File;

import structures.Briandais.Briandais;
import structures.TrieHybride.TrieHybride;

public class Main {
    public static void main(String[] args) {

        TrieHybride trieHybride = new TrieHybride();
        Briandais briandais = new Briandais();

        //trieHybride.insertFromFile("data/Exemples/exemple_de_base.txt", false, true);
        
        System.out.println("----- Trie Hybride -----");
        trieHybride.insertShakespeare(false, true);

        briandais = trieHybride.toBRD();

        System.out.println("----- Briandais -----");
        System.out.println("Profondeur moyenne : " + briandais.profondeurMoyenne());
        System.out.println("Hauteur : " + briandais.hauteur());
        System.out.println("Nombre de mots : " + briandais.comptageMots());
        System.out.println("Nombre de noeuds nuls : " + briandais.comptageNil());

        //System.out.println("----- Briandais -----");
        //briandais.insertShakespeare(false,true);

    }
}
