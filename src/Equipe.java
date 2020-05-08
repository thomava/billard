import java.util.ArrayList;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Equipe{

    private int joueurActuel;
    private ArrayList<Joueur> listeJoueurs;
    private BilleCouleur billeCouleurAssociée;


// CONSTRUCTEURS ----------------------------------------------------

    public Equipe(){
        listeJoueurs = new ArrayList<Joueur>();
    }

    public void addJoueur(Joueur j){
        listeJoueurs.add(j);
        j.setEquipe(this);
    }

    public void setBilleCouleur(BilleCouleur _bc){
        this.billeCouleurAssociée = _bc;
    }

    /**
     * Détermine et retourne le prochain joueur de cette équipe.
     * @return le joueur suivant de l'équipe.
     */
    public Joueur prochainJoueur(){ 
        joueurActuel++;
        if (joueurActuel >= listeJoueurs.size())
            joueurActuel = 0;

        return listeJoueurs.get(joueurActuel);
    }

    public void genererContenuPanel(JPanel eq, Joueur joueurActuel){
        eq.removeAll();

        for (Joueur j : listeJoueurs){
            JLabel joueurLbl = new JLabel(j.getNom());
            joueurLbl.setFont(new Font("Arial", Font.BOLD, 16));
            if (joueurActuel == j){
                joueurLbl.setForeground(Color.BLUE);
            }
            eq.add(joueurLbl);
        }
        
        JPanel scorePan = new JPanel();
        scorePan.setLayout(new BorderLayout());

        JLabel scoreLbl = new JLabel("8", SwingConstants.CENTER);
        scoreLbl.setPreferredSize(new Dimension(100,20));
        scoreLbl.setFont(new Font("Arial", Font.BOLD, 24));

        scorePan.add(scoreLbl, BorderLayout.CENTER);

        eq.add(scorePan);

        if (billeCouleurAssociée != null){
            System.out.println(billeCouleurAssociée.getCouleur());
            eq.setForeground(billeCouleurAssociée.getCouleur());
            eq.setBackground(billeCouleurAssociée.getCouleur());
            scorePan.setBackground(billeCouleurAssociée.getCouleur());
            scorePan.setForeground(billeCouleurAssociée.getCouleur());
        }

        eq.revalidate();
        eq.repaint();
    }

    @Override
    public String toString(){
        if (billeCouleurAssociée != null)
            return "couleur : "+billeCouleurAssociée;
        else
            return super.toString();
    }

}
