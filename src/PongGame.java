/**
 * Class code courtesy of Kevin Olson from 
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-2d-pong
 * with modifications by Romina Martin (@QueenRominaMartin on GitHub)
 * 
 * Additional modified code from Kevin Olson from
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-pong-extras
 */

import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class PongGame extends JPanel implements MouseMotionListener{
    static final int WINDOW_WIDTH = 1920, WINDOW_HEIGHT = 1080;

    //Sound Clips for Game
    private static final Clip pongBounceSound = Sound.createSoundClip("src/Sfx/pongbounce.wav");
    private static final Clip losePointSound = Sound.createSoundClip("src/Sfx/losepoint.wav");
    private static final Clip goalSound = Sound.createSoundClip("src/Sfx/goal.wav");

    //Game Elements
    private Ball gameBall;
    private Paddles userPaddle, comPaddle;

    //Game tracking variables
    private int userMouseY;
    private int userScore, comScore;
    private int bounceCount;
    private int turnCount;

    private static boolean paused;

    //Other Varaibles
    private final Font font;


    public PongGame(){
        gameBall = new Ball(960, 540, 35, Color.WHITE); // creates a new Ball object
        userPaddle = new Paddles(300, 250, 400, 15, Color.MAGENTA); // creates a new Paddles object called userPaddle
        comPaddle = new Paddles(300, 1670, 400, 15, Color.PINK); // creates a new Paddles object called comPaddle

        userMouseY = 0;
        addMouseMotionListener(this);
        userScore = 0; comScore = 0;
        bounceCount = 0;
        // This variable represents how many turns have elapsed in the game. A turn is the playtime between scores
        turnCount = 0;

        paused = false;

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

        if(paused == true) {
            graphics.setColor(Color.WHITE);

            graphics.fillRect(750, 250, 50, 500);

            graphics.fillRect(1050, 250, 50, 500);
        }
    }

    /**
     * Method that is called every 33 milliseconds and performs all the game logic from moving the elements on the screen
     * to updating the scores and keeping track of if the game is paused or not.
     */
    public void gameLogic() {

        if(paused == false) {
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
                Sound.playSoundClip(pongBounceSound);
                gameBall.reverseX();
                gameBall.setX(userPaddle.getX() + Paddles.PADDLE_WIDTH + 1); // This code fixes the bug that occurs when the ball hits the paddle at a 45 degree angle. From https://kevinsguides.com/guides/code/java/javaprojs/simple-pong-extras
                gameBall.ballEffect(userPaddle);
                bounceCount++;
            }
            if (comPaddle.checkCollision(gameBall) == true) {
                Sound.playSoundClip(pongBounceSound);
                gameBall.reverseX();
                if (gameBall.getSize() != 40) {
                    gameBall.setX(comPaddle.getX() - (Paddles.PADDLE_WIDTH + 1)); // This code fixes the bug that occurs when the ball hits the paddle at a 45 degree angle. From https://kevinsguides.com/guides/code/java/javaprojs/simple-pong-extras
                } else if(gameBall.getSize() == 40){
                    gameBall.setX(comPaddle.getX() - (Paddles.PADDLE_WIDTH + 6));
                }
                gameBall.ballEffect(comPaddle);
                bounceCount++;
            }

            // If the number of times the the ball has bounced off the paddles reaches 4
            // The count is reset, and the balls speed is increased via the method call
            if(bounceCount == 4){
                gameBall.setSpeed(gameBall.getCX() + 1, gameBall.getCY() + 1);
                bounceCount = 0;
            }

            /* These if statements check if the ball has gone off either the left or right ends of the screen */
            // if it goes off the left side of the screen, that means the computer has scored, increases comScore, and resets the screen
            if(gameBall.getX() < 0){
                Sound.playSoundClip(losePointSound);
                comScore++;
                reset();
            } 
            // If it goes off of the right side of the screen, that means the player scored, increased userScore, and resets the screen.
            else if(gameBall.getX() > WINDOW_WIDTH){
                Sound.playSoundClip(goalSound);
                userScore++;
                reset();
            }
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
        gameBall.reset();
        userPaddle = new Paddles(300, 250, 400, 15, Color.MAGENTA); // creates a new Paddles object called userPaddle
        comPaddle = new Paddles(300, 1670, 400, 15, Color.PINK); // creates a new Paddles object called comPaddle
        bounceCount = 0;
        turnCount++;
        if(turnCount > 3 && ((turnCount - 2) / 3)%3 == 0){
            powerUpLogic();
        }
        //gameBall.setY(gameBall.randomYCoord());
    }

    /**
     * This method performs all the logic for spawning power balls
     * It calculates a random number between 1 and 4
     * And depending on the number produced, the game either spawns a power ball
     * or a regular ball
     */
    public void powerUpLogic(){
        int chance = (int)(Math.random() * 5 + 1);

        switch (chance) {
            case 1:
                gameBall = new FreezeBall(960, 540, gameBall.getCX() / 2, gameBall.getCY() / 2, 8, 40);
                break;
            case 2:
                gameBall = new FireBall(960, 540, gameBall.getCX() * 2, gameBall.getCY() * 2, 65, 30);
                break;
            case 3:
                gameBall = new TeleportBall(960, 540, gameBall.getCX(), gameBall.getCY(), 16, 30);
                break;
            case 4:
                gameBall = new FireBall(960, 540, gameBall.getCX() + 3, gameBall.getCY() + 3, 20, 30);
                break;
            case 5:
                gameBall = new Ball(960, 540, 35, Color.WHITE);
                break;
            default:
                gameBall = new Ball(960, 540, 35, Color.WHITE);
                break;
        }
    } 

    public static void togglePause() {
        paused = !paused;
    }

    /*
     * KEYBOARD TRACKING FUNCTIONS
    */

    /* These methods are to ensure the mouse tracking works for the program */
    @Override
    public void mouseDragged(MouseEvent event){

    }
    @Override
    public void mouseMoved(MouseEvent event){
        userMouseY = event.getY();
    }


}
