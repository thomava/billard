import java.awt.event.*;
public class PanelJeu extends JPanel implements MouseMotionListener, MouseListener {
  private Plateau plateauJeu;
	private int SourisX;
  private int SourisY;
  private Tir tirJoue;

	public PanelJeu( Plateau plateau ) {

    addMouseListener( this );
    PlateauJeu = plateau;
    SourisX = getBilleBlanche().position.x;
    SourisY = getBilleBlanche().position.y;

	}

//Quand le joueur doit choisir son tire, on affiche le vecteur quand il bouge
	public void mouseMoved(MouseEvent e) {
    if(tirJoue==null){
      repaint();
    }

  public Bille getBilleBlanche(){
    Elements [] elements = plateau.listeElements;
    for( int t=0; t < Elements.length; t++ ){
      if(Elements[t] instance of BilleBlanche){
        return Elements[t];
      }
    }
  }
  public void mousePressed(MouseEvent e) {
      if(tirJoue==null){
          Tir = new Tir( e.getX(),e.getY(), getBilleBlanche());
      }
	}
  public Tir attendreTir() {
      tirJoue=null;
      while(tirJoue==null){
        Thread.sleep(100);
      }
      return tirJoue;
}
