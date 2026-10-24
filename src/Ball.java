/**
 * Class code courtesy of Kevin Olson from 
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-2d-pong
 * with modifications by Romina Martin (@QueenRominaMartin on GitHub)
 */

import java.awt.*;

public class Ball {

    public static final int BASE_BALL_SPEED = 9;
    
    // Declaring all the needed instance variables for the Ball class
    private int ballX, ballY, ballCX, ballCY, ballSize;
    private Color ballColor;

    /**
     * The constructor for the Ball class. Sets all the instance variables for this class.
     * @param x - the starting x position of the ball
     * @param y - the starting y position of the ball
     * @param size - the size of the ball in pixels
     * @param color - the color of the ball
     */
    public Ball(int x, int y, int size, Color color){
        ballX = x;
        ballY = y;
        ballSize = size;
        ballColor = color;

        ballCX = BASE_BALL_SPEED;
        ballCY = BASE_BALL_SPEED;
    }

    /**
     * This method paints a Graphics object called ballGraphics that will represent the ball on the game screen
     * @param ballGraphics - the Graphics object to represent the ball.
     */
    public void paint(Graphics ballGraphics){
        // Sets the color of the Graphics object to the instance variable ballColor
        ballGraphics.setColor(ballColor);
        // Creates an oval shape of the Graphics object at the coordinates assigned by the instance variables, 
        // with the size being that of which assigned by the instance variables
        ballGraphics.fillOval(ballX, ballY, ballSize, ballSize);

    }

    /**
     * This method moves the ball by the CX and CY variables, which are the X and Y units the ball moves per frame.
     */
    public void moveBall(){
        ballX += ballCX;
        ballY += ballCY;
    }

    // Multiplies the value of ballCX so it reverses the X direction the ball moves in
    public void reverseX(){
        ballCX = ballCX * -1;
    }

    // Multiplies the value of ballCY so it reverses the Y direction the ball moves in
    public void reverseY(){
        ballCY = ballCY * -1;
    }

    /**
     * This method uses the random method of the Math class to assign the ball a random Y coordinate
     * @return the random Y coordinate now assigned to the ball.
     */
    public int randomYCoord(){
        int randomY = (int)(Math.random() * (PongGame.WINDOW_HEIGHT-50));
        return randomY;
    }

    /**
     * this method performs calculations to determine if the ball is touching a top or bottom edge of the screen
     * if so, it calls the reverseY() method to reverse the Y direction the ball moves in so it stays on the screen.
     * @param top - the Y-coordinate of the top of the screen
     * @param bottom - the Y-coordinate of the bottom of the screen
     */
    public void bounceOffEdges(int top, int bottom){

        // Detects if the y values are approaching the top or bottom of the screen
        // If so, it calls the reverseY() method to reverse the Y direction of the ball
        if(ballY > bottom - (2*ballSize)){
            reverseY();
        } else if(ballY < top + (ballSize / 2)){
            reverseY();
        }
    }

    /**
     * Resets the ball
     * Intended for code use after a goal is scored
     */
    public void reset(){
        ballX = 960;
        ballY = 540;
        ballCX = BASE_BALL_SPEED;
        ballCY = BASE_BALL_SPEED;
    }

    /**
     * Increases the speed of the ball by adding onto the existing speed.
     * @param xSpeed - the x speed that should be added to the cX speed
     * @param ySpeed - the Y speed that should be added to the cY speed
     */
    public void setSpeed(int xSpeed, int ySpeed){
        ballCX = ballCX + xSpeed;
        ballCY = ballCY + ySpeed;
    }

    /**
     * Decreases the speed of the ball by subtracting from the existing speed.
     * @param xDecrease - the integer to subtract from the current x speed
     * @param yDecrease - the integer to subtract from the current y speed.
     */
    public void decreaseSpeed(int xDecrease, int yDecrease){
        ballCX = ballCX - xDecrease;
        ballCY = ballCY - yDecrease;
    }

    public void increaseSpeed(int xIncrease, int yIncrease){
        ballCX = ballCX + xIncrease;
        ballCY = ballCY + yIncrease;
    }

    /**
     * A parent method to represent the effect the balls have on the paddles when they come into contact
     * @param aPaddles - the Paddle object to apply the effects to
     */
    public void ballEffect(Paddles aPaddles){
        
    }

    public int getX(){
        return ballX;
    }

    public int getY(){
        return ballY;
    }

    public int getCX(){
        return ballCX;
    }

    public int getCY(){
        return ballCY;
    }

    public void setCX(int cx){
        ballCX = cx;
    }

    public void setCY(int cy){
        ballCY = cy;
    }

    public int getSize(){
        return ballSize;
    }

    public void setX(int xPos){
        ballX = xPos;
    }

    public void setY(int yPos){
        ballY = yPos;
    }



}
