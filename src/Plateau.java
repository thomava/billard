import java.util.ArrayList;


public class Plateau{

    private Equipe[] listeEquipes;
    private Element[] listeElements;
    private ArrayList<Bille> listeBillesTombées;
    private int equipeActuelle;
    private boolean faute;

    private Joueur joueurActuel;

    public Plateau(Equipe[] _listeEquipes){
        listeEquipes = _listeEquipes;
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
            panelJeu.attendreTir();
            // TODO : Appelé le PanelJeu pour lui dire de redonner la main au
            // joueur et lui laisser le droit de tirer. Cette méthode retourne
            // les paramètres de tir : force, angle, ..
            
            // TODO : Lancer le moteur physique à partir des paramètre de tir
            // récuprer la méthode au dessus. Cette méthode est blocante. Elle
            // retourne le controle à plateau quand toutes les billes sont
            // arretées. Et donc quand le tour est terminé ! Cette méthode
            // retourne les informations importantes sur le tour : présence de
            // faute, billes qui sont tombées.

            finDeTour(new DescriptionTour()); 
        }
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
     */
    public void finDeTour(DescriptionTour desc){
        this.faute = desc.fauteCommise; 
        this.listeBillesTombées.addAll(desc.billesTombéesTour); 
    }

    /**
     * Méthode qui retourne le joueur associé au tour actuel.
     * @return Joueur - le joueur du tour actuel. 
     */
    public Joueur getJoueurActuel(){
        return joueurActuel;
    }

}
