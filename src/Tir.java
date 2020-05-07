public class Tir{
    protected boolean doitTirer;
    protected Vecteur tir;

    public Tir(Vecteur _tir){
        tir = _tir;
    }
    
    public Vecteur getVitesse(){
        return tir;
    }

}
