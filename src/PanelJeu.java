import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;

/*
 * TODO : Retirer l'accès à Plateau en attribut. Cela donne trop de pouvoir à
 * cette classe. Privilégier un attribut BilleBlanche.
 */

public class PanelJeu extends JPanel implements MouseMotionListener, MouseListener {
    private BilleBlanche billeBlanche;
    private Tir tirJoue;
    private ArrayList<Element> listeElements;
    private Terrain terrain;

    private int translate = 25;

    private int xS, yS;

	public PanelJeu(ArrayList<Element> _listeElements, 
                    Terrain _terrain,
                    BilleBlanche _billeBlanche) {
        addMouseListener(this);
        addMouseMotionListener(this);
        billeBlanche = _billeBlanche;
        listeElements = _listeElements;
        terrain = _terrain;
	}

    public void paint(Graphics g){
        g.translate(translate,translate);
        terrain.peindreElement(g);
        for (Element e : listeElements){
            e.peindreElement(g);
        }
        
        if (tirJoue==null){
            g.setColor(Color.blue);
            g.drawLine(xS, yS, (int)billeBlanche.position.x, (int)billeBlanche.position.y);
        }

    }

//Quand le joueur doit choisir son tire, on affiche le vecteur quand il bouge
	public void mouseMoved(MouseEvent e) {
    if(tirJoue==null){
        xS = e.getX() - translate;
        yS = e.getY() - translate;
        repaint();
        }
    }


  /*
   * TODO : Supprimer la méthode qui n'est plus utile.
  public Bille getBilleBlanche(){
    Elements [] elements = plateauJeu.listeElements;
    for( int t=0; t < elements.length; t++ ){
      if(elements[t] instanceof BilleBlanche){
        return elements[t];
      }
    }
  }
   */


  // Méthodes inutiles mais obligatoires pour compiler. Il faut tenir compte du
  // contrat passer avec les interfaces.
  public void mouseDragged(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {
      if(tirJoue==null){
          tirJoue = new Tir( e.getX()-translate,e.getY()-translate, billeBlanche);
      }
	}


  public Tir attendreTir() {
      tirJoue=null;
      repaint();
      while(tirJoue==null){
          try{
              // Permet au processeur de prendre un peu de répit et de ne pas
              // calculer la condition de la boucle à toute vitesse.
              Thread.sleep(100);
          }catch(InterruptedException e) {}
      }
      return tirJoue;
    }

}
