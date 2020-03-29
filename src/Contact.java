
public class Contact implements Comparable<Contact>{

    private Bille e1;
    private Element e2;
    private Vecteur norm;
    private Vecteur tan;
    private double profondeurContact;

    public Contact(Bille _e1, Element _e2, Vecteur _norm, double _profondeurContact){
        e1 = _e1;
        e2 = _e2;
        norm = _norm;
        tan = norm.vecteurNormal();
        profondeurContact = _profondeurContact;
    }

    public void faireContact(){
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

            double masseFactor11 = e1.getMasse() - b2.getMasse() / (e1.getMasse() + b2.getMasse());
            double masseFactor12 = 2 * b2.getMasse() / (e1.getMasse() + b2.getMasse());
            double masseFactor21 = 2 * e1.getMasse() / (e1.getMasse() + b2.getMasse());
            double masseFactor22 = -masseFactor11;

            newVitNorm1 = masseFactor11 * vitNorm1 + masseFactor12 * vitNorm2; 
            newVitNorm2 = masseFactor21 * vitNorm2 + masseFactor22 * vitNorm2; 

            e1.setVitesse(norm.mul(newVitNorm1).add(tan.mul(vitTan1)));
            b2.setVitesse(norm.mul(newVitNorm2).add(tan.mul(vitTan2)));
        }else{
            newVitNorm1 = - vitNorm1; 
            newVitNorm2 = 0;
            e1.setVitesse(norm.mul(newVitNorm1).add(tan.mul(vitTan1)));
        }
    }

    public int compareTo(Contact c){
        return (int)(profondeurContact - c.profondeurContact);
    }

}
