import java.util.ArrayList;

public class DescriptionTour{

    public boolean fauteCommise;
    private ArrayList<Bille> billesTombéesTour;
    private boolean peutRejouer;
    private Joueur joueurActuel;
    private boolean fauteTourAvant;

    public DescriptionTour(boolean _fauteTourAvant, Joueur _joueurActuel){
        billesTombéesTour = new ArrayList<Bille>();
        fauteCommise = false;
        peutRejouer = false;
        joueurActuel = _joueurActuel;
        fauteTourAvant = _fauteTourAvant;
    }

    public void addBilleTombée(Bille b){
        billesTombéesTour.add(b);
        if (b.getBilleCouleur().getEquipe() == joueurActuel.equipe)
            peutRejouer = true;
    }

    public ArrayList<Bille> getListeBillesTombées(){
        return billesTombéesTour;
    }

    public void Faute(){
        for( Bille b : billesTombéesTour ){
          if (b.getBilleCouleur().getEquipe() != joueurActuel.equipe){
            fauteCommise = true;
          }
        }
    }

    public boolean peutRejouer(){
        Faute();
        return ((fauteTourAvant || peutRejouer) && !fauteCommise);
    }

}
