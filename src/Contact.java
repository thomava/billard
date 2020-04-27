
public class Contact implements Comparable<Contact>{

    private Bille e1;
    private Element e2;
    private Vecteur norm;
    private Vecteur tan;
    private double profondeurContact; //distance "d'enfoncement" d'une bille dans l'autre

    // _norm est le vecteur unitaire orienté de e1 vers e2.
    public Contact(Bille _e1, Element _e2, Vecteur _norm, double _profondeurContact){
        e1 = _e1;
        e2 = _e2;
        norm = _norm;
        tan = norm.vecteurNormal();
        profondeurContact = _profondeurContact;
    }

    /**
     * Méthode appelée par le MoteurPhysique à la fin d'une actualisation pour
     * gérer les contacts entre les élements physiques.
     * @return e1 si elle est tombée dans un trou. Sinon, retourne null.
     */
    public Bille faireContact(){
        double vitTan1 = tan.produitScalaire(e1.getVitesse());
        double vitTan2 = 0;

        double vitNorm1 = norm.produitScalaire(e1.getVitesse());
        double vitNorm2 = 0;

        double newVitNorm1;
        double newVitNorm2;


        if (e2 instanceof Bille){
            Bille b2 = (Bille) e2;
            vitTan2 = tan.produitScalaire(b2.getVitesse());

            vitNorm2 = norm.produitScalaire(b2.getVitesse());

            double masseFactor11 = (e1.getMasse() - b2.getMasse()) / (e1.getMasse() + b2.getMasse());
            double masseFactor12 = 2 * b2.getMasse() / (e1.getMasse() + b2.getMasse());
            double masseFactor21 = 2 * e1.getMasse() / (e1.getMasse() + b2.getMasse());
            double masseFactor22 = -masseFactor11;

            newVitNorm1 = masseFactor11 * vitNorm1 + masseFactor12 * vitNorm2;
            newVitNorm2 = masseFactor21 * vitNorm1 + masseFactor22 * vitNorm2;

            e1.setVitesse(norm.mul(newVitNorm1).add(tan.mul(vitTan1)));
            b2.setVitesse(norm.mul(newVitNorm2).add(tan.mul(vitTan2)));

            //On fait en sorte que les billes ne se rentrent pas les une dans
            //les autres.
            e1.setPosition(e1.getPosition().add(norm.mul(-(profondeurContact/2 + 0.5))));
            b2.setPosition(b2.getPosition().add(norm.mul(+(profondeurContact/2 + 0.5))));
        }else if (e2 instanceof Terrain){
            newVitNorm1 = - vitNorm1;
            newVitNorm2 = 0;
            e1.setVitesse(norm.mul(newVitNorm1).add(tan.mul(vitTan1)));
        }else{
            //L'element de contact est un trou.
            e1.tomber();
            return e1;
        }
        return null;
    }

    public int compareTo(Contact c){
        return (int)(profondeurContact - c.profondeurContact);
    }

    public boolean equals(Object e){
        if (!(e instanceof Contact))
            return false;

        Contact c = (Contact) e;
        if ((this.e1 == c.e1 && this.e2 == c.e2) ||
            (this.e1 == c.e2 && this.e2 == c.e1))
            return true;

        return false;
    }

}
