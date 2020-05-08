import java.lang.Math;
import java.awt.Color;

public class Main{

    public static void main(String[] args){

        Equipe a = new Equipe();
        Equipe b = new Equipe();

        Joueur a1 = new Joueur("Joueur A");
        Joueur b1 = new Joueur("Joueur B");
        Joueur a2 = new Joueur("Joueur C");
        Joueur b2 = new Joueur("Joueur D");

        a.addJoueur(a1);
        b.addJoueur(b1);
        a.addJoueur(a2);
        b.addJoueur(b2);

        Equipe[] listeEquipes = new Equipe[]{a,b};

        Plateau p = new Plateau(listeEquipes);
        p.lancerPartie();
    }

}
