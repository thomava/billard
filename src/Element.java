import java.awt.Graphics;

public abstract class Element{

    public Element(){

    }

    public abstract void drawElement(Graphics g);

    public abstract Contact recoitContact(Bille b);

}
