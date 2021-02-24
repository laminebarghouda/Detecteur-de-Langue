// Texte à étudier divisé en mots et algorithme de detection de la langue d'ecriture et ses méthodes nécessaires
import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;

public class Console {
    ArrayList<Mot> mots = new ArrayList<Mot>(); // Stocker les mots du texte avec l'occurence de chacune
    String langue = ""; // langue du texte
    String erreur = ""; // erreur conclus suite à l'application de l'algorithme de reconnaissance

    public Console(File f) throws IOException {
        this.mots = compterOccurences(f);
        chercherLangue();
    }

    // Diviser le texte en mots et compter le nombre d'occurence de chaque mot
    public ArrayList<Mot> compterOccurences(File f) throws IOException {
        ArrayList<Mot> mots = new ArrayList<Mot>();
        Hashtable table = new Hashtable(); // tableau auxiliaire dans ses keys sont les mots extraits du texte
        BufferedReader entree = new BufferedReader(new FileReader(f));
        String ligne; // Pour stocker une ligne du texte
        StringTokenizer st; // Permet de décomposer une chaîne de caractères en une suite de "mots" séparés par des "délimiteurs".
        String mot;
        int nbOcc; // Nombre d'occurence d'une mot

        while ((ligne = entree.readLine()) != null) {
            st = new StringTokenizer(ligne, " ,.;:_-+*/\\.;\n\"'{}()=><\t!?");
            while (st.hasMoreTokens()) {
                mot = st.nextToken().toLowerCase(); // mot extrait d'une line
                if (table.containsKey(mot)) { // Si le mot existe dans table on incrémente son nombre d'occurence
                    nbOcc = ((Integer) table.get(mot)).intValue();
                    nbOcc++;
                } else nbOcc = 1;
                table.put(mot, new Integer(nbOcc)); // Si le mot n'existe pas dans table, on l'ajoute avec un nombre d'occurence initialisé à 1
            }
        }
        Enumeration lesMots = table.keys();
        // Extraire les mots de table et créer des objets "Mot" avec le mot et son nombre d'occurences et les stockers tous dans l'ArrayList
        while (lesMots.hasMoreElements()) {
            mot = (String) lesMots.nextElement();
            nbOcc = ((Integer) table.get(mot)).intValue();
            mots.add(new Mot(mot, nbOcc));
        }
        return mots;
    }

    // Calculer le produit scalaire entre deux textes chaucn par l'ArrayList de ses mots et leurs occurences
    public int calculPS(ArrayList<Mot> a1, ArrayList<Mot> a2) {
        int ps = 0;
        for (int i = 0; i < a1.size(); i++) {
            for (int j = 0; j < a2.size(); j++) {
                if (a1.get(i).contenu.equals(a2.get(j).contenu))
                    ps += a1.get(i).occurence * a2.get(j).occurence;
            }
        }
        return ps;
    }

    // Appliquer l'algorithme pour reconnaître la langue d’un texte écrit
    public void chercherLangue() {
        ArrayList<Mot> texte = this.mots;
        ArrayList<Mot> francais = new ArrayList<>();
        ArrayList<Mot> anglais = new ArrayList<>();
        ArrayList<Mot> portugais = new ArrayList<>();
        ArrayList<Mot> allemand = new ArrayList<>();
        ArrayList<Mot> suedois = new ArrayList<>();
        try {
            // Preparer les textes de comparaison
            try {
                // Les mots du texte de réference avec leurs nombre d'occurences dans chacune des ArrayList
                francais = compterOccurences(new File(getClass().getResource("/LanguesDeTest/fr.txt").toURI()));
                anglais = compterOccurences(new File(getClass().getResource("/LanguesDeTest/en.txt").toURI()));
                portugais = compterOccurences(new File(getClass().getResource("/LanguesDeTest/pt.txt").toURI()));
                allemand = compterOccurences(new File(getClass().getResource("/LanguesDeTest/de.txt").toURI()));
                suedois = compterOccurences(new File(getClass().getResource("/LanguesDeTest/sv.txt").toURI()));
            } catch (URISyntaxException e2) {
                this.erreur="Impossible de trouver les fichiers de Test\n" + "Assurez vous que ces fichiers ne sont pas modifié et existant sous src/LanguesDeTest puis réessayez";
            }
            catch (NullPointerException e2) {
                this.erreur="Impossible de trouver les fichiers de Test\n" + "Assurez vous que ces fichiers ne sont pas modifié et existant sous src/LanguesDeTest puis réessayez";
            }
            //Calculer le score accumulé du texte à étudier par rapport à chaque texte de réference de la langue donnée
            Vector<Double> scores = new Vector<Double>();
            scores.add(new Double((float) calculPS(texte, francais) / (texte.size() * francais.size())));
            scores.add(new Double((float) calculPS(texte, anglais) / (texte.size() * anglais.size())));
            scores.add(new Double((float) calculPS(texte, portugais) / (texte.size() * portugais.size())));
            scores.add(new Double((float) calculPS(texte, allemand) / (texte.size() * allemand.size())));
            scores.add(new Double((float) calculPS(texte, suedois) / (texte.size() * suedois.size())));
            // Faire la correspondance du texte à étudier aux langues connus
            Double lng = Collections.max(scores);
            // Le seuil d'approxiamtion pour les langues francais, anglais et portugais est mis à 0.017 après plusieurs expérience pour plus de précision
            if (lng > 0.017 && (scores.indexOf(lng) != 3)) {
                switch (scores.indexOf(lng)) {
                    case 0:
                        this.langue = "Français";
                        break;
                    case 1:
                        this.langue = "Anglais";
                        break;
                    case 2:
                        this.langue = "Portugais";
                        break;
                    case 4:
                        this.langue = "Suedois";
                        break;
                }
                // Le seuil d'approxiamtion pour la langue allemand et suedois est maintenu à 0.045
            } else if (lng > 0.0045 && (scores.indexOf(lng) == 3)) {
                this.langue = "Allemand";
            }
            //  si la langue du texte n'est pas trouvé et aucune formule n'est vérifiée
            else {
                this.langue = "Inconnue";
            }
            // Si le texte vérifie la formule pour plusieurs langues différentes
            Collections.sort(scores);
            if ((scores.get(2) > 0.017) && (scores.get(3) > 0.017))
                this.langue = "Erreur";
        } catch (IOException e) {
            erreur = "Erreur lors de la lecture des fichiers de Test !\n" + "Assurer vous bien que ces fichiers ne sont pas modifiés et de type texte(.txt)";
        }
    }


}