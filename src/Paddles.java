/**
 * Class code courtesy of Kevin Olson from 
 * https://kevinsguides.com/guides/code/java/javaprojs/simple-2d-pong
 * with modifications by Romina Martin (@QueenRominaMartin on GitHub)
 */

import java.awt.Color;
import java.awt.Graphics;

public class Paddles {
    
    // The instance variables for the Paddles class
    private int paddleHeight, paddleX, paddleY, paddleSpeed;
    private Color paddleColor;

    //Constant variable for the width of the paddle
    static final int PADDLE_WIDTH = 30;

    /**
     * The constructor for the Paddles class
     * @param height - the height of the paddle
     * @param x - the starting X coordinate of the paddle
     * @param y - the starting Y coordinate of the paddle
     * @param speed - The speed of the paddle, the number of pixels it can move per frame
     * @param color - The color of the paddle
     */
    public Paddles(int height, int x, int y, int speed, Color color){
        paddleHeight = height;
        paddleX = x;
        paddleY = y;
        paddleSpeed = speed;
        paddleColor = color;
    }

    /**
     * This method paints the paddles on the screen.
     * @param graphics - the graphics object to paint the paddles onto
     */
    public void paint(Graphics graphics){
        // Paints the paddles on the Graphics object, with the color, position, width and height all being assigned from the instance variables
        graphics.setColor(paddleColor);
        graphics.fillRect(paddleX, paddleY, PADDLE_WIDTH, paddleHeight);
    }

    /**
     * Moves the paddles towards the target Y position every frame
     * @param moveToY - centered position that the paddle should move towards
     */
    public void moveTowards(int moveToY){

        // Calculates the center of the paddle by taking the Y, the top of the paddle, and adding the height divided by 2
        int centerY = paddleY + paddleHeight / 2;

        if(Math.abs(centerY - moveToY) > paddleSpeed){
            if(centerY > moveToY){
                paddleY -= paddleSpeed;
            } else if(centerY < moveToY){
                paddleY += paddleSpeed;
            }
        }
        
    }

    /**
     * This method checks whether or not the paddle and ball have collided with each other.
     * @param ball - the Ball object of which the method will check if they are collided
     * @return - this method returns true if the ball and paddle are collided.
     */
    public boolean checkCollision(Ball ball){
        int rightX = paddleX + PADDLE_WIDTH;
        int bottomY = paddleY + paddleHeight;

        if(ball.getX() > (paddleX - ball.getSize()) && ball.getX() < rightX){
            if(ball.getY() > paddleY && ball.getY() < bottomY){
                return true;
            }
        }

        return false;
    }


    public int getX(){
        return paddleX;
    }

    public int getSpeed(){
        return paddleSpeed;
    }

    public void setPosition(int x){
        paddleX = x;
        paddleY = 400;
    }

    public void setPosition(int x, int y){
        paddleX = x;
        paddleY = y;
    }

    public void setSpeed(int speed){
        paddleSpeed = speed;
    }

    public void setColor(Color color){
        paddleColor = color;
    }

    /**
     * This method uses the random method of the Math class to assign a random position to the paddle
     * @return the random Y position the paddle is now set to
     */
    public int setRandomPosition(){
        int randomY = (int)(Math.random() * (PongGame.WINDOW_HEIGHT - paddleHeight));
        return randomY;
    }

    public void decreaseSpeed(int decrease){
        paddleSpeed = paddleSpeed - decrease;
    }

    public void increaseSpeed(int increase){
        paddleSpeed = paddleSpeed + increase;
    }

    
}
