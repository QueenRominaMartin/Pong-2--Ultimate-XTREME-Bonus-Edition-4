import java.awt.*;

public class TeleportBall extends Ball {

    private static Color teleportColor = Color.ORANGE;
    
    public TeleportBall(int x, int y, int cx, int cy, int speed, int size){
        super(x, y, size, teleportColor);
    }

    public void ballEffect(Paddles aPaddle){
        aPaddle.setPosition(aPaddle.getX(), aPaddle.setRandomPosition());
    }
}
