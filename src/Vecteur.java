import java.lang.Math;

public class Vecteur{

    public double x;
    public double y;

    public Vecteur(double _x, double _y){
        x = _x;
        y = _y;
    }

    /**
     * Calcule la distance au carré entre deux vecteurs.
     * Utilise la propriété de bijectivité de la racine carée pour permettre de
     * ne pas calculer la racine.
     * @param vec Vecteur avec lequel est calculé la distance.
     * @return distance au carré.
     */
    public double distanceCarrée(Vecteur vec){
        return (vec.x - x) * (vec.x - x) + (vec.y - y) * (vec.y - y);
    }

    /** 
     * Calcule la norme 2 du vecteur.
     * @return Norme 2.
     */
    public double norme(){
        return Math.sqrt(x*x+y*y);
    }

    /**
     * Calcule le vecteur normalisé de ce vecteur.
     * @return Même vecteur à la norme 1.
     */
    public Vecteur normaliser(){
        return mul(1/norme());
    }

    /**
     * Calcule le produit scalaire entre deux vecteurs.
     * @param vec Vecteur avec lequel est calculé le produit scalaire.
     * @return Produit scalaire : this . vec.
     */
    public double produitScalaire(Vecteur vec){
        return vec.x * x + vec.y * y;
    }

    /**
     * Genère un vecteur normal de même norme au vecteur de l'instance.
     * @return Vecteur normal. 
     */
    public Vecteur vecteurNormal(){
        return new Vecteur(-y, x);
    }

    /**
     * Genère un vecteur résultant de la somme de deux vecteurs.
     * @param vec Vecteur opérande de la somme.
     * @return Somme des deux vecteurs : this + vec.
     */
    public Vecteur add(Vecteur vec){
        return new Vecteur(x + vec.x, y + vec.y);
    }

    /**
     * Genère le vecteur résultant du produit par un scalaire.
     * @param scal Scalaire qui multiplie le vecteur.
     * @return Vecteur multiplié par scal : this * scal. 
     */
    public Vecteur mul(double scal){
        return new Vecteur(x*scal, y*scal);
    }

    /**
     * Génère un vecteur en pixels avec un vecteur en USI.
     * @return Vecteur en pixels. 
     */
    public Vecteur MetreVersPixels(){
      int a = 20;
      return mul(a);
    }

}
