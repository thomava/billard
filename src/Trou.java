import java.awt.Color;

public class Trou extends Element{

  protected Vecteur position;
  private double rayon;

  // CONSTRUCTEURS -----------------------------------------------------------------------------------------------------
  public Trou(Vecteur position){
      this.position = position
      this.rayon = 10.0;
  }

  // FONCTIONS  -----------------------------------------------------------------------------------------------------
  public Contact recoitContact(Bille Bille){
    /* fonction qui dit si la bille est en contact avec une autre bille : si c'est une autre bille,
    elle répond true, si la bille rencontre un trou, on fait tomber la bille et on retourne false */
    Vecteur Distance = new Vecteur(Maths.abs(Bille2.centre.x-this.centre.x), Maths.abs(Bille2.centre.y-this.centre.y));
    //On compare la distance entre les centre des deux billes à la longueur de deux rayons pour voir si les deux billes sont en Contact
    if(Distance.norme() <= super.rayon){
      return new Contact(Bille2, this, Distance.normaliser(), 2*super.rayon-Distance.norme());
    }
    return null;
  }

// AFFICHAGE -----------------------------------------------------------------------------------------------------
  public void peindreElement( Graphics g ){
    int xPosition = (int)(position.MetreVersPixels().x-super.rayon);
    int yPosition = (int)(position.MetreVersPixels().y-super.rayon);
    g.fillOval(xPosition, yPosition, (int)super.rayon, (int)super.rayon),Color.yellow());
  }

}
