public class Disque{
    
    protected Vecteur centre; // Position du centre de la bille
    protected double rayon;// Rayon de la bille en centimètres


    
// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    public Disque(Vecteur centre, double rayon){
	/* fonction qui va permettre de construire notre objet en définissant son rayon et sa taille */
	
		// on construit un disque défini par son rayon et son centre
		this.centre = centre;
		this.rayon = rayon;
    }
}
