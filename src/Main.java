import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import structures.Briandais.Briandais;
import structures.TrieHybride.TrieHybride;

public class Main {
    public static void main(String[] args) {

        File[] listOfFiles = new File("data/Shakespeare").listFiles();

        TrieHybride trieHybride = new TrieHybride();
        Briandais briandais = new Briandais();

        int countfile = 0;

        for (File file : listOfFiles) {

            try {
                //System.out.println(file.getAbsolutePath());

                try {
                    Scanner fileIn = new Scanner(new File(file.getAbsolutePath()));

                    while (fileIn.hasNextLine()) {

                        String mot = fileIn.nextLine();

                        if (mot != null && mot != "") {

                            // System.out.println(mot);
                            if (!trieHybride.recherhe(mot))
                            {
                                trieHybride.insertion(mot);
                            }
                            //briandais.inserer(mot);
                        }

                    }

                    fileIn.close();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }

        System.out.println(trieHybride.comptageMots());
        System.out.println(trieHybride.hauteur());
        System.out.println(trieHybride.profondeurMoyenne());

    }
}
