import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;

public class PanelJeu extends JPanel implements MouseMotionListener, MouseListener {
    private BilleBlanche billeBlanche;
    private Vecteur direction;
    private double norme;
    private Tir tirJoue;
    private ArrayList<Element> listeElements;
    private Terrain terrain;
    private JSlider curseurNorme;
    private JButton validerNorme;

    private int translate = 25;

    private int xS, yS;

    /*
     * TODO : Positionner les boutons et jauges et choisir la vitesse max ( celle qui permet de parcourir la longeur du terrain.
     */
     	public PanelJeu(ArrayList<Element> _listeElements,
                    Terrain _terrain,
                    BilleBlanche _billeBlanche) {
        addMouseListener(this);
        addMouseMotionListener(this);
        billeBlanche = _billeBlanche;
        listeElements = _listeElements;
        terrain = _terrain;

        curseurNorme = new JSlider(JSlider.HORIZONTAL,0, Vmax, Vmax/2);
        curseurNorme.addChangeListener(this);
        validerNorme = new JButton("Valider");
        validerNorme.addActionListener(this);
	}

    public void setNorme()
    public void paint(Graphics g){
        g.translate(translate,translate);
        terrain.peindreElement(g);
        for (Element e : listeElements){
            e.peindreElement(g);
        }

        // tant que le jour n'a pas choisi sa direction, on affiche le vecteur direction qui bouge avec la souris
        if ((tirJoue==null)&&(direction==null)){
            g.setColor(Color.blue);
            Vecteur dir = normaliser(new Vecteur( billeBlanche.position.x - xS,billeBlanche.position.y - xS));
            g.drawLine((int)billeBlanche.position.x, (int)billeBlanche.position.x, (int)billeBlanche.position.x - dir.x, (int)billeBlanche.position.y - dir.y);
        }

        //tant que le joueur n'a pas chosi sa norme, on affiche le tir qu'il chosit en fonction de la norme
        if ((tirJoue==null)&&(direction!=null)){
            g.setColor(Color.red);
            g.drawLine((int)billeBlanche.position.x, (int)billeBlanche.position.x, (int)billeBlanche.position.x + dir.x * norme, (int)billeBlanche.position.y - dir.y * norme);
        }

    }

//Quand le joueur doit choisir son tire, on affiche le vecteur quand il bouge
	public void mouseMoved(MouseEvent e) {

    //si le joueur n'a pas choisi son tir, on affiche la direction du tir
    if((tirJoue==null)&&(direction==null)){
        xS = e.getX() - translate;
        yS = e.getY() - translate;
        repaint();
        }
    }

  // Méthodes inutiles mais obligatoires pour compiler. Il faut tenir compte du
  // contrat passer avec les interfaces.
  public void mouseDragged(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {
      if((tirJoue==null)&&(direction==null)){
          direction = normaliser(new Vecteur( billeBlanche.position.x - e.getX(), billeBlanche.position.y - e.getY()));
          ChoixNorme();
      }
	}

  public void ChoixNorme() {
    this.add(curseurNorme);
    this.add(validerNorme);
  }

  public void actionPerformed(ActionEvent e) {
    tirJoue = new Tir( direction.mul(norme), billeBlanche);
	}

  /*
   * TODO : Affichage de l'effet de la norme : dans l'idéal trajectoire de la bille si elle ne rencontre rien
   */

  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    norme = (double)source.getValue();
}

  public Tir attendreTir() {
      direction=null;
      norme = null;
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
