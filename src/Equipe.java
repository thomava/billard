import java.util.ArrayList;
public class Equipe{

    private int joueurActuel;
    private ArrayList<Joueur> listeJoueurs;

// CONSTRUCTEURS ----------------------------------------------------

    public Equipe(){
        listeJoueurs = new ArrayList<Joueur>();
    }

    public void addJoueur(Joueur j){
        listeJoueurs.add(j);
        j.setEquipe(this);
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
