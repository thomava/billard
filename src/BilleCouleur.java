import java.awt.Color;

/** Fait le lien entre un ensemble de bille d'une couleur et une équipe.
 *  @author Thomas, Léa, Diane
 */
public class BilleCouleur{

    private Color couleur;
    private Equipe equipe;

    public BilleCouleur(Color _couleur){
       this.couleur = _couleur; 
    }

    public void setEquipe(Equipe _equipe){
        this.equipe = _equipe;
    }

    public Equipe getEquipe(){
        return this.equipe;
    }

    public Color getCouleur(){
        return this.couleur;
    }

}
