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

    private boolean premièreBilleTombée;
    private Bille premièreBilleTombéeObjet;

    public DescriptionTour(boolean _fauteTourAvant, Joueur _joueurActuel){
        this.billesTombéesTour = new ArrayList<Bille>();
        this.fauteCommise = false;
        this.peutRejouer = false;
        this.joueurActuel = _joueurActuel;
        this.fauteTourAvant = _fauteTourAvant;

        this.billeBlancheTombée = false;
        this.billeNoireTombée = false;

        this.premièreBilleTombée = false;
    }

    /**
     * Méthode qui permet de renseigner le premier contact.
     */
    public void registerPremierContact(Bille _b){
        if (!(_b instanceof BilleBlanche))
        { 
            premierContact = _b;

            if (_b instanceof BilleNoire){
                this.fauteCommise = true;
            }
            else if (_b.getBilleCouleur().isEquipeSet()){
                System.out.println("check faute première collision");
                if (_b.getBilleCouleur().getEquipe() != joueurActuel.equipe){
                    this.fauteCommise = true; 
                }
            }
        }else{
            
        }
    }

    public boolean isPremierContactSet(){
        return (premierContact != null);
    }

    public void addBilleTombée(Bille _b){
        if (_b instanceof BilleBlanche){
            billeBlancheTombée = true;
            this.fauteCommise = true;
        }else if (_b instanceof BilleNoire){
            billeNoireTombée = true;
        }else{
            this.billesTombéesTour.add(_b);

            if (_b.getBilleCouleur().isEquipeSet()){
                if (_b.getBilleCouleur().getEquipe() == joueurActuel.equipe){
                    this.peutRejouer = true;
                }else{
                    this.fauteCommise = true;
                }
            }else{
                this.premièreBilleTombéeObjet = _b;
                this.premièreBilleTombée = true;
                this.peutRejouer = true;
            }
        }
    }

    public boolean isPremièreBilleTombée(){
        return premièreBilleTombée;
    }

    public Bille getPremièreBilleTombée(){
        return premièreBilleTombéeObjet;
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

    // Méthode à appeler une fois le tour finis, sinon la détermination des
    // règles sera mal effectuée.
    public boolean isFauteCommise(){
        //Si aucune bille n'est rentrée en contact avec la bille blanche alors
        //il y a forcement une faite.
        if (!isPremierContactSet()){
            fauteCommise = true;
        }
        return fauteCommise;
    }

    public Joueur getJoueurActuel(){
        return joueurActuel;
    }

    // Méthode à appeler une fois le tour finis, sinon la détermination des
    // règles sera mal effectuée.
    public boolean peutRejouer(){
        //Si aucune bille n'est rentrée en contact avec la bille blanche alors
        //il y a forcement une faite.
        if (!isPremierContactSet()){
            fauteCommise = true;
        }
        return ((fauteTourAvant || peutRejouer) && !fauteCommise);
    }

    @Override
    public String toString(){
        String str = "";

        str += "-----------------------------\n";
        str += "Tour numéro "+"TODO"+"\n";
        str += "Joueur du tour : "+joueurActuel+"\n";
        str += "Faute : "+fauteCommise+" | PeutRejouer "+peutRejouer+"\n";
        str += "Nbr billes tombées : "+billesTombéesTour.size()+" dont : \n";
        str += "BilleBlanche : "+billeBlancheTombée+" | BilleNoire : "+billeNoireTombée+"\n";
        str += "Premier tour où des billes tombent : "+premièreBilleTombée+"\n";
        str+= "-----------------------------\n";

        
        return str;
    }

}
