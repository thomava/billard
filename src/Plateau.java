/*
 * TODO :  Gérer le score.
 * TODO :  Gérer la fin de tour.
 * TODO :  Gérer la fin de partie.
 */

import java.util.ArrayList;
import java.awt.Color;

public class Plateau{

    private Equipe[] listeEquipes;
    private ArrayList<Bille> listeBillesTombées;
    private int equipeActuelle;
    private PanelJeu panelJeu;
    private MoteurPhysique moteurPhy;

    private ArrayList<Element> listeElements;
    private BilleBlanche bb;
    private BilleNoire bn;
    private Terrain tr;

    // Attributs relatifs à un tour.
    private Joueur joueurActuel;
    private boolean faute;
    private boolean billeBlancheTombée;

    public Plateau(Equipe[] _listeEquipes){
        listeEquipes = _listeEquipes;

        listeElements = genererElements();
        panelJeu = new PanelJeu(listeElements, tr, bb);
        FenetreJeu fj = new FenetreJeu(panelJeu);

        listeBillesTombées = new ArrayList<Bille>();

        moteurPhy = new MoteurPhysique(panelJeu, bb, listeElements);
    }


    public ArrayList<Element> genererElements(){
        Vecteur diagonaleTerrain = new Vecteur(750,450);
        ArrayList<Element> elementArray = new ArrayList<Element>();
        double rayonBille = 13;
        double rayonTrou = 18;

        bb = new BilleBlanche(new Vecteur(600,200), rayonBille, 200);
        tr = new Terrain(diagonaleTerrain);
        elementArray.add(tr);
        elementArray.add(bb);
        this.billeBlancheTombée = false;

        // génération des trous du terrain
        for (int i = 0; i<3 ; i++){
              double positionXtrou = rayonTrou+i*(diagonaleTerrain.x-2*rayonTrou)/2;
              Trou haut = new Trou(new Vecteur(positionXtrou,rayonTrou),rayonTrou);
              Trou bas = new Trou(new Vecteur(positionXtrou,diagonaleTerrain.y-rayonTrou),rayonTrou);
              elementArray.add(haut);
              elementArray.add(bas);
        }
        
        // génération des billes sur le terrain
        BilleCouleur cr = new BilleCouleur(Color.red);
        BilleCouleur cj = new BilleCouleur(Color.yellow);
        BilleCouleur[] placementOfficiel = new BilleCouleur[]{cj, cr, cj, cr, cj, cr, cj, null, cj, cr, cr, cr, cj, cj, cr};
        int k = 0;
        for (int i = 0; i<5 ; i++){
            for (int j = 0 ; j<5-i ; j++){
                Bille b;
                if (placementOfficiel[k] != null){
                    b = new Bille(new Vecteur(100+24*j,150+28*i+14*j),
                                        placementOfficiel[k],
                                        13,
                                        200);
                }else{
                    b = new BilleNoire(new Vecteur(100+24*j,150+28*i+14*j),
                                        13,
                                        200);
                }
                elementArray.add(b);
                k++;
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

    private boolean isBilleBlancheBienReplacée(){
        Vecteur pos = bb.getPosition();
        for (Element e : listeElements){
            if (e.recoitContact(bb) != null)
                return false;
        }
        //TODO Mettre les vrais zones autorisées.
        return true;
    }

    /**
     * Méthode utilisée pour lancer la partie.
     */
    public void lancerPartie(){
        while(!partieTerminée()){
            if (billeBlancheTombée){
                do{
                    panelJeu.attendreReplacerBilleBlanche();
                }while(!isBilleBlancheBienReplacée());
                listeElements.add(bb);
            }
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
        this.faute = desc.isFauteCommise();
        this.listeBillesTombées.addAll(desc.getListeBillesTombées());
        this.billeBlancheTombée = desc.isBilleBlancheTombée();
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
    public int nombreBillesTombees(BilleCouleur _billeCouleur){
        int c = 0;
        for( Bille b : listeBillesTombées){
            if(b.getBilleCouleur() == _billeCouleur){
                c += 1;
            }
        }
        return c;
    }

}
