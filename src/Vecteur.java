import java.lang.Math;

public class Vecteur{

    public double x;
    public double y;

    public Vecteur(double _x, double _y){
        x = _x;
        y = _y;
    }

    public double scaredDist(Vecteur vec){
        return (vec.x - x) * (vec.x - x) + (vec.y - y) * (vec.y - y);
    }

    public double norme(){
        return Math.sqrt(x*x+y*y);
    }

    public double produitScalaire(Vecteur vec){
        return vec.x * x + vec.y * y;
    }

    public Vecteur vecteurNormal(){
        return new Vecteur(-y, x);
    }

    public Vecteur add(Vecteur b){
        return new Vecteur(x + b.x, y + b.y);
    }

    public Vecteur mul(double scal){
        return new Vecteur(x*scal, y*scal);
    }
    public Vecteur MetreVersPixels(){
      int a = 20;
      return new Vecteur(x*a, y*a);
    }

}
