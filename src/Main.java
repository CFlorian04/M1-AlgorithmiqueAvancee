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
        //trieHybride.insertFromFile("data/base.txt", true, true);


        System.out.println("----- Briandais -----");
        briandais.insertShakespeare(false,true);
        //briandais.insertFromFile("data/base.txt", true, true);
    }
}
