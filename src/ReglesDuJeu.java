import javax.swing.*;
import java.awt.*;

public class ReglesDuJeu extends JFrame{

    public ReglesDuJeu(){

        private JTextField Regles;

        this.setTitle("REGLES DU JEU");
				this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panneau pour avoir un fond vert foncé sur toute la fenetre comme ça c'est beau
        JPanel Fond = new JPanel();
        Fond.setBounds(0,0,1000,700);
        Fond.setLayout(null);
        Fond.setBackground(new Color(9,61,23));
				this.add(Fond);

				//Un deuxièmre panneau pour que le fond soit clean
				JPanel Fond_2 = new JPanel();
				Fond_2.setBounds(50,50,900,600);
				Fond_2.setLayout(null);
				Fond_2.setBackground(Color.white);
				Fond.add(Fond_2);

				//Titre au milieu
				JLabel titre = new JLabel();
				titre.setText("REGLES DU JEU");
				titre.setBounds(475,100,50,50);
				titre.setFont(new Font("Courier", Font.BOLD, 18));
				Fond_2.add(titre);

				//Sous-titre : But du jeu
				JLabel subtitle_1 = new JLabel();
				subtitle_1.setText("But du jeu");
				subtitle_1.setFont(new Font("Courier", Font.PLAIN, 14));
				subtitle_1.setBounds(60,150,50,50);
				Fond_2.add(subtitle_1);

				//Texte : But du jeu
				JLabel text_1 = new JLabel();
				text_1.setBounds(60,160,800,500);
				text_1.setText("utiliser un text area ou je sais pas quoi pour que le texte revienne automatiquement à la ligne ");
				text_1.setFont(new Font("Courier", Font.PLAIN, 12));
				Fond_2.add(text_1);
}
