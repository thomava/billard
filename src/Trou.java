import java.awt.Color;
import java.awt.Graphics;

public class Trou extends Element{

  private double rayon;

  // CONSTRUCTEURS -----------------------------------------------------------------------------------------------------
  public Trou(Vecteur _position, double _rayon){
      super(_position);
      this.rayon = _rayon;
  }

  // FONCTIONS  -----------------------------------------------------------------------------------------------------
  public Contact recoitContact(Bille bille){

    /* fonction qui dit si la bille est en contact avec une autre bille : si c'est une autre bille,
    elle répond true, si la bille rencontre un trou, on fait tomber la bille et on retourne false */
    double dist = Math.sqrt(position.distanceCarrée(bille.position));

    //On compare la distance entre les centre des deux billes à la longueur de deux rayons pour voir si les deux billes sont en Contact
    if(dist <= rayon){
        //Le vecteur normal n'est pas calculé/spécifié. Il ne sert à rien dans
        //la cas d'un contact avec un Trou.
        return new Contact(bille, this, new Vecteur(0, 0), rayon - dist);
    }
    return null;
  }

// AFFICHAGE -----------------------------------------------------------------------------------------------------
public void peindreElement( Graphics g ){
    g.setColor(Color.black);
  //int xPosition = (int)(position.MetreVersPixels().x-rayon);
  //int yPosition = (int)(position.MetreVersPixels().y-rayon);
  int xPosition = (int)(super.position.x - rayon);
  int yPosition = (int)(super.position.y - rayon);
  g.fillOval(xPosition, yPosition, 2*(int)rayon, 2*(int)rayon);
}
}
