// Selectionner et lire un fichier texte graçe à un FileDialog suite à l'appuie sur le bouton "Nouveau Fichier"
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MyActionListenerForOpen implements ActionListener{
    MyFrame f;

     // Constructeur
    public MyActionListenerForOpen(MyFrame f ){
        this.f=f;
    }

    public void actionPerformed(ActionEvent e){
        FileDialog fd = new FileDialog(f);
        fd.setVisible(true);
        String nomFichier = fd.getFile(); // nom du Fichier à traiter
        String repFichier = fd.getDirectory(); // Repertoire du fichier
        String nomComplet = repFichier+nomFichier; // PATH complet du fichier
        File file = new File (nomComplet);
        // Lire le texte caractère par caractère depuis le fichier
        try {
            f.monTexte = new Console(file);
            int size;
            size = (int) file.length();

            FileInputStream in = new FileInputStream(file);
            // Tableau de byte qui va contenir le texte devisé en caractère
            byte data[] = new byte[size];
            in.read(data);
            // Stocker le texte dans un String
            String s=new String (data);
            // Afficher le texte dans le TextArea
            f.texte.setText(s);
        }
        catch (FileNotFoundException e2){
            f.resultat.setText("Impossible de trouver le fichier avec le chemin :  " + nomFichier + "\n" + "Assurez vous que le fichier n'est pas modifié et existant puis réessayez");
        }
        catch (IOException e3){
            f.resultat.setText("Erreur lors de la lecture du fichier !\n" + "Assurer vous bien que le fichier est de type texte(.txt)");
        }

    }
}