import java.util.ArrayList;


public class Plateau{

    private ArrayList<Equipe> listeEquipes;
    private Geometrie geometrie;

    private Joueur joueurActuel;

    public Plateau(ArrayList<Equipe> _listeEquipes){
        listeEquipes = _listeEquipes;
    }

    public void setGeometrie(Geometrie _geometrie){
        geometrie = _geometrie;
    }

    /**
     * Méthode utilisée pour lancer la partie.
     */
    public void lancerPartie(){
       if (geometrie == null){
           setGeometrie(new Geometrie());
       } 
       //FenetreJeu fj = new FenetreJeu(); 

    }

    /**
     * Méthode qui retourne le joueur associé au tour actuel.
     * @return Joueur - le joueur du tour actuel. 
     */
    public Joueur getJoueurActuel(){
        return joueurActuel;
    }

    public class Geometrie{
        Vecteur dimension = new Vecteur(1000,600);
        Terrain terrain = new Terrain();
    }

}
