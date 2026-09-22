/**
 * Class code courtesy of Kevin Olson from 
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-2d-pong
 * with modifications by Romina Martin (@QueenRominaMartin on GitHub)
 */

 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

import javax.swing.*;
 
 
 public class App {
     
 
     static JFrame mainWindow = new JFrame("Pong 2: ULTIMATE XTREME BONUS EDITION 4");
     public static void main(String[] args) throws Exception {
 
         //final JFXPanel fxPanel = new JFXPanel();
 
         // Sets the main window to close when user clicks the close button.
         mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
         // Sets the size of the game window, the two parameters being the height and width of the window
         mainWindow.setSize(1940, 1100);
 
         // Creates a new instance variable of the PongGame object, and then adds it to the main window to be displayed.
         PongGame pong = new PongGame();
         mainWindow.add(pong);
 
         // Sets the visibility of the window, whether the user can see it or not, to true, so the user can see the window.
         mainWindow.setVisible(true);
 
         // Creates a Timer object called gameTimer that redraws the pong game screen every 33 milliseconds, about 30 times per second
         // This is so the screen can constantly update and use game logic to accurately display where all game objects are.
         Timer gameTimer = new Timer(33, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent event){
 
                 // Calls the gameLogic method to perform all the calculations needed for the game
                 pong.gameLogic();
                 // Repaints the screen so it is updated.
                 pong.repaint();
             }
         });
 
         // Starts the timer object in order to be able to update the frame
         gameTimer.start();
 
     }
 }
 