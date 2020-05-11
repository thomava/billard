import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.awt.Color;

public class Plateau{

    private Equipe[] listeEquipes;
    private BilleCouleur[] listeCouleurs;
    private ArrayList<Bille> listeBillesTombées;
    private int equipeActuelle;
    private PanelJeu panelJeu;
    private FenetreJeu fj;
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
        fj = new FenetreJeu(panelJeu);

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
        this.listeCouleurs = new BilleCouleur[]{cr,cj};
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
        this.fj.setVisible(true);
        boolean partTerminée = false;
        prochainJoueur();
        do{
            fj.genererContenuPanneauEquipe(listeEquipes, joueurActuel);

            if (billeBlancheTombée){
                do{
                    panelJeu.attendreReplacerBilleBlanche();
                }while(!isBilleBlancheBienReplacée());
                listeElements.add(bb);
            }
            Tir tir = panelJeu.attendreTir();

            DescriptionTour desc = new DescriptionTour(faute, joueurActuel);
            moteurPhy.executerTour(desc, tir);

            if (!finDeTour(desc)){
               prochainJoueur();
            }

            partTerminée = partieTerminée(desc);
        }while(!partTerminée);
    }

    /**
     * Actions à executer à la fin d'une partie.
     */
    public void finDePartie(Equipe billeNoireEq, Equipe eqGagne){
        //On ferme la fenetre du billard.
        fj.dispose();
        FenetreFinPartie fp = new FenetreFinPartie(billeNoireEq,
                                                   eqGagne,
                                                   listeEquipes);
        fp.setVisible(true);
    }

    /**
     * Vérifie à partir des règles du jeu si la partie est terminée.
     * @return True si la partie est terminée.
     */
    public boolean partieTerminée(DescriptionTour dt){
        if (dt.isBilleNoireTombée()){
            finDePartie(dt.getJoueurActuel().getEquipe(), null);
            return true;
        }

        for (Equipe e : listeEquipes){
            if (0 == nombreBillesRestantes(e.getBilleCouleur())){
                finDePartie(null, e);
            }
        }


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
        System.out.println(desc);
        this.faute = desc.isFauteCommise();
        this.listeBillesTombées.addAll(desc.getListeBillesTombées());
        this.billeBlancheTombée = desc.isBilleBlancheTombée();

        if(desc.isPremièreBilleTombée())
        {
            gérerAttributionEquipes(desc);
        }

        // Gestion du score
        for(Equipe eq : listeEquipes){
            //Si les équipes sont liées aux couleurs
            if (eq.getBilleCouleur() != null){
                eq.setScore(nombreBillesRestantes(eq.getBilleCouleur()));
            }
        }

        if(faute){
          panelJeu.afficherFaute();
        }

        fj.repaint();
        return (desc.peutRejouer());
    }

    public void gérerAttributionEquipes(DescriptionTour desc){
        Bille première = desc.getPremièreBilleTombée();
        for (BilleCouleur c : listeCouleurs){
            if (c == première.getBilleCouleur())
            {
                c.setEquipe(desc.getJoueurActuel().getEquipe());
                break;
            }
        }


        // Tout le reste de la méthode pour gérer le fait qu'il peut y avoir
        // plus de deux équipes. Fait en sorte que la denière équipe soit
        // attribué à la bonne BilleCouleur.

        int nbrSet = 0;
        for (BilleCouleur c : listeCouleurs){
            if (c.isEquipeSet())
                nbrSet++;
        }

        LinkedList<Equipe> equipeDistribuée = new LinkedList<Equipe>();
        LinkedList<Equipe> tempListeEquipes = new LinkedList<Equipe>(Arrays.asList(listeEquipes));
        if (nbrSet == listeCouleurs.length -1){
            for (BilleCouleur c : listeCouleurs){
                if (c.isEquipeSet()){
                    Equipe e = c.getEquipe();
                    equipeDistribuée.add(e);
                }
            }
            tempListeEquipes.removeAll(equipeDistribuée);

            for (BilleCouleur c : listeCouleurs){
                if (!c.isEquipeSet()){
                    c.setEquipe(tempListeEquipes.get(0));
                }
            }

        }

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

    public int nombreBillesRestantes(BilleCouleur _billeCouleur){
        int c = 0;
        for( Element e : listeElements){
            if (e instanceof Bille){
            Bille b = (Bille) e;
                if(b.getBilleCouleur() == _billeCouleur){
                    c += 1;
                }
            }
        }
        return c;
    }


}
