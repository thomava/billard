import java.lang.Math;
import java.awt.Color;

public class Main{

    public static void main(String[] args){

        Equipe a = new Equipe();
        Equipe b = new Equipe();

        Joueur a1 = new Joueur("a1");
        Joueur b1 = new Joueur("b1");

        a.addJoueur(a1);
        b.addJoueur(b1);

        Equipe[] listeEquipes = new Equipe[]{a,b};

        Plateau p = new Plateau(listeEquipes);
        p.lancerPartie();
    }

}
