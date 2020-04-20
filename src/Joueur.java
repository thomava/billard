public class Joueur{

    protected String nom;// Nom du joueur
    protected Equipe equipe;

// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    public Joueur(String nom){
		this.nom = nom;
    }

    public void setEquipe(Equipe e){
        equipe = e;
    }
}
