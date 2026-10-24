import java.awt.*;

public class FreezeBall extends Ball {

    private static Color freezeColor = Color.BLUE;
    
    public FreezeBall(int x, int y, int cx, int cy, int speed, int size){
        super(x, y, size, freezeColor);
    }

    public void ballEffect(Paddles aPaddles){
        aPaddles.decreaseSpeed(2);
        aPaddles.setColor(Color.CYAN);
    }

}
