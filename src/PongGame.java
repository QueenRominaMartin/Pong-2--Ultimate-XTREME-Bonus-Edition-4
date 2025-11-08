/**
 * Class code courtesy of Kevin Olson from 
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-2d-pong
 * with modifications by Romina Martin (@QueenRominaMartin on GitHub)
 * 
 * Additional modified code from Kevin Olson from
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-pong-extras
 */

import javax.swing.JPanel;

import java.awt.*;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class PongGame extends JPanel implements MouseMotionListener{
    
    static final int WINDOW_WIDTH = 1920, WINDOW_HEIGHT = 1080;

    private Ball gameBall;
    private Paddles userPaddle, comPaddle;
    private int userMouseY;
    private int userScore, comScore;
    private int bounceCount;
    private int turnCount;
    private final Font font;

    /*
     * Constructor for PongGame class that creates the gameball object
     */
    public PongGame(){
        gameBall = new Ball(960, 540, 9, 9, 16, 35, Color.WHITE); // creates a new Ball object
        userPaddle = new Paddles(300, 250, 400, 15, Color.MAGENTA); // creates a new Paddles object called userPaddle
        comPaddle = new Paddles(300, 1670, 400, 15, Color.PINK); // creates a new Paddles object called comPaddle

        userMouseY = 0;
        addMouseMotionListener(this);

        userScore = 0; comScore = 0;

        bounceCount = 0;

        // This variable represents how many turns have elapsed in the game. A turn is the playtime between scores
        turnCount = 0;

        font = new java.awt.Font("Comic Sans", java.awt.Font.BOLD, 60);
    }

    /**
     * This method updates all of the graphics on the screen to what is needed.
     * @param graphics - The Graphics object of which we will be updating all of the graphics for.
     */
    public void paintComponent(Graphics graphics){

        // Sets the color to black, then fills in a rectangle with black to become the background of the screen
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        graphics.setColor(Color.WHITE);

        graphics.setFont(font);
        graphics.drawString("SCORE - USER : " + userScore + "    COM : " + comScore, 520, 100);

        // Draws the gameball on the graphics object
        gameBall.paint(graphics);

        // Draws the paddles on the graphics object
        userPaddle.paint(graphics);
        comPaddle.paint(graphics);
    }

    /**
     * This method will be called once per frame to update the game with essential game logic
     * i.e. moving the Ball and paddles, and keeping track of scores.
     */
    public void gameLogic(){

        // Calls the moveBall method to move the ball on the screen
        gameBall.moveBall();

        // calls this method to check if the ball needs to bounce off of the edge of the screen
        gameBall.bounceOffEdges(0, WINDOW_HEIGHT);

        // these methods move both the paddles
        // the user paddle towards the Y position of the User's cursor
        // the computer paddle towards the Y position of the ball.
        userPaddle.moveTowards(userMouseY);
        comPaddle.moveTowards(gameBall.getY());

        // These if statements check if the ball has collided with either paddle
        // If so it makes the balls bounce off of the paddle, increasing the bounce count.
        if (userPaddle.checkCollision(gameBall) == true) {
            playSound("src/Sfx/pongBounce.mp3");
            gameBall.reverseX();
            gameBall.setX(userPaddle.getX() + Paddles.PADDLE_WIDTH + 1); // This code fixes the bug that occurs when the ball hits the paddle at a 45 degree angle. From https://kevinsguides.com/guides/code/java/javaprojs/simple-pong-extras
            gameBall.ballEffect(userPaddle);
            bounceCount++;
        }
        if (comPaddle.checkCollision(gameBall) == true) {
            playSound("src/Sfx/pongBounce.mp3");
            gameBall.reverseX();
            if (gameBall.getSize() != 40) {
                gameBall.setX(comPaddle.getX() - (Paddles.PADDLE_WIDTH + 1)); // This code fixes the bug that occurs when the ball hits the paddle at a 45 degree angle. From https://kevinsguides.com/guides/code/java/javaprojs/simple-pong-extras
            } else if(gameBall.getSize() == 40){
                gameBall.setX(comPaddle.getX() - (Paddles.PADDLE_WIDTH + 6));
            }
            gameBall.ballEffect(comPaddle);
            bounceCount++;
        }

        // If the number of times the the ball has bounced off the paddles reaches 5
        // The count is reset, and the balls speed is increased via the method call
        if(bounceCount == 6){
            gameBall.setSpeed(gameBall.getCX() + 1, gameBall.getCY() + 1);
            bounceCount = 0;
        }

        /* These if statements check if the ball has gone off either the left or right ends of the screen */
        // if it goes off the left side of the screen, that means the computer has scored, increases comScore, and resets the screen
        if(gameBall.getX() < 0){
            playSound("src/Sfx/LosePoint.mp3");
            comScore++;
            reset();
        } 
        // If it goes off of the right side of the screen, that means the player scored, increased userScore, and resets the screen.
        else if(gameBall.getX() > WINDOW_WIDTH){
            playSound("src/Sfx/GOAL!.mp3");
            userScore++;
            reset();
        }

    }

    /*
     * This method resets the screen by resetting the X and Y coordinates of the ball to the center of the screen
     * and resetting the bounceCount to 0
     * It also resets the paddle positions back to the center
     */
    public void reset(){
        try{
            Thread.sleep(250);
        } catch(Exception exc){
            exc.printStackTrace();
        }
        gameBall.setX(960);
        gameBall.setY(540);
        //gameBall.setCX(9);
        //gameBall.setCY(9);
        userPaddle = new Paddles(300, 250, 400, 15, Color.MAGENTA); // creates a new Paddles object called userPaddle
        comPaddle = new Paddles(300, 1670, 400, 15, Color.PINK); // creates a new Paddles object called comPaddle
        bounceCount = 0;
        turnCount++;
        if(turnCount > 3){
            powerUpLogic();
        }
        gameBall.setY(gameBall.randomYCoord());
        
    }

    /**
     * This method performs all the logic for spawning power balls
     * It calculates a random number between 1 and 4
     * And depending on the number produced, the game either spawns a power ball
     * or a regular ball
     */
    public void powerUpLogic(){
        int chance;
        chance = (int)(Math.random() * 12 + 1);
        if(chance == 1 || chance == 2){
            playSound("src/Sfx/freeze.mp3");
            gameBall = new FreezeBall(960, 540, gameBall.getCX() / 2, gameBall.getCY() / 2, 8, 40, Color.BLUE);
        } else if(chance == 3 || chance == 4){
            playSound("src/Sfx/explosion.mp3");
            gameBall = new FireBall(960, 540, gameBall.getCX() * 2, gameBall.getCY() *2, 65, 30, Color.RED);
        } else if(chance >= 5 && chance <= 9){
            gameBall = new Ball(960, 540, gameBall.getCX(), gameBall.getCY(), 16, 35, Color.WHITE);
        } else if(chance == 10){
            playSound("src/Sfx/bombsAway.mp3");
            gameBall = new NukeBall(960, 540, gameBall.getCX() + 3, gameBall.getCY() + 3, 20, 100, Color.DARK_GRAY);
        } else if(chance == 11 || chance == 12){
            gameBall = new TeleportBall(960, 540, gameBall.getCX(), gameBall.getCY(), 16, 30, Color.ORANGE);
        }
    }


    /**
     * This method is used to play a MP3 sound file.
     * @param filePath - the String that contains the path to the MP3 file that will be played
     */
    public void playSound(String filePath) {
        String sound = filePath;
        try {
            Media media = new Media(new File(sound).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
    }  

    /* These methods are to ensure the mouse tracking works for the program */
    @Override
    public void mouseDragged(MouseEvent event){

    }
    @Override
    public void mouseMoved(MouseEvent event){
        userMouseY = event.getY();
    }

}
