// Afficher le résultat de detection de langue et les erreurs suite à l'appui sur le bouton "Résultat de Detection"
import java.awt.event.*;

public class MyActionListenerForResult implements ActionListener {
    MyFrame f;

    public MyActionListenerForResult(MyFrame f) {
        this.f = f;
    }

    public void actionPerformed(ActionEvent e) {
        // Si un erreur ou une exception est lévee lors du detection
        if(!f.monTexte.erreur.equals(""))
            f.resultat.setText(f.monTexte.erreur);
        else {
            //  si la langue du texte n'est pas trouvé
            if (f.monTexte.langue.equals("Inconnue")) {
                f.resultat.setText("Aucune correspondance établi ! La langue de ce texte est inconnue\n" + "N.B: le texte doit être suffisamment long pour appliquer l'algorithme de reconaissance");
            }
            
            // Si le texte vérifie la formule pour plusieurs langues différentes
            else if (f.monTexte.langue.equals("Erreur")) {
                f.resultat.setText("Impossible de determiner la langue de ce texte\n" + "Il vérifie la formule pour plusieurs langues différentes");
            } else {
                f.resultat.setText("Ce Texte est écrit en " + f.monTexte.langue);
            }
        }
    }
}