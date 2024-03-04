package structures.TrieHybride;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import structures.Briandais.Briandais;
import structures.TrieHybride.Noeud;

public class TrieHybride {

    private Noeud racine;

    ///////////////////
    // Constructeurs //
    ///////////////////
    public TrieHybride() {
        this.racine = new Noeud('\0');
    }

    public TrieHybride(Noeud racine) {
        this.racine = racine;
    }

    // Retourne le premier caractère
    private char first(String mot) {
        return mot.charAt(0);
    }

    // Retoune tous les caractères sauf le premier
    private String rest(String mot) {
        return mot.substring(1);
    }

    // Garde que les lettres et les mets en minuscule
    private static String formatText(String mot) {
        return mot.toLowerCase().replaceAll("[^a-z]", "");
    }

    private void insertion(String mot) {
        this.racine = HYBInsertion(racine, formatText(mot));
    }

    private Noeud HYBInsertion(Noeud noeud, String mot) {
        if (mot.length() == 0) {
            return noeud;
        }

        if (noeud == null) {
            return HYBConstruct(mot);
        }

        char p = first(mot);

        // Si le caractère du Noeud est vide
        if (noeud.caractere == '\0') {
            noeud.caractere = p;

            // Si c'est le dernier caractère -> Donc fin de mot
            if (mot.length() == 1) {
                noeud.finMot = true;
                return noeud;
            }

            // Sinon on continue l'insertion
            noeud.centre = HYBInsertion(noeud.centre, rest(mot));
            return noeud;

        }

        // Si le caractère du Noeud est égale au premier caractère du mot
        if (noeud.caractere == p) {
            noeud.centre = HYBInsertion(noeud.centre, rest(mot));
            return noeud;
        }

        // Si le caractère du Noeud est supérieur au premier caractère du mot
        if (noeud.caractere > p) {
            noeud.droit = HYBInsertion(noeud.droit, mot);
            return noeud;
        }

        // Si le caractère du Noeud est inférieur au premier caractère du mot
        if (noeud.caractere < p) {
            noeud.gauche = HYBInsertion(noeud.gauche, mot);
            return noeud;
        }

        return noeud;
    }

    private Noeud HYBConstruct(String mot) {
        if (mot.length() == 0) {
            return new Noeud('\0', null, null, null, true);
        } else {
            return new Noeud(first(mot), null, HYBConstruct(rest(mot)), null, false);
        }
    }

    private boolean recherhe(String mot) {
        return HYBRecherche(this.racine, formatText(mot));
    }

    private Boolean HYBRecherche(Noeud noeud, String mot) {
        if (mot.isEmpty()) {
            return true;
        }

        if (noeud == null) {
            return false;
        }

        char p = first(mot);

        if (noeud.caractere == p) {
            return HYBRecherche(noeud.centre, rest(mot));
        } else if (noeud.caractere > p) {
            return HYBRecherche(noeud.droit, mot);
        } else if (noeud.caractere < p) {
            return HYBRecherche(noeud.gauche, mot);
        } else {
            System.err.println("Erreur avec noeud : " + noeud.caractere + "et le mot : " + mot);
            return false;
        }
    }

    private int comptageMots() {
        return HYBComptageMots(this.racine);
    }

    private int HYBComptageMots(Noeud noeud) {
        if (noeud == null) {
            return 0;
        }

        int count = 0;

        // Si le nœud actuel est une fin de mot
        if (noeud.finMot) {
            count++;
        }

        count += HYBComptageMots(noeud.gauche);
        count += HYBComptageMots(noeud.centre);
        count += HYBComptageMots(noeud.droit);

        return count;

    }

    private List<String> listeMots() {
        List<String> motsListe = new ArrayList<>();
        HYBListeMots(this.racine, "", motsListe);
        return motsListe;
    }
    
    private void HYBListeMots(Noeud noeud, String motActuel, List<String> motsListe) {
        if (noeud == null) {
            return;
        }

        // Parcours in-order
        HYBListeMots(noeud.gauche, motActuel, motsListe);

        // Ajout du caractère du nœud au mot actuel
        String nouveauMotActuel = motActuel + noeud.caractere;

        // Si le nœud marque la fin d'un mot, ajoute le mot à la liste
        if (noeud.finMot) {
            motsListe.add(nouveauMotActuel);
        }

        // Parcours in-order du sous-arbre central
        HYBListeMots(noeud.centre, nouveauMotActuel, motsListe);

        // Parcours in-order du sous-arbre droit
        HYBListeMots(noeud.droit, nouveauMotActuel, motsListe);
    }

    private int comptageNil() {
        return HYBComptageNil(this.racine);
    }

    private int HYBComptageNil(Noeud noeud) {
        if (noeud == null) {
            return 1;
        }

        int count = 0;

        count += HYBComptageNil(noeud.gauche);
        count += HYBComptageNil(noeud.centre);
        count += HYBComptageNil(noeud.droit);

        return count;
    }

    private int hauteur() {
        return HYBHauteur(this.racine);
    }

    private int HYBHauteur(Noeud noeud) {
        if (noeud == null) {
            return 0;
        }

        int hauteurGauche = HYBHauteur(noeud.gauche);
        int hauteurCentre = HYBHauteur(noeud.centre);
        int hauteurDroit = HYBHauteur(noeud.droit);

        return 1 + Math.max(Math.max(hauteurGauche, hauteurCentre), hauteurDroit);
    }

    private double profondeurMoyenne() {
        return HYBprofondeurMoyenne(this.racine, 0);
    }

    private double HYBprofondeurMoyenne(Noeud noeud, int profondeur) {
        if (noeud == null) {
            return 0;
        }

        if (noeud.gauche == null && noeud.centre == null && noeud.droit == null) {
            return profondeur;
        }

        double sum = 0;
        int count = 0;

        if (noeud.gauche != null) {
            sum += HYBprofondeurMoyenne(noeud.gauche, profondeur + 1);
            count++;
        }

        if (noeud.centre != null) {
            sum += HYBprofondeurMoyenne(noeud.centre, profondeur + 1);
            count++;
        }

        if (noeud.droit != null) {
            sum += HYBprofondeurMoyenne(noeud.droit, profondeur + 1);
            count++;
        }

        return sum / count;
    }

    private int prefixe(String mot) {
        return HYBPrefixe(this.racine, mot, 0);
    }

    private int HYBPrefixe(Noeud noeud, String mot, int index) {
        if (noeud == null || index == mot.length()) {
            return 0;
        }

        char p = mot.charAt(index);

        if (noeud.caractere == p) {
            if (index == mot.length() - 1) {
                return HYBComptageMots(noeud.centre);
            } else {
                return HYBPrefixe(noeud.centre, mot, index + 1);
            }
        } else if (noeud.caractere > p) {
            return HYBPrefixe(noeud.droit, mot, index);
        } else {
            return HYBPrefixe(noeud.gauche, mot, index);
        }
    }

    private void suppression(String mot)
    {
        this.racine = HYBSuppression(this.racine, formatText(mot), 0);
    }

    private Noeud HYBSuppression(Noeud noeud, String mot, int index) {
        if (noeud == null) {
            return null;
        }

        if (index == mot.length()) {
            noeud.finMot = false;
        } else {
            char p = mot.charAt(index);

            if (noeud.caractere == p) {
                noeud.centre = HYBSuppression(noeud.centre, mot, index + 1);
            } else if (noeud.caractere > p) {
                noeud.droit = HYBSuppression(noeud.droit, mot, index);
            } else {
                noeud.gauche = HYBSuppression(noeud.gauche, mot, index);
            }
        }

        if (noeud.centre == null && noeud.gauche == null && noeud.droit == null && !noeud.finMot) {
            return null;
        }

        return noeud;
    }

    private void equilibrer() {
        this.racine = HYBEquilibrer(this.racine);
    }
    
    private Noeud HYBEquilibrer(Noeud noeud) {
        if (noeud == null) {
            return null;
        }
    
        // Rééquilibrage des sous-arbres gauche, centre et droit
        noeud.gauche = HYBEquilibrer(noeud.gauche);
        noeud.centre = HYBEquilibrer(noeud.centre);
        noeud.droit = HYBEquilibrer(noeud.droit);
    
        // Calcul de la hauteur des sous-arbres gauche, centre et droit
        int hauteurGauche = HYBHauteur(noeud.gauche);
        int hauteurCentre = HYBHauteur(noeud.centre);
        int hauteurDroit = HYBHauteur(noeud.droit);
    
        // Rééquilibrage avec rotation droite
        if (hauteurGauche - hauteurCentre > 1) {
            Noeud nouveauNoeud = noeud.gauche;
            noeud.gauche = nouveauNoeud.droit;
            nouveauNoeud.droit = noeud;
            noeud = nouveauNoeud;
        }
    
        // Rééquilibrage avec rotation gauche
        if (hauteurDroit - hauteurCentre > 1) {
            Noeud nouveauNoeud = noeud.droit;
            noeud.droit = nouveauNoeud.gauche;
            nouveauNoeud.gauche = noeud;
            noeud = nouveauNoeud;
        }
    
        return noeud;
    }


    // QUESTION 1.0.5
    // Récupéré de Anh
    private void insertionPhrase(String phrase) {
        // Séparer le texte en mots et les insérer dans l'arbre
        String[] mots = phrase.split("\\s+|(?=,)|(?<=,)");
        Set<String> setSansDoublons = new LinkedHashSet<>();
        for (String mot : mots) {
            // Ajout du mot au set, les doublons seront ignorés
            setSansDoublons.add(mot);
        }
        for (String mot : setSansDoublons) {
            this.insertion(mot);
            //System.out.println(mot);

        }
        //System.out.println("taille " + setSansDoublons.size());
    }

    public static void main(String[] args) {

        TrieHybride trieHybride = new TrieHybride();

        // trieHybride.insertion("pomme");
        // trieHybride.insertion("poisson");
        // trieHybride.insertion("poil");
        // trieHybride.insertion("pollution");
        // trieHybride.insertion("porte");

        trieHybride.insertionPhrase("A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches duclavier de la machine a ecrire ?");

        trieHybride.equilibrer();

        System.out.println(trieHybride.hauteur());
        System.out.println(trieHybride.profondeurMoyenne());

        List<String> motsListe = trieHybride.listeMots();
        for (String mot : motsListe) {
            System.out.println(mot);
        }
        


    }

}
