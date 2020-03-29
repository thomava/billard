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
     */
    public void executerTour(){
        while(!tourEstTerminé()){
            actualisation(0.1);
            traiterCollisions(determinerCollisions());
        }
    }


    /**
     * Appelle la méthode actualiser() pour toutes les billes. Cela permet
     * d'actualiser la cinématique des billes.
     * @param dt temps en seconde depuis la dernière actualisation.
     */
    public void actualisation(double dt){
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
                        Contact c = eb.testContact(et);
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
