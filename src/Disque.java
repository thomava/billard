import java.awt.Graphics;
import java.awt.Color;

public abstract class Disque extends Element{

    protected Color couleur;
    protected Vecteur position;
    protected double rayon;

    public void drawElement(Graphics g){
        g.setColor(couleur);
        g.fillOval((int)(position.x - rayon),
                   (int)(position.y - rayon),
                   (int)(position.x + rayon),
                   (int)(position.y + rayon));
    }

    public double getRayon(){
        return rayon;
    }

}
