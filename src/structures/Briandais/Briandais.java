package structures.Briandais;

public class Briandais {

    private Noeud racine;

    public Briandais() {
        this.racine = new Noeud('\0');
    }

    // Insérer un mot dans l'arbre
    public void inserer(String mot) {
        inserer(this.racine, mot);
    }

    private void inserer(Noeud noeud, String mot) {
        if (mot.isEmpty()) return;
    
        
    
        Noeud courant = noeud.fils;
        Noeud precedent = null;
    
        // Trouver une position 
        while (courant != null && courant.caractere < premier(mot)) {
            precedent = courant;
            courant = courant.frere;
        }
    
        // Si le caractère n'existe pas encore à cet endroit, on crée un nouveau noeud
        if (courant == null || courant.caractere > premier(mot)) {
            Noeud nouveauNoeud = new Noeud(premier(mot));
            if (precedent == null) {
                // Insertion au début de la liste de frères
                nouveauNoeud.frere = noeud.fils;
                noeud.fils = nouveauNoeud;
            } else {
                // Insertion au milieu ou à la fin de la liste de frères
                nouveauNoeud.frere = precedent.frere;
                precedent.frere = nouveauNoeud;
            }
            courant = nouveauNoeud;
        }
    
        if (!reste(mot).isEmpty()) {
            inserer(courant, reste(mot));
        }
    }
    
   
    public boolean rechercher(String mot) {
        return rechercher(this.racine, mot);
    }

    private boolean rechercher(Noeud noeud, String mot) {
        if (mot.isEmpty()) return true; 
        

        Noeud enfantActuel = noeud.fils;
        

        while (enfantActuel != null) {
            if (enfantActuel.caractere == premier(mot)) {
                return rechercher(enfantActuel, reste(mot));
            }
            enfantActuel = enfantActuel.frere;
        }

        return false; 
    }

    public void afficherArbre() {
        afficherArbre(this.racine, "");
    }

    private void afficherArbre(Noeud noeud, String indent) {
        if (noeud == null) {
            return;
        }
    
        System.out.println(indent + noeud.caractere + " . ");
    
        if (noeud.fils != null) {
            afficherArbre(noeud.fils, indent );
        }
    
        if (noeud.frere != null) {
            System.out.println(indent + " |________ ");
            afficherArbre(noeud.frere, indent);
        }

        
    }

    public char premier(String s){
        return s.charAt(0);
    }

    public String reste(String s){
        return s.substring(1);
    }

    
    



    public class Main {
        public static void main(String[] args) {
            Briandais trie = new Briandais();
            //trie.inserer("test2");
            //trie.inserer("abc");
            //trie.inserer("test1");
    
            
    
            /*System.out.println(trie.rechercher("test1"));   
            System.out.println(trie.rechercher("test123"));  
            System.out.println(trie.rechercher("test"));  
            System.out.println(trie.rechercher("test2")); */

            String exemple_de_base = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
            
            // Séparer le texte en mots et les insérer dans l'arbre
            String[] mots = exemple_de_base.split("\\s+");
            for (String mot : mots) {
                trie.inserer(mot);
            }
    
            trie.afficherArbre();
        }
    }

}



