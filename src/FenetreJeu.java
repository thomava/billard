import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FenetreJeu extends JFrame implements ActionListener {

    private JTextField fenetreScore;
    private JTextField fenetreRegles;
    private JButton BoutonRegles;
    private JButton BoutonQuit;
    private PanelJeu panelJeu;

    private JPanel panneauEquipeGeneral;
    private JPanel panneauInfo;
    private JLabel coupLibre;

    public FenetreJeu(PanelJeu _panelJeu){

        this.setTitle("JEU DE BILLARD");
		this.setSize(1000,750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panelJeu = _panelJeu; 

        //Panneau qui affiche le SCORE des joueurs
        panneauInfo = new JPanel();
        panneauInfo.setBounds(20,20,960,120);
        panneauInfo.setLayout(new BorderLayout());
        panneauInfo.setBackground(Color.white);
        genererContenuPanneauInformation();

        //Panneau général où tout se passe
        JPanel panneauGeneral = new JPanel();
		panneauGeneral.setBounds(0,0,1000,700);
		panneauGeneral.setLayout(null);
		panneauGeneral.setBackground(new Color(209,184,161));
		panneauGeneral.add(panneauInfo);
        panneauGeneral.add(panelJeu);

        coupLibre = new JLabel("Coup libre permis !");
        coupLibre.setFont(new Font("Arial", Font.PLAIN, 20));
        coupLibre.setBackground(Color.white);

        this.add(panneauGeneral);
    }

    public void genererContenuPanneauInformation(){

        //Panneau qui montre les billes quand elles sont rentrées
        JPanel panneauBilles = new JPanel();

        //Bouton qui affiche les règles du jeu quand on appuie dessus
        BoutonRegles = new JButton("Règles du jeu");
		BoutonRegles.setBackground(Color.white);
		BoutonRegles.setForeground(Color.black);
		BoutonRegles.addActionListener(this);
        BoutonRegles.setFont(new Font("Arial", Font.PLAIN, 20));

        //Bouton pour quitter la partie
        BoutonQuit = new JButton("Quitter");
		BoutonQuit.setBackground(Color.white);
		BoutonQuit.setForeground(Color.black);
		BoutonQuit.addActionListener(this);
        BoutonQuit.setFont(new Font("Arial", Font.PLAIN, 20));

        //Panneau qui contient les boutons
        JPanel panneauBouton = new JPanel();
        panneauBouton.setLayout(new BorderLayout()); 

        //Panneau qui contient les informations de la partie
        JPanel panneauPartie = new JPanel();
        panneauPartie.setLayout(new BorderLayout());
        genererContenuPanneauPartie(panneauPartie);

        panneauBouton.add(BoutonRegles, BorderLayout.NORTH);
        panneauBouton.add(BoutonQuit, BorderLayout.SOUTH);

        panneauInfo.add(panneauBouton, BorderLayout.WEST);
        panneauInfo.add(panneauPartie, BorderLayout.CENTER);

    }

    public void genererContenuPanneauPartie(JPanel panneauPartie){

        panneauEquipeGeneral = new JPanel();
        panneauEquipeGeneral.setLayout(new FlowLayout());

        JLabel equipeLbl = new JLabel("Equipes", SwingConstants.CENTER);
        equipeLbl.setFont(new Font("Arial", Font.PLAIN, 20));

        panneauPartie.add(equipeLbl, BorderLayout.NORTH);
        panneauPartie.add(panneauEquipeGeneral, BorderLayout.CENTER);
    }

    public void genererContenuPanneauEquipe(Equipe[] listeEquipes, Joueur joueurActuel, boolean faute){
        JPanel panEquipes = new JPanel();
        panEquipes.setLayout(new FlowLayout());

        for (Equipe e : listeEquipes){
            JPanel panEq = new JPanel();
            panEq.setLayout(new BoxLayout(panEq, BoxLayout.Y_AXIS)); 
            //panEq.setLayout(new FlowLayout()); 

            e.genererContenuPanel(panEq, joueurActuel);
            
            panEquipes.add(panEq);
        }
        panneauEquipeGeneral.removeAll();
        panneauEquipeGeneral.add(panEquipes);

        if (faute){
            panneauInfo.add(coupLibre, BorderLayout.EAST);
        }else{
            panneauInfo.remove(coupLibre);
        }
        panneauEquipeGeneral.revalidate();
        panneauEquipeGeneral.repaint();
    }

    //Pour afficher les règles quand on appuie sur le bouton "Règles du jeu"
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == BoutonRegles){
            ReglesDuJeu reglesDuJeu = new ReglesDuJeu();
            reglesDuJeu.setVisible(true);
        }

        if (e.getSource() == BoutonQuit){
            System.exit(0);
        }
 

    }
}
