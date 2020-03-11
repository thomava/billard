
public class Bille extends Disque{
    
    private double masse; // Masse de la bille en grammes
    protected Vecteur vitesse; // Vecteur qui représente la vitesse en mètres par seconde
    protected Vecteur acceleration; // Vecteur qui représente la vitesse en mètres par seconde
    private Equipe couleur; // indique a qui appartient la boule
      
    protected boolean estTombe; // indique si la boule est tombée dans un trou
    protected Joueur joueur; // le joueur qui a fait tomber cette boule, si elle n'est pas tombée, variable nulle

    
// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    public Bille(Vecteur centre, Equipe couleur, int rayon, double masse){
	/* fonction qui va permettre de construire notre objet en définissant son rayon et sa taille */
	
		// la Bille est un Disque, on construit le disque auquel on ajoute une masse et une appartenance
		super(centre, rayon); 
		this.masse = masse;
		this.couleur = couleur;
		
		// la vitesse et l'accélération sont nuls quand on créé les Billes, elles seront modifiées en jeu		
		this.vitesse = new Vecteur(0,0);
		this.acceleration = new Vecteur(0,0);
		
		// tant que la bille n'est pas tombée, ces attributs ne sont pas importants
		this.estTombe = false;
		this.joueur = null;
    }
    
    public Bille(Vecteur centre, Equipe couleur){
	/* fonction qui va permettre de construire notre objet avec une masse et un rayon pédéfini 
	qui sont amenés à ne pas varier d'un objet à un autre */
		// la Bille est un Disque, on construit le disque
		super(centre, 10.0); // on crée un bille de rayon de 10 cm
		
		double M = 200; // masse de 200 g 
		// on ajoute une masse et une appartenance
		this.masse = M;
		this.couleur = couleur;
		
		// la vitesse et l'accélération sont nuls quand on créé les Billes, elles seront modifiées en jeu
		this.vitesse = new Vecteur(0,0);
		this.acceleration = new Vecteur(0,0);
		
		// tant que la bille n'est pas tombée, ces attributs ne sont pas importants
		this.estTombe = false;
		this.joueur = null;
    }
 
// GET et SET -----------------------------------------------------------------------------------------------------
	public void setVitesse(Vecteur vitesse){
		this.vitesse=vitesse;
	}
	
	public Vecteur getVitesse(){
		return this.vitesse;
	}
	
	public void setAcceleration(Vecteur acceleration){
		this.acceleration=acceleration;
	}
	
	public Vecteur getAcceleration(){
		return this.acceleration;
	}
// FONCTIONS -----------------------------------------------------------------------------------------------------   
    public String toString(){
		String res = super.toString() + "billle de "+ couleur + "; de masse " + masse;
		if (estTombe==true){
			res += "; " + joueur+ " à fait tomber la bille.";
		}
		else{
			res+= " ayant une vitesse de " + vitesse + " et une accélération de " + acceleration + "." ;
		}
		return res;
    }

    public void actualiser(int dT){
		
		//on calcule la nouvelle vitesse de la Bille
		vitesse.x += dT*acceleration.x;
		vitesse.y += dT*acceleration.y;
		
		//on regarde la nouvelle position de la bille
		super.centre.x += vitesse.x*dT;
		super.centre.y += vitesse.y*dT;		
		
		diminueAcc();
		
    }
    
    public void diminueAcc(){
		double visq =1.85*Math.pow(10,-8); // avec la viquosité dynamique en grammes par metre par secondes 
		double k = 6*Math.PI*super.rayon*visq; 
		acceleration.x=-k*vitesse.x/masse;
		acceleration.y=-k*vitesse.y/masse;
    } 
}
