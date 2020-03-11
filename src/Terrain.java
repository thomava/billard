import java.awt.Graphics;
import java.awt.Color;

public class Terrain extends Element{

    private Vecteur taille;

    public Terrain(){

    }
    
    public void drawElement(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(0,
                   0,
                   (int)taille.x,
                   (int)taille.y);
    }

    public Contact recoitContact(Bille b){
        if (b.position.y - b.rayon < 0)
            return new Contact(b, this, new Vecteur(0, 1));
        else if (b.position.y + b.rayon > taille.y)
            return new Contact(b, this, new Vecteur(0, 1));
        else if (b.position.x - b.rayon < 0)
            return new Contact(b, this, new Vecteur(1, 0));
        else if (b.position.x + b.rayon > taille.x)
            return new Contact(b, this, new Vecteur(1, 0));
        return null;
    }

}
