import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FenetreJeu extends JFrame implements ActionListener {

		private JTextField fenetreScore;
    private JTextField fenetreRegles;
    private JButton BoutonRegles;
    private PanelJeu panelJeu;

    public FenetreJeu(PanelJeu _panelJeu){

        this.setTitle("JEU DE BILLARD");
		    this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panneau qui affiche le SCORE des joueurs
        JPanel panneauScore = new JPanel();
        panneauScore.setBounds(20,20,150,100);
        panneauScore.setLayout(null);
        panneauScore.setBackground(Color.white);

        //Panneau qui montre les billes quand elles sont rentrées
        JPanel panneauBilles = new JPanel();
        panneauBilles.setBounds(600,20,160,50);
        panneauBilles.setLayout(null);
        panneauBilles.setBackground(Color.white);

        //Bouton qui affiche les règles du jeu quand on appuie dessus
        BoutonRegles = new JButton("Règles du jeu");
		    BoutonRegles.setBounds(20,20,160,50);
		    BoutonRegles.setBackground(Color.white);
		    BoutonRegles.setForeground(Color.black);
		    BoutonRegles.addActionListener(this);

        //Panneau général où tout se passe
        JPanel panneauGeneral = new JPanel();
		    panneauGeneral.setBounds(0,0,1000,700);
		    panneauGeneral.setLayout(null);
		    panneauGeneral.setBackground(new Color(209,184,161));
		    panneauGeneral.add(panneauScore);
        panneauGeneral.add(BoutonRegles);
        panneauGeneral.add(panneauBilles);

        panelJeu = _panelJeu; 
        panneauGeneral.add(panelJeu);

        this.add(panneauGeneral);
        this.setVisible(true);
    }

    //Pour afficher les règles quand on appuie sur le bouton "Règles du jeu"
    public void actionPerformed (ActionEvent e){
        if (e.getSource()==BoutonRegles){
            ReglesDuJeu reglesDuJeu = new ReglesDuJeu();
            reglesDuJeu.setVisible(true);
        }
    }
}
