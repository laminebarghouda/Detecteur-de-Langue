/* Pour stocker les mots extraits depuis le texte et leurs nombre d’occurrences dans un objet pour faciliter leurs manipulations
et les calculs effectués pour estimer si deux textes sont écrits dans la même langue ou non. */

public class Mot {
    String contenu; // Le mot extrait du texte
    int occurence; // Nombre d'occurence du mot dans un texte donnée

    public Mot(String contenu,int occurence){
        this.contenu=contenu;
        this.occurence=occurence;
    }

}
