public class Joueur{

    protected String nom;// Nom du joueur
    protected Equipe equipe;


    
// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    public Joueur(String nom, Equipe equipe){
	/* fonction qui va permettre de construire notre objet en définissant son rayon et sa taille */
	
		// on construit un disque défini par son rayon et son centre
		this.nom = nom;
		this.equipe = equipe;
    }
}
