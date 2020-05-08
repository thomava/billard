import java.awt.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.MouseInfo;
import java.awt.Graphics;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class PanelJeu extends JPanel implements MouseMotionListener, MouseListener, ChangeListener, ActionListener{
    private BilleBlanche billeBlanche;
    private Vecteur direction;
    private double norme = 0.0;
    private Tir tirJoue;
    private ArrayList<Element> listeElements;
    private Terrain terrain;
    private JSlider curseurNorme;
    private JButton validerNorme;

    private boolean etatReplacementBilleBlanche;

    private int translate = 25;

    private int xS, yS;

     	public PanelJeu(ArrayList<Element> _listeElements, Terrain _terrain, BilleBlanche _billeBlanche) {
		super();
		int haut = 50;
		billeBlanche = _billeBlanche;
        listeElements = _listeElements;
        terrain = _terrain;
		this.setBounds(100,150, (int)terrain.getXTerrain()+2*translate, (int)terrain.getYTerrain()+2*translate+haut); //(int)terrain.getXTerrain()
        addMouseListener(this);
        addMouseMotionListener(this);

		int Vmax = 500;
        curseurNorme = new JSlider(JSlider.HORIZONTAL,0, Vmax, 0);
        curseurNorme.addChangeListener(this);
        curseurNorme.setBounds(0,(int)terrain.getYTerrain()+2*translate, 300,haut);
        validerNorme = new JButton("Valider");
        validerNorme.addActionListener(this);
        validerNorme.setBounds(300,(int)terrain.getYTerrain()+2*translate, 200, haut);

        this.add(curseurNorme);
		this.add(validerNorme);
        setLayout(null);
	}

    public void paint(Graphics g){
		super.paint(g);
        g.translate(translate,translate);
        terrain.peindreElement(g);
        for (Element e : listeElements){
            e.peindreElement(g);
        }

        // tant que le jour n'a pas choisi sa direction, on affiche le vecteur direction qui bouge avec la souris
        if ((tirJoue==null)&&(direction==null)){
            Vecteur dir = (new Vecteur( billeBlanche.position.x - xS,billeBlanche.position.y - yS)).normaliser();
            dessinerQueue(g, dir);
        }

        //tant que le joueur n'a pas chosi sa norme, on affiche le tir qu'il chosit en fonction de la norme
        if ((tirJoue==null)&&(direction!=null)){
            g.setColor(Color.red);
            Vecteur Arrivee = direction.mul(1.5*norme);
            g.drawLine((int)billeBlanche.position.x, (int)billeBlanche.position.y, (int)(billeBlanche.position.x + Arrivee.x), (int)(billeBlanche.position.y + Arrivee.y));
            dessinerQueue(g, direction);
        }

        if (etatReplacementBilleBlanche){
            billeBlanche.peindreElement(g);
        }

    }

	public void dessinerQueue(Graphics g, Vecteur dir){
		Graphics2D g2 = (Graphics2D) g;
        Stroke s = g2.getStroke();
		g2.setStroke(new BasicStroke(6));
		g2.setColor(new Color(126, 88, 53));
        g2.drawLine((int)(billeBlanche.position.x - dir.mul(16).x), (int)(billeBlanche.position.y - dir.mul(16).y), (int)(billeBlanche.position.x - dir.mul(300).x), (int)(billeBlanche.position.y - dir.mul(300).y));
        g2.setColor(Color.black);
		g2.drawLine((int)(billeBlanche.position.x - dir.mul(16).x), (int)(billeBlanche.position.y - dir.mul(16).y), (int)(billeBlanche.position.x - dir.mul(30).x), (int)(billeBlanche.position.y - dir.mul(30).y));
		g2.setStroke(s);
	}
//Quand le joueur doit choisir son tire, on affiche le vecteur quand il bouge
	public void mouseMoved(MouseEvent e) {

    if((tirJoue==null)&&(direction==null) || etatReplacementBilleBlanche){

        xS = e.getX() - translate;
        yS = e.getY() - translate;
        repaint();

        if (etatReplacementBilleBlanche){
            billeBlanche.setPosition(new Vecteur(xS, yS));
        }

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
          direction = (new Vecteur( billeBlanche.position.x - xS,billeBlanche.position.y - yS)).normaliser();
      }

      if(etatReplacementBilleBlanche){
          this.etatReplacementBilleBlanche = false;
      }
	}

  public void actionPerformed(ActionEvent e) {
	  try{
		tirJoue = new Tir(direction.mul(norme));
		curseurNorme.setValue(0);
	}catch(NullPointerException a){
		System.out.println("Cliquer pour valider la direction de tir");
		/*
   * TODO : Afficher messag erreur
   */
	}
  }

  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    norme = (double)source.getValue();
    repaint();
}

  private void updateMousePosition(){
      this.xS = (int)(MouseInfo.getPointerInfo().getLocation().getX()
                        - this.getLocationOnScreen().getX()
                        - translate);
      this.yS = (int)(MouseInfo.getPointerInfo().getLocation().getY()
                        - this.getLocationOnScreen().getY()
                        - translate);
  }

  public void attendreReplacerBilleBlanche() {
      this.etatReplacementBilleBlanche = true;

      updateMousePosition();
      billeBlanche.setPosition(new Vecteur(xS, yS));

      repaint();
      while(etatReplacementBilleBlanche){
          try{
              // Permet au processeur de prendre un peu de répit et de ne pas
              // calculer la condition de la boucle à toute vitesse.
              Thread.sleep(100);
          }catch(InterruptedException e) {}
      }
    }



  public Tir attendreTir() {
      direction=null;
      norme = 0;
      tirJoue=null;

      updateMousePosition();
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
