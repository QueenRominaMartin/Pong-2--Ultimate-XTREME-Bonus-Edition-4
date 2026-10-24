import java.awt.Color;

public class FireBall extends Ball{

    private static Color fireColor = Color.RED;
    
    public FireBall(int x, int y, int cx, int cy, int speed, int size){
        super(x, y, size, fireColor);
    }


    public void ballEffect(Paddles aPaddle){
        aPaddle.increaseSpeed(1);;
        aPaddle.setColor(Color.ORANGE);
    }

}
