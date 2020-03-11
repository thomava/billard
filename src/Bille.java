import java.lang.Math;

public class Bille extends Disque{

    private double masse;
    private Vecteur vitesse;
    private Vecteur acceleration;

    private Equipe equipe;


    public Bille(Equipe _equipe){

        equipe = _equipe;
        couleur = equipe.getColor();

    }

    public double getMasse(){
        return masse;
    }

    public void setVitesse(Vecteur _vitesse){
        vitesse = _vitesse;
    }

    public Vecteur getVitesse(){
        return vitesse;
    }

    public Contact recoitContact(Bille b){
        if (b.position.scaredDist(position) > Math.pow(b.rayon + rayon, 2))
            return null; // Il n'y a pas de contact

        double angleContact = Math.atan2(b.position.y - position.y,
                                         b.position.x - position.x);

        Vecteur normale = new Vecteur(Math.cos(angleContact), Math.sin(angleContact));
        return new Contact(this, b, normale);
    }

    public Contact enContact(Element elem){
        return elem.recoitContact(this);

    }

}
