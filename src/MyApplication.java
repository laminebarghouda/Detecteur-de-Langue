import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

// Class de Test qui déclenche l'éxecution de l'application et par suite l'interface graphique
public class MyApplication{


    public static void main(String[] args){

        // Créer et afficher une fenetre de l'interface graphique de l'application
        MyFrame f = new MyFrame();

        // La fermeture du fenêtre initiale de l'application entrainera la fermeture de tous les autres sous-fenêtres ouverts à partir d'elle
        f.mQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}

