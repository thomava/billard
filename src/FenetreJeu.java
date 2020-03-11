import javax.swing.*;

public class FenetreJeu extends JFrame{

    Plateau p;

    public FenetreJeu(Plateau _p){
        setSize(1000,700);
        setVisible(true);

        p = _p;
    }

}
