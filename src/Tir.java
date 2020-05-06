public class Tir{
    protected boolean doitTirer;
    protected Vecteur tir;

    public Tir(Vecteur _tir){
        tir = _tir;
    }
    /*
     * TODO : Voir comment est utilisé vitesse avec thomas .
     */
    public Vecteur getVitesse(){
        return vitesse.mul(-1);
    }

}
