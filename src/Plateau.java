import java.util.ArrayList;


public class Plateau{

    private ArrayList<Equipe> listeEquipes;
    private Geometrie geometrie;

    public Plateau(ArrayList<Equipe> _listeEquipes){

        listeEquipes = _listeEquipes;

    }

    public void setGeometrie(Geometrie _geometrie){
        geometrie = _geometrie;
    }

    public void lancerPartie(){
       if (geometrie == null){
           setGeometrie(new Geometrie());
       } 
       FenetreJeu fj = new FenetreJeu(); 

    }

    public class Geometrie{
        Vecteur dimension = new Vecteur(1000,600);
        Trou[] trous = {new Trou(new Vecteur(0,0)),
                        new Trou(new Vecteur(1000,600)),
                        new Trou(new Vecteur(1000,0)),
                        new Trou(new Vecteur(0,600))};
        Terrain terrain = new Terrain();
    }

}
