import java.awt.Graphics;
import java.awt.Color;

public class Bille extends Element{

    private double rayon;
    private double masse; // Masse de la bille en grammes
    protected Vecteur vitesse; // Vecteur qui représente la vitesse en mètres par seconde
    protected Vecteur acceleration; // Vecteur qui représente la vitesse en mètres par seconde
    protected BilleCouleur couleur; // indique la couleur que prend la boule. Se déduit de la couleur de l'équipe.

    protected boolean estTombe; // indique si la boule est tombée dans un trou
    protected Joueur joueur; // le joueur qui a fait tomber cette boule, si elle n'est pas tombée, variable nulle


// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    /**
    * fonction qui va permettre de construire notre objet en définissant son rayon et sa taille
    */
    public Bille(Vecteur position, BilleCouleur _couleur, double rayon, double masse){
		this.position = position;
        this.rayon = rayon;
		this.masse = masse;
        this.couleur = _couleur;

		// la vitesse et l'accélération sont nuls quand on créé les Billes, elles seront modifiées en jeu
		this.vitesse = new Vecteur(0,0);
		this.acceleration = new Vecteur(0,0);

		// tant que la bille n'est pas tombée, ces attributs ne sont pas importants
		this.estTombe = false;
		this.joueur = null;
    }

    /**
    * fonction qui va permettre de construire notre objet avec une masse et un rayon pédéfini
  	* qui sont amenés à ne pas varier d'un objet à un autre
    */
    public Bille(Vecteur position, BilleCouleur couleur){
        this(position, couleur, 10.0, 200.0);
    }

// GET et SET -----------------------------------------------------------------------------------------------------

    public void setPosition(Vecteur position){
		this.position=position;
	}

	public Vecteur getPosition(){
		return this.position;
	}

	public void setVitesse(Vecteur vitesse){
		this.vitesse=vitesse;
	}

	public Vecteur getVitesse(){
		return this.vitesse;
	}

    public double getRayon(){
        return this.rayon;
    }

    public double getMasse(){
        return this.masse;
    }

    public BilleCouleur getBilleCouleur(){
        return this.couleur;
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

    /**
    * fonction qui va permettre d'afficher les informations liées à la bille
    * @return chaine de carractères contenant les informations sur la bille.
    */
    public String toString(){
		String res = super.toString() + "billle de "+ couleur + "; de masse " + masse;
		if (estTombe == true){
			res += "; " + joueur+ " à fait tomber la bille.";
		}
		else{
			res+= "position : "+ position +" | vitesse : " + vitesse + " | accélération : " + acceleration + "." ;
		}
		return res;
    }

    // FONCTIONS De Test  ---------------------------------------------------

    /**
    * Méthode qui va permettre d'arréter une bille lorsque cette dernière à une vitesse trop faible et
    * qui va retourner true si on immobilise la bille. Elle signale par un booléen qu'elle vient d'arréter la bille.
    * @return true si la bille est en mouvement, false sinon.
    */
    public boolean estEnMouvement(){
      // la vitesseStatique correspond à la vitesse à partir de laquelle on considère que la bille est immobile
      double vitesseStatique = 2.0;
      // si la bille va plus lentement que cette vitesse, on l'arête manuellement
      if(this.vitesse.norme()< vitesseStatique){
        this.vitesse = new Vecteur(0,0);
        this.acceleration = new Vecteur(0,0);
        //quand on arrête la bille, on retourne vrai
        return false;
      }
      else{
        return true;
      }
    }

    // FONCTIONS De gestion Automatique de la Vitesse  ----------------------

    /**
    * Méthode qui diminue l'accéleration d'une bille selon sa vitesse et la visquosité que l'on définit
    */
    public void actualiser(double dT){

  		//on regarde la nouvelle position de la bille
  		position.x += vitesse.x*dT;
  		position.y += vitesse.y*dT;

        //On diminue l'acceleration pour le prochain déplacement
        double visq = 2;
        double k = 6*Math.PI*rayon*visq;
        acceleration.x=-k*vitesse.x/masse;
        acceleration.y=-k*vitesse.y/masse;

        //on calcule la nouvelle vitesse de la Bille
  		vitesse.x += dT*acceleration.x;
  		vitesse.y += dT*acceleration.y;
    }

    // FONCTIONS De Gestion des Contacts  -----------------------------------

    /**
     * Méthode appelée par une paire de billes qui va générer un contact entre ces deux billes
     * si elles sont en contact ou qui ne va rien renvoyer si elles ne se touchent pas.
     * @param Bille2 - la deuxième bille avec laquelle on teste le contact de la première bille.
     * @return Contact si il y a un contact entre ces deux billes, rien sinon.
     */
    public Contact recoitContact(Bille Bille2){
      Vecteur distance = new Vecteur(Bille2.position.x-this.position.x, Bille2.position.y-this.position.y);
      //On compare la distance entre les centre des deux billes à la longueur de deux rayons pour voir si les deux billes sont en contact
      if(distance.norme() <= 2*rayon){
        return new Contact(this, Bille2, distance.normaliser(), 2*rayon-distance.norme());
      }
      return null;
    }

     /**
     * Méthode qui met à jour le statut de la bille quand elle est tombée.
     */
    public void tomber(){
      System.out.println("bille tombée");
      this.estTombe = true;
    }

// AFFICHAGE ----------------------------------------------------------------------------------------------------

  /**
  * Méthode qui génère l'affichage d'une bille
  */
  public void peindreElement( Graphics g ){
      g.setColor(this.getCouleur());
      int xPosition = (int)(position.x - rayon);
      int yPosition = (int)(position.y - rayon);
      g.fillOval(xPosition, yPosition, 2*(int)rayon, 2*(int)rayon);
  }

  protected Color getCouleur(){
     return this.getBilleCouleur().getCouleur();
  }

}
