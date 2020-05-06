import java.awt.Color;

public class BilleBlanche extends Bille{

    public BilleBlanche(Vecteur position, double rayon, double masse){
        super(position, null, rayon, masse);
    }


    @Override
    protected Color getCouleur(){
        return Color.white;
    }

}

