import javax.swing.*;
import java.awt.*;

public class ReglesDuJeu extends JFrame{

    private JTextField Regles;

    public ReglesDuJeu(){


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

				//Un deuxième panneau pour que le fond soit clean
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
				subtitle_1.setFont(new Font("Courier", Font.BOLD, 14));
				subtitle_1.setBounds(60,150,50,50);
				Fond_2.add(subtitle_1);

				//Texte : But du jeu
				JTextArea text_1 = new JTextArea();
				text_1.setBounds(60,160,800,500);
				text_1.setText("La partie sera gagnée par le joueur qui, après avoir empoché les 7 billes de sa couleur, empochera alors légalement la bille noire, de manière directe ou non dans l'une des 6 poches.");
				text_1.setFont(new Font("Courier", Font.PLAIN, 12));
        text_1.setLineWrap(true);
        text_1.setWrapStyleWord(true);
				Fond_2.add(text_1);

        //Sous-titre : Déroulement du jeu
        JLabel subtitle_2 = new JLabel();
        subtitle_2.setText("Déroulement du jeu");
        subtitle_2.setFont(new Font("Courier", Font.BOLD, 14));
        subtitle_2.setBounds(60,200,50,50);
        Fond_2.add(subtitle_2);

        //Texte : Déroulement du jeu
        JTextArea text_2 = new JTextArea();
				text_2.setBounds(60,210,800,500);
				text_2.setText("Le joueur 1 commence en cliquant et en tirant sur la queue pour l'orienter de façon à empocher une ou plusieurs de ses billes dans une poche. "
        +"Un tir n'est valable que si la boule blanche touche en premier une bille du même groupe de couleur que celui du joueur. "
        +"Le joueur peut continuer à jouer jusqu'à ce qu'il n'empoche aucune bille ou qu'il commette une faute. "
        +"Dans ce cas, c'est le tour du joueur 2. Les tours s'enchainent jusqu'à ce qu'un des joueurs empoche la boule noire.");
				text_2.setFont(new Font("Courier", Font.PLAIN, 12));
        text_2.setLineWrap(true);
        text_2.setWrapStyleWord(true);
				Fond_2.add(text_2);

        //Sous-titre : Fautes
        JLabel subtitle_3 = new JLabel();
        subtitle_3.setText("Déroulement du jeu");
        subtitle_3.setFont(new Font("Courier", Font.BOLD, 14));
        subtitle_3.setBounds(60,400,50,50);
        Fond_2.add(subtitle_3);

        //Texte : Fautes
				JTextArea text_3 = new JTextArea();
				text_3.setBounds(60,410,800,500);
				text_3.setText("-Empocher la blanche \n"
        +"-Toucher avec la blanche une bille adverse avant une de son groupe de couleur \n"
        +"-Ne toucher aucune bille avec la blanche \n"
        +"-Toucher la noire directement alors qu'il reste au joueur des billes de sa couleur \n"
        +"-Empocher une bille adverse \n"
        +"-Jouer ou toucher avec la queue une autre bille que la blanche");
				text_3.setFont(new Font("Courier", Font.PLAIN, 12));
        text_3.setLineWrap(true);
        text_3.setWrapStyleWord(true);
				Fond_2.add(text_3);
      }
}
