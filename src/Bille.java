import java.awt.Graphics;
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
    /* fonction qui va permettre d'afficher les informations liées à la bille */
		String res = super.toString() + "billle de "+ couleur + "; de masse " + masse;
		if (estTombe==true){
			res += "; " + joueur+ " à fait tomber la bille.";
		}
		else{
			res+= " ayant une vitesse de " + vitesse + " et une accélération de " + acceleration + "." ;
		}
		return res;
    }

    public void actualiser(double dT){
    /* fonction qui va permettre d'afficher les informations liées à la bille */
		//on calcule la nouvelle vitesse de la Bille
		vitesse.x += dT*acceleration.x;
		vitesse.y += dT*acceleration.y;

		//on regarde la nouvelle position de la bille
		super.position.x += vitesse.x*dT;
		super.position.y += vitesse.y*dT;

    //On diminue l'acceleration pour le prochain déplacement
    DiminuerAcceleration();
    }

		public boolean EstEnContact(Element objet, Joueur joueurActif){
      /* fonction qui dit si la bille est en contact avec quelque chose : si c'est une autre bille,
      elle répond true, si la bille rencontre un trou, on fait tomber la bille et on retourne false */
      Vecteur Distance = new Vecteur(Math.abs(objet.position.x-this.position.x), Math.abs(objet.position.y-this.position.y));
      //On compare la distance entre les centre des deux billes à la longueur de deux rayons pour voir si les deux billes sont en Contact
      if(objet instanceof Bille){
        if(Distance.norme() <= 2*super.rayon){
          return true;
        }
        else{
          return false;
        }
      }
      if(objet instanceof Trou){
        if(Distance.norme() <= super.rayon){
          return true;
          Tomber(joueurActif);
        }
        else{
          return false;
        }
      }
    }

    public Contact testContact(Element e){
        return e.recoitContact(this);
    } 

    public Contact recoitContact(Bille b){
        
    }

    public Contact Tomber(Joueur joueurActif){
    /* fonction qui met a jour le statut de la bille : on fait tomber la bille */
      this.estTombe = true;
      this.joueur = joueurActif;
    }

    public void DiminuerAcceleration(){
  		double visq =1.85*Math.pow(10,-8); // avec la viquosité dynamique en grammes par metre par secondes
  		double k = 6*Math.PI*super.rayon*visq;
  		acceleration.x=-k*vitesse.x/masse;
  		acceleration.y=-k*vitesse.y/masse;
    }

    public void ProdiguerAcceleration(){

    }

    public boolean estEnMouvement(){
        return false;
    }

// AFFICHAGE -----------------------------------------------------------------------------------------------------
  @Override
  public void peindreElement( Graphics g ){
    int xPosition = (int)(position.MetreVersPixels().x-super.rayon);
    int yPosition = (int)(position.MetreVersPixels().y-super.rayon);
    g.fillOval(xPosition, yPosition, (int)super.rayon, (int)super.rayon);
  }
}
