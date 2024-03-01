package structures.Briandais;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;


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



    public int comptageMots() {
        return comptageMots(this.racine);
    }

    // Méthode récursive pour compter les mots
    private int comptageMots(Noeud noeud) {
        if (noeud == null) return 0;
        
        int compteur = 0;
        if (noeud.caractere == '\0') {
            compteur = 1;
        }

        // Compter récursivement les mots dans les noeuds fils et frère
        compteur += comptageMots(noeud.fils); 
        compteur += comptageMots(noeud.frere); 

        return compteur;
    }

    public List<String> listeMots() {
        List<String> mots = new ArrayList<>();
        rec_listeMots(racine, "", mots);
        return mots;
    }

    // Fonction récursive pour parcourir l'arbre 
    private void rec_listeMots(Noeud noeud, String motCourant, List<String> mots) {
        if (noeud == null) return;

        // Ajouter le mot courant si c'est une fin de mot 
        if (noeud.caractere == '\0') {
            mots.add(motCourant);
        }

        // Parcourir les fils 
        rec_listeMots(noeud.fils, motCourant + noeud.caractere, mots);

        // Parcourir les frères 
        rec_listeMots(noeud.frere, motCourant, mots);
    }

    public int comptageNil() {
        return rec_comptageNil(racine);
    }

    // Fonction récursive pour compter les pointeurs vers Nil
    private int rec_comptageNil(Noeud noeud) {
        if (noeud == null) return 1; // Pointeur vers Nil

        int compteur = 0;
        compteur += rec_comptageNil(noeud.fils);
        compteur += rec_comptageNil(noeud.frere);

        return compteur;
    }

    public int hauteur() {
        return rec_hauteur(racine);
    }

    private int rec_hauteur(Noeud noeud) {
        if (noeud == null) return 0;

        // Calculer récursivement la hauteur des sous-arbres fils et frères
        int hauteurFils = rec_hauteur(noeud.fils);
        int hauteurFrere = rec_hauteur(noeud.frere);

        // La hauteur de l'arbre est la hauteur maximale entre les fils et les frères +1 pour le nœud actuel
        return Math.max(hauteurFils, hauteurFrere) + 1;
    }

    public double profondeurMoyenne() {
        // Appel à une fonction auxiliaire pour calculer la profondeur moyenne
        int[] profondeursEtFeuilles = calculerProfondeursEtFeuilles(racine, 0);
        
        // Éviter une division par zéro
        if (profondeursEtFeuilles[1] == 0) return 0;
        
        // Calcul de la profondeur moyenne
        return (double) profondeursEtFeuilles[0] / profondeursEtFeuilles[1];
    }

    // Fonction récursive pour calculer la profondeur moyenne des feuilles
    private int[] calculerProfondeursEtFeuilles(Noeud noeud, int profondeur) {
        int[] result = new int[]{0, 0}; // Tableau pour stocker la somme des profondeurs et le nombre de feuilles
        
        if (noeud == null) {
            return result;
        }

        // Si le nœud actuel est une feuille, mettre à jour la somme des profondeurs et le nombre de feuilles
        if (noeud.caractere == '\0') {
            result[0] += profondeur;
            result[1]++;
        }

        // Parcourir récursivement les fils et les frères avec une profondeur augmentée de 1
        int[] profondeursEtFeuillesFils = calculerProfondeursEtFeuilles(noeud.fils, profondeur + 1);
        int[] profondeursEtFeuillesFrere = calculerProfondeursEtFeuilles(noeud.frere, profondeur);

        // Ajouter les résultats des fils et des frères
        result[0] += profondeursEtFeuillesFils[0] + profondeursEtFeuillesFrere[0];
        result[1] += profondeursEtFeuillesFils[1] + profondeursEtFeuillesFrere[1];

        return result;
    }

    public int prefixe(String mot) {
        return compterMotsAvecPrefixe(racine, mot);
    }

    // Fonction récursive pour compter le nombre de mots ayant le préfixe 
    private int compterMotsAvecPrefixe(Noeud noeud, String mot) {
        if (noeud == null || mot.isEmpty()) {
            return 0; 
        }
        
        char premierCaractere = mot.charAt(0);
        
        if (premierCaractere < noeud.caractere) {
            return compterMotsAvecPrefixe(noeud.fils, mot); 
        } else if (premierCaractere > noeud.caractere) {
            return compterMotsAvecPrefixe(noeud.frere, mot); 
        } else {
            String resteMot = mot.substring(1);
            if (resteMot.isEmpty()) {
                return comptageMots(noeud);
            } else {
                return compterMotsAvecPrefixe(noeud.fils, resteMot);
            }
        }
    }

    public Noeud suppression(String m){
        return rec_suppression(this.racine, m);
    }


    public Noeud rec_suppression(Noeud A, String m) {
        if (m.isEmpty()) return A.frere;
        
        if (comptageMots(A) == 1) return null;

        if (A.caractere < premier(m)) {
            // Si la clé de A est inférieure à t, aller vers le frère de A
            return new Noeud(A.caractere, A.fils, rec_suppression(A.frere, m));
        } else {
            // Sinon, si la clé de A est égale à t
            // Supprimer récursivement le reste de m dans le fils de A
            return new Noeud(A.caractere, rec_suppression(A.fils, reste(m)), A.frere);
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
        trie2.inserer("test6");
        trie2.inserer("abc");
        trie2.inserer("abc1");
        trie2.inserer("test");
        trie2.inserer("testtcjs");trie2.inserer("testvdfv");trie2.inserer("testdsf");


        System.out.println(trie2.comptageMots());

        List<String> liste = trie2.listeMots();
        for(String mot : liste){
            System.out.println(mot);
        }

        System.out.println(trie2.recherche("abc1")); 
        trie2.racine = trie2.suppression("abc1");
        System.out.println(trie2.recherche("abc1")); 


        



    }
    

}



