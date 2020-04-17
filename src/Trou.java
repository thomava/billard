import java.awt.Color;
import java.awt.Graphics;

public class Trou extends Element{

  protected Vecteur position;
  private double rayon;

  // CONSTRUCTEURS -----------------------------------------------------------------------------------------------------
  public Trou(Vecteur position){
      this.position = position;
      this.rayon = 10.0;
  }

  // FONCTIONS  -----------------------------------------------------------------------------------------------------
  public Contact recoitContact(Bille bille){

    /* fonction qui dit si la bille est en contact avec une autre bille : si c'est une autre bille,
    elle répond true, si la bille rencontre un trou, on fait tomber la bille et on retourne false */
    double dist = Math.sqrt(position.distanceCarrée(bille.position));

    //On compare la distance entre les centre des deux billes à la longueur de deux rayons pour voir si les deux billes sont en Contact
    if(dist <= rayon){
        //La profondeur de contact est directement mise à 0. En effet, elle n'a
        //pas d'utilité dans le cas d'un contact entre une bille et un trou.
        //De même pour le vecteur normal au contact.
      return new Contact(bille, this, null, 0);
    }
    return null;
  }

// AFFICHAGE -----------------------------------------------------------------------------------------------------
  public void peindreElement( Graphics g ){
    g.setColor(Color.black);
    int xPosition = (int)(position.MetreVersPixels().x-rayon);
    int yPosition = (int)(position.MetreVersPixels().y-rayon);
    g.fillOval(xPosition, yPosition, (int)rayon, (int)rayon);
  }

}
