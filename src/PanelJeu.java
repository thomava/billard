import java.awt.event.*;
import java.awt.Font;
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
import javax.swing.Timer;

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
    private String Message = null;
    private Timer Chrono;

    private int translate = 25;

    private int xS, yS;

// CONSTRUCTEURS -----------------------------------------------------------------------------------------------------

    public PanelJeu(ArrayList<Element> _listeElements, Terrain _terrain, BilleBlanche _billeBlanche) {
        super();
        Chrono = new Timer( 5000, this );
        int haut = 50; // hauteur des éléments de choix de la norme du tir
        billeBlanche = _billeBlanche;
        listeElements = _listeElements;
        terrain = _terrain;

        //dimension du panel qui va contenir le terrain, et les éléments de choix de la norme du tir
        this.setBounds(100,150, (int)terrain.getXTerrain()+2*translate, (int)terrain.getYTerrain()+2*translate+haut);
        //on va écouter le mouvement de la souris et les clics
        addMouseListener(this);
        addMouseMotionListener(this);

        // valeur maximale que le curseur va pouvoir proposer pour la norme
        int Vmax = 500;
        // création du curseur du choix de la norme
        curseurNorme = new JSlider(JSlider.HORIZONTAL,0, Vmax, 0);
        curseurNorme.addChangeListener(this);
        curseurNorme.setBounds(0,(int)terrain.getYTerrain()+2*translate, 300,haut);
        // création du bouton permettant de valider le choix de la norme
        validerNorme = new JButton("Valider");
        validerNorme.addActionListener(this);
        validerNorme.setBounds(300,(int)terrain.getYTerrain()+2*translate, 200, haut);
        // ajout des éléments dans le panel
        this.add(curseurNorme);
        this.add(validerNorme);
        setLayout(null);
    }

// GET et SET -----------------------------------------------------------------------------------------------------

    public void afficherFaute(){
        Message = "Une faute a été comise." ;
        Chrono.start();
        repaint();
    }
    public void afficherClic(){
        Message = "Cliquer pour valider la direction de tir" ;
        Chrono.start();
        repaint();
    }
    public void afficherNorme(){
        Message = "Choisissez une force non nulle." ;
        Chrono.start();
        repaint();
    }

// Gestion des évènements sur la souris ---------------------------------------------------------------------------

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

// Gestion des évènements des éléments graphiques ---------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e) {

        // Lorsque l'utilisateur va cliquer sur le bouton valider
        if(e.getSource()==validerNorme){
            try{
                // Si la norme est non nulle, on essaie de créer l'objet
                if(norme!=0.0){
                    tirJoue = new Tir(direction.mul(norme));
            		    curseurNorme.setValue(0);
                }
                else{
                    // Si la norme est nulle, on demande à l'utilisateur de choisir une norme
                    afficherNorme();
                }
            // Si on ne peut pas effectuer le try, c'est que le vecteur est nul donc on demande à l'utilisateur de valider une direction
        	  }catch(NullPointerException a){
            		afficherClic();
        	  }
        }

        // Le timer permet d'effacer les messages ephémères du panel après un certain temps ( 5sec )
        if(e.getSource()==Chrono){
          Message=null;
          Chrono.stop();
          repaint();
        }

    }


    public void stateChanged(ChangeEvent e) {
        // Chaque fois que l'utilisateur change la valeur de la norme, l'affichage s'adapte à la nouvelle norme
        JSlider source = (JSlider)e.getSource();
        norme = (double)source.getValue();
        repaint();
  }

//Méthodes d'attente de décision ---------------------------------------------------------------------------
  public void attendreReplacerBilleBlanche() {
      this.etatReplacementBilleBlanche = true;

      //On affiche
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

// AFFICHAGE ----------------------------------------------------------------------------------------------------

    public void paint(Graphics g){
        super.paint(g);

        // On affiche les éléments du plateau en ne décalant le coin pour que le plateau s'affiche bien au milieu du pane et que les éléments sient bien positionnés
        g.translate(translate,translate);
        terrain.peindreElement(g);
        for (Element e : listeElements){
            e.peindreElement(g);
        }

        // si c'est au joeur de tirer et tant qu'il n'a pas choisi sa direction, on affiche la queue pour avoir la direction
        if ((tirJoue==null)&&(direction==null)){
            Vecteur dir = (new Vecteur( billeBlanche.position.x - xS,billeBlanche.position.y - yS)).normaliser();
            dessinerQueue(g, dir);
        }

        //quand le joueur a validé une direction en cliquant et tant que le joueur n'a pas chosi sa norme, on affiche le tir qu'il chosit en fonction de la norme
        if ((tirJoue==null)&&(direction!=null)){
            // on propose au joueur une approximation de l'endroit ou arriverait sa bille si elle n'avait aucun contact
            g.setColor(Color.red);
            Vecteur Arrivee = direction.mul(1.5*norme);
            g.drawLine((int)billeBlanche.position.x, (int)billeBlanche.position.y, (int)(billeBlanche.position.x + Arrivee.x), (int)(billeBlanche.position.y + Arrivee.y));
            //On affiche la position de la queue choisie précément par le joueur
            dessinerQueue(g, direction);
        }

        //On affiche la bille blanche sous la souris du joueur si ce dernier doit la replacer
        if (etatReplacementBilleBlanche){
            billeBlanche.peindreElement(g);
        }

        //On affiche un message de faute ou de consigne si il y en a
        if(Message!=null){
			g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.PLAIN, 20)); 
            g.drawString(Message, (int)terrain.getXTerrain()/2+translate, 3*translate );

        }
    }

    public void dessinerQueue(Graphics g, Vecteur dir){
        //On utilise un Graphics2D pour dessiner des lignes plus épaisses
        Graphics2D g2 = (Graphics2D) g;
        Stroke s = g2.getStroke(); // On mémorise la largeur du trait initial
        g2.setStroke(new BasicStroke(6));
        g2.setColor(new Color(126, 88, 53));
        g2.drawLine((int)(billeBlanche.position.x - dir.mul(16).x), (int)(billeBlanche.position.y - dir.mul(16).y), (int)(billeBlanche.position.x - dir.mul(300).x), (int)(billeBlanche.position.y - dir.mul(300).y));
        g2.setColor(Color.black);
        g2.drawLine((int)(billeBlanche.position.x - dir.mul(16).x), (int)(billeBlanche.position.y - dir.mul(16).y), (int)(billeBlanche.position.x - dir.mul(30).x), (int)(billeBlanche.position.y - dir.mul(30).y));
        g2.setStroke(s);
    }

    private void updateMousePosition(){
        this.xS = (int)(MouseInfo.getPointerInfo().getLocation().getX()
                          - this.getLocationOnScreen().getX()
                          - translate);
        this.yS = (int)(MouseInfo.getPointerInfo().getLocation().getY()
                          - this.getLocationOnScreen().getY()
                          - translate);
    }
}
