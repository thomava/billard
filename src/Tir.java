public class Tir{
    protected boolean doitTirer;
    protected Vecteur position;
    protected Vecteur point;

    public Tir(int x, int y, Bille billeBlanche){
        position = billeBlanche.position;
        point = new Vecteur(x, y);
    }

    public Vecteur getVitesse(){
        return position.add(point.mul(-1));
    }

}
