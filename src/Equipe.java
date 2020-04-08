import java.awt.Color;
public class Equipe{

    private Color col;

    private int joueurActuel;
    private Joueur[] listeJoueurs;

// CONSTRUCTEURS ----------------------------------------------------

    public Equipe(Color _col){
        col = _col;
    }

    public Color getColor(){
        return col;
    }

    /**
     * Détermine et retourne le prochain joueur de cette équipe.
     * @return le joueur suivant de l'équipe.
     */
    public Joueur prochainJoueur(){ 
        joueurActuel++;
        if (joueurActuel >= listeJoueurs.length)
            joueurActuel = 0;

        return listeJoueurs[joueurActuel];
    }

}
