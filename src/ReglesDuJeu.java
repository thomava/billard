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
        
        //Panneau pour avoir un fond blanc mais pas sur toute la fenetre comme ça c'est beau
        JPanel Fond = new JPanel();
        Fond.setBounds(50,50,700,700);
        Fond.setLayout(null);
        Fond.setBackground(Color.white);
        
}

