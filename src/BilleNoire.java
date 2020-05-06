import java.awt.Color;

public class BilleNoire extends Bille{

    public BilleNoire(Vecteur position, double rayon, double masse){
        super(position, null, rayon, masse);
    }

    @Override
    protected Color getCouleur(){
        return Color.black;
    }

}

