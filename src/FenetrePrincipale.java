import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame implements ActionListener {

    private JButton BoutonRegles;
    private JButton LancerPartie;

    private Plateau p;

    public FenetrePrincipale(Plateau _p){

        p = _p;

        this.setTitle("BIENVENUE");
        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TITRE Jeu de Billard en haut au milieu de la fenetre
        JLabel JDB = new JLabel();
        JDB.setText("JEU DE BILLARD");
        JDB.setBounds(400,10,500,50);
        JDB.setFont(new Font("Courier", Font.BOLD, 30));
        
        //Texte BIENVENUE
        JTextArea Intro = new JTextArea();
        Intro.setBounds(100,20,200,100);
        Intro.setText("Bienvenue au jeu de billard. \n"
        + "Afin de démarrer le jeu, appuyez sur le bouton LANCER UNE PARTIE. \n"
        + "Pour lire les règles du jeu, appuyez sur le bouton REGLES DU JEU");
        Intro.setFont(new Font("Courier", Font.PLAIN, 12));
        Intro.setLineWrap(true);
        Intro.setWrapStyleWord(true);
        Intro.setEditable(false);
        
        //Noms des créateurs du programme
        JLabel credits = new JLabel();
        credits.setText("Réalisé par : Thomas Léa Diane K41 etc");
        credits.setBounds(300,400,500,50);
        credits.setFont(new Font("Courier", Font.BOLD, 14));

        //Bouton qui permet de lancer une partie
        LancerPartie = new JButton("LANCER UNE PARTIE");
        LancerPartie.setBounds(460,250,160,50);
        LancerPartie.setBackground(Color.white);
        LancerPartie.setBackground(Color.black);
        LancerPartie.addActionListener(this);
        
        //Bouton qui affiche les règles du jeu 
        BoutonRegles = new JButton("REGLES DU JEU");
        BoutonRegles.setBounds(460,300,160,50);
        BoutonRegles.setBackground(Color.white);
        BoutonRegles.setForeground(Color.black);
        BoutonRegles.addActionListener(this);

        //Panneau principal 
        JPanel panneauPrincipal = new JPanel();
        panneauPrincipal.setBounds(0,0,1000,700);
        panneauPrincipal.setLayout(null);
        //panneauPrincipal.setBackground(new Color(9,61,23));
        panneauPrincipal.add(JDB);
        panneauPrincipal.add(Intro);
        panneauPrincipal.add(LancerPartie);
        panneauPrincipal.add(BoutonRegles);
        panneauPrincipal.add(credits);

        this.add(panneauPrincipal);
        this.setVisible(true);
    }

    //Pour lancer une partie et afficher les règles du jeu
    public void actionPerformed (ActionEvent e){
        if (e.getSource()==BoutonRegles){
            ReglesDuJeu reglesDuJeu = new ReglesDuJeu();
            reglesDuJeu.setVisible(true);
        }
        if (e.getSource()==LancerPartie){
            System.out.println("Lancer partie click");
            genererPartie_test();
        }
    }

    public void genererPartie_test(){

        this.setVisible(false);

        p.lancerPartie();

    }


}
