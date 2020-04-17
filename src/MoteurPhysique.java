import java.util.ArrayList;
import java.util.Collections;

public class MoteurPhysique{

    public ArrayList<Element> listeElements;
    
    /**
     * Determine si le tour est terminé. Le tour est terminé lorsque toutes les
     * billes ont une vitesse inférieure à une vitesse de référence.
     * @return True si le tour est considéré comme terminé.
     */
    public boolean tourEstTerminé(){
        for (Element e : listeElements)
            if (e instanceof Bille)
                if (((Bille) e).estEnMouvement())
                    return false;
        return true;
    }


    /**
     * Gère la physique du tour actuel. La méthode quitte la boucle lorsque le
     * tour est considéré comme terminé. 
     *
     * Prend en paramètre le Tir. Puis simule le tour à partir de ce tir.
     * @param tir Objet tir associée à la décision de tir du joueur. C'est à
     * partir de cet objet Tir que tous les mouvements vont découler.
     * @return Les informations importantes du tour. Présence de faute et la
     * liste des billes qui sont tombées dans les poches (trou).
     */
    public DescriptionTour executerTour(Tir tir){
        DescriptionTour desc = new DescriptionTour();
        effectuerTir(tir);
        while(!tourEstTerminé()){
            //TODO : determiner le temps réél entre chaque tour de boucle.
            actualisation(1);
            traiterCollisions(determinerCollisions());
        }
        return desc;
    }

    /**
     * TODO
     * Gère le tir et donne la vitesse initale à la bille blanche.
     */
    public void effectuerTir(Tir tir){
        
    }

    /**
     * Appelle la méthode actualiser() pour toutes les billes. Cela permet
     * d'actualiser la cinématique des billes.
     * @param dt temps en seconde depuis la dernière actualisation.
     */
    public void actualisation(int dt){
        for (Element e : listeElements)
            if (e instanceof Bille)
                ((Bille) e).actualiser(dt);
    }


    /**
     * Trouve les collisions entre les elements et génère une liste de Contact. 
     *
     * Les collisions ne viennent que des billes en mouvement. Pour gagner un
     * peu de temps, les tests de contact sont fait uniquement pour les billes
     * en mouvement avec le reste des élements.
     * @return Une liste de contact.
     */
    public ArrayList<Contact> determinerCollisions(){
        ArrayList<Contact> listeContacts = new ArrayList<Contact>();
        for (Element e : listeElements)
            if (e instanceof Bille){
                Bille eb = (Bille) e;
                if (eb.estEnMouvement()){
                    for (Element et : listeElements){
                        Contact c = et.recoitContact(eb);
                        if (c != null)
                            listeContacts.add(c);
                    }
                }
            }
        return listeContacts;
    }

    /**
     * Traite les collisions à partir des objets Contact générés par
     * determinerCollisions().
     *
     * Les collisions doivent être traitées dans l'ordre. Pour faire cela,
     * l'objet Contact implémente l'interface Comparable. L'ordre est
     * important, si une bille est considérée comme en contact avec deux autres
     * billes alors il faut traiter le contact le plus profond en premier.
     * @param listeContacts Les contacts qu'il faut traiter.
     */ 
    public void traiterCollisions(ArrayList<Contact> listeContacts){
        Collections.sort(listeContacts);
        for (Contact c : listeContacts)
            c.faireContact();

    }


}
