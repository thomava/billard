import java.awt.Graphics;
import java.awt.Color;

public class Terrain extends Element{

    /**
     * Taille du terrain en pixel (longueur, largeur).
     */
    private Vecteur taille;

    public Terrain(){}
    
    /**
     * Méthode appelée par paint() de FenetreJeu pour peindre les différents
     * élements qui composent le Plateau.
     * @param g Objet Graphics pour peindre.
     */
    @Override
    public void peindreElement(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(0,
                   0,
                   (int)taille.x,
                   (int)taille.y);
    }

    /**
     * Méthode appelé par une bille qui provoque un contact. Elle génère un
     * objet Contact qui décrit le présent contact pour un traitement
     * ultérieur.
     * @param b Bille qui provoque le contact.
     * @return Contact - description du contact. 
     */
    @Override
    public Contact recoitContact(Bille b){
        if (b.position.y - b.rayon < 0)
            return new Contact(b, this, new Vecteur(0, 1), b.rayon - b.position.y);
        else if (b.position.y + b.rayon > taille.y)
            return new Contact(b, this, new Vecteur(0, 1), b.rayon - taille.y + b.position.y);
        else if (b.position.x - b.rayon < 0)
            return new Contact(b, this, new Vecteur(1, 0), b.rayon - b.position.x);
        else if (b.position.x + b.rayon > taille.x)
            return new Contact(b, this, new Vecteur(1, 0), b.rayon - taille.x + b.position.x);
        return null;
    }

}
