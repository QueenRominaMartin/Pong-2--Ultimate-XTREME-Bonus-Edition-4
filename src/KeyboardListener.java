import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter{
    
    @Override 
    public void keyPressed(KeyEvent keyPressed) {

        int pressedKey = keyPressed.getKeyCode();

        if(pressedKey == KeyEvent.VK_SPACE || pressedKey == KeyEvent.VK_ESCAPE) {
            PongGame.togglePause();
        }

    }
}
