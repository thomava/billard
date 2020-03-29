import java.awt.Graphics;

public abstract class Element{

    protected Vecteur position;

    /**
     * Méthode appelée par paint() de FenetreJeu pour peindre les différents
     * élements qui composent le Plateau.
     * @param g Objet Graphics pour peindre.
     */
    public abstract void peindreElement(Graphics g);
    
    /**
     * Méthode appelée par une bille qui provoque un contact. Elle génère un
     * objet Contact qui décrit le présent contact pour un traitement
     * ultérieur.
     * @param b Bille qui provoque le contact.
     * @return Contact - description du contact. 
     */
    public abstract Contact recoitContact(Bille b);

    /**
     * Méthode qui retourne la position de l'Element.
     * @return Position de l'Element.
     */
    public Vecteur getPosition(){
        return position;
    }

}
