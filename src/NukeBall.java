import java.awt.Color;

public class NukeBall extends Ball {

    
    public NukeBall(int x, int y, int cx, int cy, int speed, int size, Color color){
        super(x, y, cx, cy, speed, size, color);
    }

    public void ballEffect(Paddles aPaddles){
        System.exit(0);
    }
}
