package structures.Briandais;
import java.util.LinkedHashSet;
import java.util.Set;


public class Briandais {

    private Noeud racine;

    public Briandais() {
        this.racine = new Noeud('\0'); 
    }
    // Méthode pour insérer un mot dans l'arbre
    public void inserer(String mot) {
        racine = BRDinsertion(racine, mot);
    }

    // Fonction récursive d'insertion 
    private Noeud BRDinsertion(Noeud A, String m) {
        if (m.equals("")) {
            return new Noeud('\0', null, A); 
        }
        if (A == null) {
            return BRDcons(m);
        }
        char t = premier(m);
        if (A.caractere < t) {
            A.frere = BRDinsertion(A.frere, m);
            return A;
        }
        if (A.caractere == t) {
            A.fils = BRDinsertion(A.fils, reste(m));
            return A;
        }
        if (A.caractere > t) {
            return new Noeud(t, BRDcons(reste(m)), A);
        }
        return A; 
    }

    private Noeud BRDcons(String m) {
        if (m.equals("")) {
            return new Noeud('\0', null, null); 
        } else {
            return new Noeud(premier(m), BRDcons(reste(m)), null);
        }
    }

    // Fonction pour obtenir le premier caractère d'une chaîne
    private char premier(String s) {
        return s.charAt(0);
    }

    // Fonction pour obtenir la chaîne sans le premier caractère
    private String reste(String s) {
        return s.substring(1);
    }

    public boolean recherche(String mot) {
        return recherche(racine, mot + "\0"); 
    }

    
    private boolean recherche(Noeud noeud, String mot) {
        if (mot.isEmpty()) {
            return true; // Tous les caractères ont été trouvés ou c'est un mot vide
        }
        if (noeud == null) {
            return false; // Fin du parcours sans trouver le mot
        }
        

        // Si le caractère actuel correspond, continuer avec le fils
        if (noeud.caractere == premier(mot)) {
            return recherche(noeud.fils, reste(mot));
        } else {
            // Sinon, passer au frère suivant
            return recherche(noeud.frere, mot);
        }
    }


    
    public static void main(String[] args) {
        Briandais trie = new Briandais();

        String exemple_de_base = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
        
        // Séparer le texte en mots et les insérer dans l'arbre
        /*String[] mots = exemple_de_base.split("\\s+|(?=,)|(?<=,)");
        Set<String> setSansDoublons = new LinkedHashSet<>();
        for (String mot : mots) {
            // Ajout du mot au set, les doublons seront ignorés
            setSansDoublons.add(mot);
        }
        for (String mot : setSansDoublons) {
            trie.inserer(mot);
            System.out.println(mot);

        }
        System.out.println("taille " + setSansDoublons.size());*/


        Briandais trie2 = new Briandais();
        trie2.inserer("abc2");
        trie2.inserer("abc1");
        trie2.inserer("test2");

        
    



    }
    

}



