// Ouvrir une nouvelle fenêtre de l'interface graphique suite à l'appuie sur le bouton "Nouvelle Fenêtre"
import java.awt.event.*;

public class MyActionListenerForNew implements ActionListener {
    MyFrame f;

    // Constructeur
    public MyActionListenerForNew(MyFrame f) {
        this.f = f;
    }


    public void actionPerformed(ActionEvent e) {
        MyFrame f2 = new MyFrame();
        f2.setBounds(200,200,520,550);
    }
}