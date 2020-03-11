import java.awt.Color;

public class Trou extends Disque{

    public Trou(Vecteur _position){
        position = _position;
        rayon = 10;
        couleur = Color.black;
    }

    public Contact recoitContact(Bille b){
        return null;
    }


}
