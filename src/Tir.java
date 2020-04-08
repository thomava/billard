public class Tir{
    protected boolean DoitTirer;
    protected Vecteur Position;
    protected Vecteur Force;

    public Tir(int x, int y, Bille BilleBlanche){
      Position = BilleBlanche.position;
    }

}
