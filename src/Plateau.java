/*
 * TODO :  Gérer les fautes.
 * TODO :  Gérer le score.
 * TODO :  Gérer la fin de tour.
 * TODO :  Gérer la fin de partie.
 */

import java.util.ArrayList;
import java.awt.Color;

public class Plateau{

    // TODO : Vérifier que les attributs ne sont pas null.
    private Equipe[] listeEquipes;
    private ArrayList<Bille> listeBillesTombées;
    private int equipeActuelle;
    private boolean faute;
    private PanelJeu panelJeu;
    private MoteurPhysique moteurPhy;


    private ArrayList<Element> listeElements;
    private BilleBlanche bb;
    private BilleNoire bn;
    private Terrain tr;

    private Joueur joueurActuel;

    public Plateau(Equipe[] _listeEquipes){
        listeEquipes = _listeEquipes;

        listeElements = genererElements();
        panelJeu = new PanelJeu(listeElements, tr, bb);
        FenetreJeu fj = new FenetreJeu(panelJeu);

        listeBillesTombées = new ArrayList<Bille>();

        moteurPhy = new MoteurPhysique(panelJeu, bb, listeElements);
    }


    public ArrayList<Element> genererElements(){
        ArrayList<Element> elementArray = new ArrayList<Element>();
        bb = new BilleBlanche(new Vecteur(600,200), 13, 200);
        tr = new Terrain(new Vecteur(750,450));
        elementArray.add(tr);
        elementArray.add(bb);


        BilleCouleur cr = new BilleCouleur(Color.red);
        BilleCouleur cj = new BilleCouleur(Color.yellow);
        for (int i = 0; i<5 ; i++){
            for (int j = 0 ; j<5-i ; j++){
                Bille b = new Bille(new Vecteur(100+24*j,150+28*i+14*j),
                                    cr,
                                    13,
                                    200);

                elementArray.add(b);
            }
        }


        return elementArray;
    }

    /**
     * Change l'attribut joueurActuel en le joueur suivant.
     */
    public void prochainJoueur(){
        equipeActuelle++;
        if (equipeActuelle >= listeEquipes.length)
            equipeActuelle=0;

        joueurActuel = listeEquipes[equipeActuelle].prochainJoueur();
    }

    /**
     * Méthode utilisée pour lancer la partie.
     */
    public void lancerPartie(){
        while(!partieTerminée()){
            Tir tir = panelJeu.attendreTir();

            DescriptionTour desc = new DescriptionTour(faute, joueurActuel);
            moteurPhy.executerTour(desc, tir);
            // TODO : Lancer le moteur physique à partir des paramètre de tir
            // récuprer la méthode au dessus. Cette méthode est blocante. Elle
            // retourne le controle à plateau quand toutes les billes sont
            // arretées. Et donc quand le tour est terminé ! Cette méthode
            // retourne les informations importantes sur le tour : présence de
            // faute, billes qui sont tombées.

            boolean peutRejouer = finDeTour(desc);
            if (!peutRejouer){
               prochainJoueur();
            }
        }
    }

    /**
     * Vérifie à partir des règles du jeu si la partie est terminée.
     * @return True si la partie est terminée.
     */
    public boolean partieTerminée(){
       // TODO : La méthode
        return false;
    }


    /**
     * Méthode appelée quand toutes les billes sont immobiles par
     * MoteurPhysique.
     *
     * Elle gère le changement de tour : changement de joueur, attribution des
     * pénalités (si le joueur n'a pas touché une bille de sa couleur en
     * premier) et le changement du score.
     * @param desc - Description du tour qui contient deux attributs :
     * fauteCommise (boolean) et billesTombéesTour (ArrayList Bille).
     * @return True si le joueur peut rejouer. Si il a rentré une bille.
     */
    public boolean finDeTour(DescriptionTour desc){
        this.faute = desc.fauteCommise;
        this.listeBillesTombées.addAll(desc.getListeBillesTombées());
        return (desc.peutRejouer());
    }

    /**
     * Méthode qui retourne le joueur associé au tour actuel.
     * @return Joueur - le joueur du tour actuel.
     */
    public Joueur getJoueurActuel(){
        return joueurActuel;
    }

    /**
     * Méthode qui retourne le nombre de billes d'une équipe qui sont tombées.
     * @param equipeBille - équipe dont on veut savoir le nombre de billes qui sont tombées.
     * @return int - nombre de billes tombées de cette équpe.
     */
    public int nombreBillesTombees(Equipe equipeBille){
        int c = 0;
        for( Bille b : listeBillesTombées){
            if(b.getEquipe() == equipeBille){
                c += 1;
            }
        }
        return c;
    }

}
