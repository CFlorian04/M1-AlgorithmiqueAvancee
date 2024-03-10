import structures.TrieHybride.HYBNoeud;
import structures.TrieHybride.TrieHybride;
import structures.Briandais.Briandais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import structures.Briandais.BRDNoeud;

public class Comparaison {

    // Temps d'insertion Shakespeare
    public static void InsertTimeShakespeare(int nombreEssais) {
        List<Long> HYBArray = new ArrayList<>();
        List<Long> BRDArray = new ArrayList<>();

        // HYBRIDE
        System.out.println("Insert Shakespeare : Executing Hybrid Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            TrieHybride hybride = new TrieHybride();

            long startTime = System.nanoTime();
            hybride.insertShakespeare(false, false);
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            HYBArray.add(executionTime);
        }

        // BRIANDAIS
        System.out.println("Insert Shakespeare : Executing Briandais Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            Briandais briandais = new Briandais();

            long startTime = System.nanoTime();
            briandais.insertShakespeare(false, false);
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            BRDArray.add(executionTime);
        }

        System.out.println("Insert Shakespeare : Hybrid Trie time : "
                + HYBArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");
        System.out.println("Insert Shakespeare : Briandais Trie time : "
                + BRDArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");

    }

    // Temps d'insertion d'un nouveau mot après insertion Shakespeare
    public static void InsertNewWord(int nombreEssais, String nouveauMot) {
        List<Long> HYBArray = new ArrayList<>();
        List<Long> BRDArray = new ArrayList<>();

        // HYBRIDE
        System.out.println("Insert New Word : Executing Hybrid Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            TrieHybride hybride = new TrieHybride();
            hybride.insertShakespeare(false, false);

            long startTime = System.nanoTime();
            hybride.insertion(nouveauMot);
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            HYBArray.add(executionTime);
        }

        // BRIANDAIS
        System.out.println("Insert New Word : Executing Briandais Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            Briandais briandais = new Briandais();
            briandais.insertShakespeare(false, false);

            long startTime = System.nanoTime();
            briandais.inserer(nouveauMot);
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            BRDArray.add(executionTime);
        }

        System.out.println("Insert New Word : Hybrid Trie time : "
                + HYBArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");
        System.out.println("Insert New Word : Briandais Trie time : "
                + BRDArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");
    }

    // Suppression dans une liste de mots
    public static void DeleteMultipleWords(int nombreEssais, List<String> MotASupprimer) {
        List<Long> HYBArray = new ArrayList<>();
        List<Long> BRDArray = new ArrayList<>();

        // HYBRIDE
        System.out.println("Delete Multiple Words : Executing Hybrid Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            TrieHybride hybride = new TrieHybride();
            hybride.insertShakespeare(false, false);

            long startTime = System.nanoTime();
            for (String mot : MotASupprimer) {
                hybride.suppression(mot);
            }
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            HYBArray.add(executionTime);
        }

        // BRIANDAIS
        System.out.println("Delete Multiple Words : Executing Briandais Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            Briandais briandais = new Briandais();
            briandais.insertShakespeare(false, false);

            long startTime = System.nanoTime();
            for (String mot : MotASupprimer) {
                briandais.suppression(mot);
            }
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            BRDArray.add(executionTime);
        }

        System.out.println("Delete Multiple Words : Hybrid Trie time : "
                + HYBArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");
        System.out.println("Delete Multiple Words : Briandais Trie time : "
                + BRDArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");
    }

    // Recherche dans une liste de mots
    public static void SearchMultipleWords(int nombreEssais, List<String> MotARechercher) {
        List<Long> HYBArray = new ArrayList<>();
        List<Long> BRDArray = new ArrayList<>();

        // HYBRIDE
        System.out.println("Search Multiple Words : Executing Hybrid Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            TrieHybride hybride = new TrieHybride();
            hybride.insertShakespeare(false, false);

            long startTime = System.nanoTime();
            for (String mot : MotARechercher) {
                hybride.recherche(mot);
            }
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            HYBArray.add(executionTime);
        }

        // BRIANDAIS
        System.out.println("Search Multiple Words : Executing Briandais Trie...");
        for (int i = 0; i < nombreEssais; i++) {
            Briandais briandais = new Briandais();
            briandais.insertShakespeare(false, false);

            long startTime = System.nanoTime();
            for (String mot : MotARechercher) {
                briandais.recherche(mot);
            }
            long endTime = System.nanoTime();

            long executionTime = endTime - startTime;
            BRDArray.add(executionTime);
        }

        System.out.println("Search Multiple Words : Hybrid Trie time : "
                + HYBArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");
        System.out.println("Search Multiple Words : Briandais Trie time : "
                + BRDArray.stream().mapToDouble(a -> a).average().getAsDouble() / 1000000 + " ms");
    }

    public static void ProfondeurMoyenne() {
        TrieHybride hybride = new TrieHybride();
        hybride.insertShakespeare(false, false);

        Briandais briandais = new Briandais();
        briandais.insertShakespeare(false, false);

        System.out.println("Structure Average Depth : Hybrid Trie : " + hybride.profondeurMoyenne() + " nodes");
        System.out.println("Structure Average Depth : Briandais Trie : " + briandais.profondeurMoyenne() + " nodes");

    }

    public static void Hauteur() {
        TrieHybride hybride = new TrieHybride();
        hybride.insertShakespeare(false, false);

        Briandais briandais = new Briandais();
        briandais.insertShakespeare(false, false);

        System.out.println("Structure Height : Hybrid Trie : " + hybride.hauteur() + " nodes");
        System.out.println("Structure Height : Briandais Trie : " + briandais.hauteur() + " nodes");

    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("london");
        list.add("alexandria");
        list.add("summer");
        list.add("notwithstanding");
        list.add("when");

        int nombreEssais = 50;

        InsertTimeShakespeare(nombreEssais);

        InsertNewWord(nombreEssais, "algorithmie");

        DeleteMultipleWords(nombreEssais, list);

        SearchMultipleWords(nombreEssais, list);

        ProfondeurMoyenne();

        Hauteur();

    }

}
