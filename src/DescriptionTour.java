import java.util.ArrayList;

/** Décrit les conséquences importantes d'un tour.
 *
 *  - Détermine si il y a eu une faute.
 *  - Par extension, détermine si le joueur peut rejouer.
 *  - Liste les billes tombées.
 *
 *  @author Diane, Léa, Thomas
 */
public class DescriptionTour{

    private boolean fauteCommise;
    private ArrayList<Bille> billesTombéesTour;
    private boolean peutRejouer;
    private Joueur joueurActuel;
    private boolean fauteTourAvant;
    private Bille premierContact;

    private boolean billeBlancheTombée;
    private boolean billeNoireTombée;

    public DescriptionTour(boolean _fauteTourAvant, Joueur _joueurActuel){
        this.billesTombéesTour = new ArrayList<Bille>();
        this.fauteCommise = false;
        this.peutRejouer = false;
        this.joueurActuel = _joueurActuel;
        this.fauteTourAvant = _fauteTourAvant;

        this.billeBlancheTombée = false;
        this.billeNoireTombée = false;
    }

    public void setPremierContact(Bille _b){
        this.premierContact = _b;
    }

    public void addBilleTombée(Bille _b){
        if (_b instanceof BilleBlanche){
            billeBlancheTombée = true;
        }else if (_b instanceof BilleNoire){
            billeNoireTombée = true;
        }else{
            this.billesTombéesTour.add(_b);

            if (_b.getBilleCouleur().getEquipe() == joueurActuel.equipe){
                this.peutRejouer = true;
            }else{
                this.fauteCommise = true;
            }
        }
    }

    public ArrayList<Bille> getListeBillesTombées(){
        return billesTombéesTour;
    }

    public boolean isBilleBlancheTombée(){
        return billeBlancheTombée;
    }

    public boolean isBilleNoireTombée(){
        return billeNoireTombée;
    }

    public boolean isFauteCommise(){
        return fauteCommise;
    }

    public boolean peutRejouer(){
        return ((fauteTourAvant || peutRejouer) && !fauteCommise);
    }

}
