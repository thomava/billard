import java.awt.Graphics;
import java.awt.Color;

public class Terrain extends Element{

    /**
     * Taille du terrain en pixel (longueur, largeur).
     */
    private Vecteur taille;

    public Terrain(Vecteur _taille){
        this.taille = _taille; 
    }
    
    public double getYTerrain(){
        return taille.y; 
    }
    
    public double getXTerrain(){
        return taille.x; 
    }

    
    /**
     * Méthode appelée par paint() de FenetreJeu pour peindre les différents
     * élements qui composent le Plateau.
     * @param g Objet Graphics pour peindre.
     */
    @Override
    public void peindreElement(Graphics g){
        g.setColor(new Color(85,35,0));
        g.fillRect(-25,-25, (int)(taille.x + 50), (int)(taille.y + 50));
        g.setColor(new Color(0,80,0));
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
        //TODO : pourquoi - 25 ?????
        if (b.position.y - b.getRayon() < 0)
            return new Contact(b, this, new Vecteur(0, 1), b.getRayon() - b.position.y);
        else if (b.position.y + b.getRayon() > taille.y)
            return new Contact(b, this, new Vecteur(0, 1), b.getRayon() - taille.y + b.position.y);
        else if (b.position.x - b.getRayon() < 0)
            return new Contact(b, this, new Vecteur(1, 0), b.getRayon() - b.position.x);
        else if (b.position.x + b.getRayon() > taille.x)
            return new Contact(b, this, new Vecteur(1, 0), b.getRayon() - taille.x + b.position.x);
        return null;
    }

}
