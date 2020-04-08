import java.awt.Graphics;
public class Bille extends Element{

    protected Vecteur position;
    private double rayon;
    private double masse; // Masse de la bille en grammes
    protected Vecteur vitesse; // Vecteur qui représente la vitesse en mètres par seconde
    protected Vecteur acceleration; // Vecteur qui représente la vitesse en mètres par seconde
    private Equipe couleur; // indique a qui appartient la boule

    protected boolean estTombe; // indique si la boule est tombée dans un trou
    protected Joueur joueur; // le joueur qui a fait tomber cette boule, si elle n'est pas tombée, variable nulle


// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    public Bille(Vecteur position, Equipe couleur, double rayon, double masse){
	/* fonction qui va permettre de construire notre objet en définissant son rayon et sa taille */

		// la Bille est un Disque, on construit le disque auquel on ajoute une masse et une appartenance
		this.position = position;
    this.rayon = rayon;
		this.masse = masse;
		this.couleur = couleur;

		// la vitesse et l'accélération sont nuls quand on créé les Billes, elles seront modifiées en jeu
		this.vitesse = new Vecteur(0,0);
		this.acceleration = new Vecteur(0,0);

		// tant que la bille n'est pas tombée, ces attributs ne sont pas importants
		this.estTombe = false;
		this.joueur = null;
    }

    public Bille(Vecteur position, Equipe couleur){
	/* fonction qui va permettre de construire notre objet avec une masse et un rayon pédéfini
	qui sont amenés à ne pas varier d'un objet à un autre */

		// la Bille est un Disque, on construit le disque
    this.position = position;
    double rayon = 10.0;
    this.rayon = rayon; // on crée un bille de rayon de 10 cm

		double M = 200.0; // masse de 200 g
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

  public boolean getTombe(){
		return estTombe;
	}
  
// FONCTIONS  -----------------------------------------------------------------------------------------------------
    public String toString(){
    /* fonction qui va permettre d'afficher les informations liées à la bille */
		String res = super.toString() + "billle de "+ couleur + "; de masse " + masse;
		if (estTombe == true){
			res += "; " + joueur+ " à fait tomber la bille.";
		}
		else{
			res+= " ayant une vitesse de " + vitesse + " et une accélération de " + acceleration + "." ;
		}
		return res;
    }

    // FONCTIONS De Test  ---------------------------------------------------
    public boolean EstEnMouvement(){
      double vitesseStatique = 2.0;
      if(this.vitesse.norme()< vitesseStatique){
        this.vitesse = new Vecteur(0,0);
        this.acceleration = new Vecteur(0,0);
        return false;
      }
      else{
        return true;
      }
    }

    // FONCTIONS De gestion Automatique de la Vitesse  ----------------------

    public void actualiser(int dT){
    /* fonction qui va permettre d'afficher les informations liées à la bille */
		//on calcule la nouvelle vitesse de la Bille
		vitesse.x += dT*acceleration.x;
		vitesse.y += dT*acceleration.y;

		//on regarde la nouvelle position de la bille
		position.x += vitesse.x*dT;
		position.y += vitesse.y*dT;

    //On diminue l'acceleration pour le prochain déplacement
    DiminuerAcceleration();
    }

    private void DiminuerAcceleration(){
  		double visq =1.85*Math.pow(10,-8); // avec la viquosité dynamique en grammes par metre par secondes
  		double k = 6*Math.PI*rayon*visq;
  		acceleration.x=-k*vitesse.x/masse;
  		acceleration.y=-k*vitesse.y/masse;
    }

    // FONCTIONS De Gestion des Contacts  -----------------------------------
  
    public Contact recoitContact(Bille Bille2){
      /* fonction qui dit si la bille est en contact avec une autre bille : si c'est une autre bille,
      elle répond true, si la bille rencontre un trou, on fait tomber la bille et on retourne false */
      Vecteur Distance = new Vecteur(Math.abs(Bille2.position.x-this.position.x), Math.abs(Bille2.position.y-this.position.y));
      //On compare la distance entre les position des deux billes à la longueur de deux rayons pour voir si les deux billes sont en Contact
      if(Distance.norme() <= 2*rayon){
        return new Contact(Bille2, this, Distance.normaliser(), 2*rayon-Distance.norme());
      }
      return null;
    }
  
    public void Tomber(Joueur joueurActif){
    /* fonction qui met a jour le statut de la bille : on fait tomber la bille */
      this.estTombe = true;
      this.joueur = joueurActif;
    }

// AFFICHAGE ----------------------------------------------------------------------------------------------------
  
  public void peindreElement( Graphics g ){
    int xPosition = (int)(position.MetreVersPixels().x-rayon);
    int yPosition = (int)(position.MetreVersPixels().y-rayon);
    g.fillOval(xPosition, yPosition, (int)rayon, (int)rayon);
  }
}
