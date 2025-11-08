import java.awt.*;

public class TeleportBall extends Ball {
    
    public TeleportBall(int x, int y, int cx, int cy, int speed, int size, Color color){
        super(x, y, cx, cy, speed, size, color);
    }

    public void ballEffect(Paddles aPaddle){
        aPaddle.setPosition(aPaddle.getX(), aPaddle.setRandomPosition());
    }
}
