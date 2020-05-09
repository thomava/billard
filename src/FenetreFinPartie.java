import javax.swing.*;
import java.awt.*;

public class FenetreFinPartie extends JFrame{

    public FenetreFinPartie(Equipe eqBilleNoire, Equipe gagne, Equipe[] listeEquipes){

        this.setTitle("PARTIE TERMINÉE");
	    this.setSize(800,100);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JPanel genEqPan = new JPanel();
        genEqPan.setLayout(new BoxLayout(genEqPan, BoxLayout.Y_AXIS));
        this.getContentPane().add(genEqPan);
        this.getContentPane().setLayout(new BorderLayout());

        System.out.println("-----------------------------");
        System.out.println("PARTIE TERMINÉE");

        for (Equipe e : listeEquipes){
            JPanel eqPan = new JPanel();
            eqPan.setLayout(new FlowLayout());

            e.genererContenuFinPartie(eqPan);
            if (eqBilleNoire == e){
                JLabel billeNoireEqLbl = new JLabel(" ont fait tombé la bille noire !");
                billeNoireEqLbl.setFont(new Font("Arial", Font.PLAIN, 20));
                eqPan.add(billeNoireEqLbl);
                System.out.print(e.getBilleCouleur()+" a fait tomber la bille noire");
            }

            if (gagne == e){
                JLabel gagneLbl = new JLabel(" ont gagné !");
                gagneLbl.setFont(new Font("Arial", Font.PLAIN, 20));
                eqPan.add(gagneLbl);
                System.out.print(e.getBilleCouleur()+" a fait tomber toutes les billes");
            }

            genEqPan.add(eqPan, BorderLayout.CENTER);
        }

    }
}
