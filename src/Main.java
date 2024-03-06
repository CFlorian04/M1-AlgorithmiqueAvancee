import java.io.File;

import structures.Briandais.Briandais;
import structures.TrieHybride.TrieHybride;

public class Main {
    public static void main(String[] args) {

        TrieHybride trieHybride = new TrieHybride();
        Briandais briandais = new Briandais();

        trieHybride.insertShakespeare(false, true);

        trieHybride.insertFromFile("data/Exemples/exemple_de_base.txt", false, true);

    }
}
