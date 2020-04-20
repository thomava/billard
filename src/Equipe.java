import java.awt.Color;
import java.util.ArrayList;
public class Equipe{

    private Color col;

    private int joueurActuel;
    private ArrayList<Joueur> listeJoueurs;

// CONSTRUCTEURS ----------------------------------------------------

    public Equipe(Color _col){
        col = _col;
        listeJoueurs = new ArrayList<Joueur>();
    }

    public void addJoueur(Joueur j){
        listeJoueurs.add(j);
        j.setEquipe(this);
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
        if (joueurActuel >= listeJoueurs.size())
            joueurActuel = 0;

        return listeJoueurs.get(joueurActuel);
    }

}
