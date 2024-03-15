package structures.TrieHybride;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import structures.Briandais.BRDNoeud;
import structures.Briandais.Briandais;

public class TrieHybride {

    public HYBNoeud racine;

    ///////////////////
    // Constructeurs //
    ///////////////////
    public TrieHybride() {
        this.racine = new HYBNoeud('\0');
    }

    public TrieHybride(HYBNoeud racine) {
        this.racine = racine;
    }

    // Retourne le premier caractère
    public char first(String mot) {
        return mot.charAt(0);
    }

    // Retoune tous les caractères sauf le premier
    public String rest(String mot) {
        return mot.substring(1);
    }

    // Garde que les lettres et les mets en minuscule
    public static String formatText(String mot) {
        return mot.toLowerCase().replaceAll("[^a-z]", "");
    }

    public void insertion(String mot) {
        this.racine = HYBInsertion(racine, formatText(mot));
    }

    public HYBNoeud HYBInsertion(HYBNoeud noeud, String mot) {

        if (mot == null || mot.isEmpty()) {
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

            // Si c'est le dernier caractère -> Donc fin de mot
            if (mot.length() == 1) {
                noeud.finMot = true;
                return noeud;
            }

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

    public HYBNoeud HYBConstruct(String mot) {
        if (mot.length() == 1) {
            return new HYBNoeud(first(mot), null, null, null, true);
        } else {
            return new HYBNoeud(first(mot), null, HYBConstruct(rest(mot)), null, false);
        }
    }

    public boolean recherche(String mot) {
        return HYBRecherche(this.racine, formatText(mot));
    }

    public Boolean HYBRecherche(HYBNoeud noeud, String mot) {

        if (mot.isEmpty()) {
            return false;
        }

        if (noeud == null) {
            return false;
        }

        char p = first(mot);

        if (noeud.caractere == p) {
            if (mot.length() == 1)
                return noeud.finMot;
            else
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

    public int comptageMots() {
        return HYBComptageMots(this.racine);
    }

    public int HYBComptageMots(HYBNoeud noeud) {
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

    public List<String> listeMots() {
        List<String> motsListe = new ArrayList<>();
        HYBListeMots(this.racine, "", motsListe);
        return motsListe;
    }

    private void HYBListeMots(HYBNoeud noeud, String motActuel, List<String> motsListe) {
        if (noeud == null) {
            return;
        }

        
        // Ajout du caractère du nœud au mot actuel
        String nouveauMotActuel = motActuel + noeud.caractere;

        // Si le nœud marque la fin d'un mot, ajoute le mot à la liste
        if (noeud.finMot) {
            motsListe.add(nouveauMotActuel);
        }

        // Parcours in-order
        HYBListeMots(noeud.droit, motActuel, motsListe);


        // Parcours in-order du sous-arbre central
        HYBListeMots(noeud.centre, nouveauMotActuel, motsListe);

        // Parcours in-order du sous-arbre droit
        HYBListeMots(noeud.gauche, motActuel, motsListe);
    }

    public int comptageNil() {
        return HYBComptageNil(this.racine);
    }

    public int HYBComptageNil(HYBNoeud noeud) {
        if (noeud == null) {
            return 1;
        }

        int count = 0;

        count += HYBComptageNil(noeud.gauche);
        count += HYBComptageNil(noeud.centre);
        count += HYBComptageNil(noeud.droit);

        return count;
    }

    public int hauteur() {
        return HYBHauteur(this.racine);
    }

    public int HYBHauteur(HYBNoeud noeud) {
        if (noeud == null) {
            return 0;
        }

        int hauteurGauche = HYBHauteur(noeud.gauche);
        int hauteurCentre = HYBHauteur(noeud.centre);
        int hauteurDroit = HYBHauteur(noeud.droit);

        return 1 + Math.max(Math.max(hauteurGauche, hauteurCentre), hauteurDroit);
    }

    public double profondeurMoyenne() {
        return HYBprofondeurMoyenne(this.racine, 0);
    }

    public double HYBprofondeurMoyenne(HYBNoeud noeud, int profondeur) {
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

    public int prefixe(String mot) {
        return HYBPrefixe(this.racine, mot, 0);
    }

    public int HYBPrefixe(HYBNoeud noeud, String mot, int index) {
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

    public void suppression(String mot) {
        this.racine = HYBSuppression(this.racine, formatText(mot), 0);
    }

    public HYBNoeud HYBSuppression(HYBNoeud noeud, String mot, int index) {
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

    public void equilibrer() {
        this.racine = HYBEquilibrer(this.racine);
    }

    private HYBNoeud HYBEquilibrer(HYBNoeud noeud) {

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
            HYBNoeud nouveauNoeud = noeud.gauche;
            noeud.gauche = nouveauNoeud.droit;
            nouveauNoeud.droit = noeud;
            noeud = nouveauNoeud;
        }

        // Rééquilibrage avec rotation gauche
        if (hauteurDroit - hauteurCentre > 1) {
            HYBNoeud nouveauNoeud = noeud.droit;
            noeud.droit = nouveauNoeud.gauche;
            nouveauNoeud.gauche = noeud;
            noeud = nouveauNoeud;
        }

        return noeud;
    }

    public Briandais toBRD() {
        Briandais briandais = new Briandais();
        briandais.racine = HYBtoBRD(this.racine);
        return briandais;
    }

    private BRDNoeud HYBtoBRD(HYBNoeud hybNoeud) {
        if (hybNoeud == null) {
            return null;
        }

        BRDNoeud brdNoeud = new BRDNoeud(hybNoeud.caractere);

        if (hybNoeud.finMot) {
            brdNoeud.fils = new BRDNoeud('\0');
        }

        brdNoeud.frere = HYBtoBRD(hybNoeud.centre);
        brdNoeud.fils = HYBtoBRD(hybNoeud.gauche);

        return brdNoeud;
    }

    // QUESTION 1.0.5 - Insertion de la phrase de base
    public void insertFromFile(String path, boolean showWordsList, boolean showStats) {
        File file = new File(path);
        try (Scanner fileIn = new Scanner(new File(file.getAbsolutePath()))) {
            while (fileIn.hasNext()) {
                String mot = formatText(fileIn.next());
                if (mot != null && mot != "") {
                    if (!this.recherche(mot)) {
                        this.insertion(mot);
                    }
                }
            }
            fileIn.close();

            if (showWordsList) {
                List<String> motsListe = this.listeMots();
                for (String mots : motsListe) {
                    System.out.println(mots);
                }
            }

            if (showStats) {
                System.out.println("Profondeur moyenne : " + this.profondeurMoyenne());
                System.out.println("Hauteur : " + this.hauteur());
                System.out.println("Nombre de mots : " + this.comptageMots());
                System.out.println("Nombre de noeuds nuls : " + this.comptageNil());
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // QUESTION 5.0.11 - Insertion des oeuvres de Shakespeare
    public void insertShakespeare(boolean showWordsList, boolean showStats) {
        File[] listOfFiles = new File("data/Shakespeare").listFiles();

        for (File file : listOfFiles) {
            try {
                try {
                    Scanner fileIn = new Scanner(new File(file.getAbsolutePath()));
                    while (fileIn.hasNextLine()) {
                        String mot = formatText(fileIn.nextLine());
                        if (mot != null && mot != "") {
                            if (!this.recherche(mot)) {
                                this.insertion(mot);
                            }
                        }
                    }
                    fileIn.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (showWordsList) {
            List<String> motsListe = this.listeMots();
            for (String mots : motsListe) {
                System.out.println(mots);
            }
        }

        if (showStats) {
            System.out.println("Profondeur moyenne : " + this.profondeurMoyenne());
            System.out.println("Hauteur : " + this.hauteur());
            System.out.println("Nombre de mots : " + this.comptageMots());
            System.out.println("Nombre de noeuds nuls : " + this.comptageNil());
        }

    }

    public static void main(String[] args) {

        TrieHybride trieHybride = new TrieHybride();

        // trieHybride.insertion("pomme");
        // trieHybride.insertion("poisson");
        // trieHybride.insertion("poil");
        // trieHybride.insertion("pollution");
        // trieHybride.insertion("porte");

        // trieHybride.equilibrer();

        // System.out.println(trieHybride.hauteur());
        // System.out.println(trieHybride.profondeurMoyenne());

        // List<String> motsListe = trieHybride.listeMots();
        // for (String mot : motsListe) {
        //     System.out.println(mot);
        // }

        trieHybride.insertFromFile("data/base.txt", false, true);

        List<String> motsListe = trieHybride.listeMots();
        for (String mot : motsListe) {
            System.out.println(mot);
        }
        

    }

}
