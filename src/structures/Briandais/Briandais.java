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

    public Briandais(char caractere, Noeud fils, Noeud frere){
        this.racine = new Noeud(caractere, fils, frere);
    }
    public void inserer(String mot) {
        racine = BRDinsertion(racine, mot);
    }

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


    private char premier(String s) {
        return s.charAt(0);
    }

    private String reste(String s) {
        return s.substring(1);
    }

    public boolean recherche(String mot) {
        return BRDrecherche(racine, mot + "\0"); 
    }

    
    private boolean BRDrecherche(Noeud noeud, String mot) {
        if (mot.isEmpty()) return true; 
        
        if (noeud == null) return false; 
        
        if (noeud.caractere == premier(mot)) {
            return BRDrecherche(noeud.fils, reste(mot));
        } else {
            return BRDrecherche(noeud.frere, mot);
        }
    }



    public int comptageMots() {
        return BRDcomptageMots(this.racine);
    }

    private int BRDcomptageMots(Noeud noeud) {
        if (noeud == null) return 0;
        
        int compteur = 0;
        if (noeud.caractere == '\0') {
            compteur = 1;
        }

        compteur += BRDcomptageMots(noeud.fils); 
        compteur += BRDcomptageMots(noeud.frere); 

        return compteur;
    }

    public List<String> listeMots() {
        List<String> mots = new ArrayList<>();
        BRDlisteMots(racine, "", mots);
        return mots;
    }

    private void BRDlisteMots(Noeud noeud, String motCourant, List<String> mots) {
        if (noeud == null) return;

        if (noeud.caractere == '\0') {
            mots.add(motCourant);
        }

        BRDlisteMots(noeud.fils, motCourant + noeud.caractere, mots);
        BRDlisteMots(noeud.frere, motCourant, mots);
    }

    public int comptageNil() {
        return BRDcomptageNil(racine);
    }

    private int BRDcomptageNil(Noeud noeud) {
        if (noeud == null) return 1; 

        int compteur = 0;
        compteur += BRDcomptageNil(noeud.fils);
        compteur += BRDcomptageNil(noeud.frere);

        return compteur;
    }

    public int hauteur() {
        return BRDhauteur(racine);
    }

    private int BRDhauteur(Noeud noeud) {
        if (noeud == null) return 0;

        int hauteurFils = BRDhauteur(noeud.fils);
        int hauteurFrere = BRDhauteur(noeud.frere);

        return Math.max(hauteurFils, hauteurFrere) + 1;
    }

    public double profondeurMoyenne() {
        int[] profondeursEtFeuilles = calculerProfondeursEtFeuilles(racine, 0);
        
        if (profondeursEtFeuilles[1] == 0) return 0;
        
        return (double) profondeursEtFeuilles[0] / profondeursEtFeuilles[1];
    }

    private int[] calculerProfondeursEtFeuilles(Noeud noeud, int profondeur) {
        int[] result = new int[]{0, 0}; 
        
        if (noeud == null) {
            return result;
        }

        if (noeud.caractere == '\0') {
            result[0] += profondeur;
            result[1]++;
        }

        int[] profondeursEtFeuillesFils = calculerProfondeursEtFeuilles(noeud.fils, profondeur + 1);
        int[] profondeursEtFeuillesFrere = calculerProfondeursEtFeuilles(noeud.frere, profondeur);

        result[0] += profondeursEtFeuillesFils[0] + profondeursEtFeuillesFrere[0];
        result[1] += profondeursEtFeuillesFils[1] + profondeursEtFeuillesFrere[1];

        return result;
    }

    public int prefixe(String mot) {
        return compterMotsAvecPrefixe(racine, mot);
    }

    private int compterMotsAvecPrefixe(Noeud noeud, String mot) {
        if (noeud == null || mot.isEmpty()) {
            return 0; 
        }
        
        if (premier(mot) < noeud.caractere) {
            return compterMotsAvecPrefixe(noeud.fils, mot); 
        } else if (premier(mot) > noeud.caractere) {
            return compterMotsAvecPrefixe(noeud.frere, mot); 
        } else {
            if (reste(mot).isEmpty()) {
                return BRDcomptageMots(noeud);
            } else {
                return compterMotsAvecPrefixe(noeud.fils, reste(mot));
            }
        }
    }

    public void suppression(String m){
        racine = BRDsuppression(this.racine, m);
    }


    public Noeud BRDsuppression(Noeud A, String m) {
        if (m.isEmpty()) return A.frere;
        
        if (BRDcomptageMots(A) == 1) return null;

        if (A.caractere < premier(m)) {
            return new Noeud(A.caractere, A.fils, BRDsuppression(A.frere, m));
        } else {
            return new Noeud(A.caractere, BRDsuppression(A.fils, reste(m)), A.frere);
        }
    }

    private void insertionPhrase(String phrase) {
        // Séparer le texte en mots et les insérer dans l'arbre
        String[] mots = phrase.split("\\s+|(?=,)|(?<=,)");
        Set<String> setSansDoublons = new LinkedHashSet<>();
        for (String mot : mots) {
            // Ajout du mot au set, les doublons seront ignorés
            setSansDoublons.add(mot);
        }
        for (String mot : setSansDoublons) {
            this.inserer(mot);
            //System.out.println(mot);

        }
        //System.out.println("taille " + setSansDoublons.size());
    }

    public void fusion(Briandais autre) {
        if (autre != null && autre.racine != null) {
            this.racine = BRDfusion(this.racine, autre.racine);
        }
    }
    
    private Noeud BRDfusion(Noeud noeud1, Noeud noeud2) {
        if (noeud1 == null) return noeud2;
        if (noeud2 == null) return noeud1;
    
        if (noeud1.caractere < noeud2.caractere) {
            noeud1.frere = BRDfusion(noeud1.frere, noeud2);
            return noeud1;
        }
        else if (noeud1.caractere > noeud2.caractere) {
            noeud2.frere = BRDfusion(noeud1, noeud2.frere);
            return noeud2;
        }
        else {
            noeud1.fils = BRDfusion(noeud1.fils, noeud2.fils);
            noeud1.frere = BRDfusion(noeud1.frere, noeud2.frere);
            return noeud1;
        }
    }
    
    


    
    
    public static void main(String[] args) {
        Briandais trie = new Briandais();

        String exemple_de_base = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
        trie.insertionPhrase(exemple_de_base);




        Briandais trie2 = new Briandais();
        trie2.inserer("test2");
        trie2.inserer("abc2");

        Briandais trie3 = new Briandais();
        trie3.inserer("test1");
        trie3.inserer("abc3");
        trie3.inserer("abc");
        
        trie2.fusion(trie3);
        

        List<String> liste = trie2.listeMots();
        for(String mot : liste){
            System.out.println(mot);
        }

        


        



    }
    

}



