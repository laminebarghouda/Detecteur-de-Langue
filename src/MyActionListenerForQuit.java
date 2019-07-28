// Fermer l'interface graphique suite à l'appui sur le bouton "Quitter"
import java.awt.event.*;

public class MyActionListenerForQuit implements ActionListener {
    MyFrame f;

    public MyActionListenerForQuit(MyFrame f) {
        this.f = f;
    }

    public void actionPerformed(ActionEvent e) {
        // La fermeture d'un sous-fenêtre ne touchera qu'à elle même
      f.setVisible(false);
    }
}