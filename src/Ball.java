/**
 * Class code courtesy of Kevin Olson from 
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-2d-pong
 * with modifications by Romina Martin (@QueenRominaMartin on GitHub)
 */

import java.awt.*;

public class Ball {
    
    // Declaring all the needed instance variables for the Ball class
    private int ballX, ballY, ballCX, ballCY, ballSize, ballSpeed;
    private Color ballColor;

    /**
     * The constructor for the Ball class. Sets all the instance variables for this class.
     * @param x - the starting x position of the ball
     * @param y - the starting y position of the ball
     * @param cx - the change in X position of the ball per frame
     * @param cy - the change in Y Position of the ball per frame
     * @param size - the size of the ball in pixels
     * @param color - the color of the ball
     */
    public Ball(int x, int y, int cx, int cy, int speed, int size, Color color){
        ballX = x;
        ballY = y;
        ballCX = cx;
        ballCY = cy;
        ballSpeed = speed;
        ballSize = size;
        ballColor = color;
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
     * A parent method to represent the effect the balls have on the paddles when they come into contact
     * @param aPaddles - the Paddle object to apply the effects to
     */
    public void ballEffect(Paddles aPaddles){
        
    }

    /**
     * A getter method that
     * @return the current X position of the ball
     */
    public int getX(){
        return ballX;
    }

    /**
     * A getter method that
     * @return the current Y position of the ball.
     */
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


    /**
     * A getter method that
     * @return the current size of the ball
     */
    public int getSize(){
        return ballSize;
    }

    /**
     * A getter method that
     * @return the current speed of the ball
     */
    public int getSpeed(){
        return ballSpeed;
    }

    /**
     * A setter method that sets the X position of the ball
     * @param xPos - the new X Coordinate position that the ball will be set to.
     */
    public void setX(int xPos){
        ballX = xPos;
    }

    /**
     * A setter method that sets the Y position of the ball
     * @param yPos - the new Y Coordinate position that the ball will be set to.
     */
    public void setY(int yPos){
        ballY = yPos;
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


}
