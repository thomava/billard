public abstract class Disque extends Element{
    
    protected double rayon;// Rayon de la bille en centimètres


    
// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    public Disque(Vecteur centre, double rayon){
	/* fonction qui va permettre de construire notre objet en définissant son rayon et sa taille */
	
		// on construit un disque défini par son rayon et son centre
		this.position = centre;
		this.rayon = rayon;
    }
}
