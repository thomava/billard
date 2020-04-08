public class Tire{
    protected boolean DoitTirer;
    protected Vecteur Position;
    protected Vecteur Force;

    public Tire(int x, int y, Bille BilleBlanche){
      Position = BilleBlanche.position;
    }

}
