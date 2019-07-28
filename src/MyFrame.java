// Mise en place de composants de l'interface graphique de l'application
import java.awt.*;
import javax.swing.*;


class MyFrame extends JFrame{

    JMenuBar mb = new JMenuBar(); // Menu
    JMenuItem mNew = new JMenuItem("Nouvelle Fenêtre"); // Element du menu
    JMenuItem mOpen = new JMenuItem("Ouvrir Fichier");  // Element du menu
    JMenuItem mQuit = new JMenuItem("Quitter");  // Element du menu
    JPanel pSouth =new JPanel();
    JLabel texteLabel = new JLabel("Texte");
    TextArea texte = new TextArea(""); // Zone du texte pour afficher le contenu du texte à traiter
    JButton bResultat = new JButton("Résultat de Détection"); // Bouton pour afficher le résultat
    JTextArea resultat = new JTextArea(""); // Zone du texte pour afficher le résultat de detection de la langue du texte
    Console monTexte; // Texte à traiter
    MyActionListenerForNew x1 =new MyActionListenerForNew(this); // Action executé en cliquant sur "Nouvelle Fenêtre"
    MyActionListenerForOpen x2 =new MyActionListenerForOpen(this); // Action executé en cliquant sur "Ouvrir Fichier"
    MyActionListenerForQuit x3 =new MyActionListenerForQuit(this); // Action executé en cliquant sur "Quitter"
    MyActionListenerForResult x4 = new MyActionListenerForResult(this); // Action executé en cliquant sur "Résultat de Détection"

    public MyFrame(){
        this.setBackground(Color.blue); // Couleur de l'arriére plan du Frame
        this.add(pSouth);
        this.setJMenuBar(mb);
        this.setBounds(50,50,520,550); // Dimensions du fenêtre du Frame
        this.setVisible(true); // Frame visible
        this.setTitle("Détecteur de Langues"); // Titre du fenêtre
        this.setDefaultCloseOperation(HIDE_ON_CLOSE); // Action par défault en cliquant sur l'icône de fermeture "X"
        this.setResizable(false); // Pour Garder la taille fixé dans le code du Frame
        // Ajout des boutons au menu
        mb.add(mNew);
        mb.add(mOpen);
        mb.add(mQuit);


        pSouth.setBackground(Color.yellow); // Couleur de  l'arriére plan du Panel
        pSouth.setBounds(0,0,520,320); // Dimensions du Panel
        pSouth.setLayout(null);
        // dimensionnement et ajout des composants au Panel qui representera notre interface graphqiue
        texteLabel.setBounds(240,5,50,20);
        pSouth.add(texteLabel);
        texte.setBounds(10,30,480,380);
        pSouth.add(texte);
        bResultat.setBounds(190,415,160,20);
        pSouth.add(bResultat);
        resultat.setBounds(10,440,480,40);
        pSouth.add(resultat);
        // Ajout des actions à chaque élement et bouton
        mNew.addActionListener(x1);
        mOpen.addActionListener(x2);
        mQuit.addActionListener(x3);
        bResultat.addActionListener(x4);


    }


}