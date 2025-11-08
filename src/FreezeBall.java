import java.awt.*;

public class FreezeBall extends Ball {

    
    public FreezeBall(int x, int y, int cx, int cy, int speed, int size, Color color){
        super(x, y, cx, cy, speed, size, color);
    }

    public void ballEffect(Paddles aPaddles){
        aPaddles.decreaseSpeed(2);
        aPaddles.setColor(Color.CYAN);
    }

}
