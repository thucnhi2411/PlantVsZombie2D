import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Pea.java <p>
 * 
 * A class for balls shot from cannon in PlantVsZombie. <p>
 */
public class Pea extends GCompound implements Runnable {
    // constants
    private static final double DELAY = 30;
    // instance variables
    private PlantVsZombie game; // the main game
    private boolean isAlive = true; // whether the pea die

    /** the constructor, create the ball */
    public Pea(PlantVsZombie game) {
        // save the parameters in instance variables
        this.game = game;
        

        // create the ball centered at the local origin
        GOval ball = new GOval(-20/2,-20/2,20, 20);
        add(ball);
        ball.setFilled(true);
        ball.setFillColor(Color.GREEN);  
    }

    /** the run method, to animate the ball */
    public void run(){
        while(isAlive){
            oneTimeStep();
            pause(DELAY);
        }
        disappear();
    }
    
    private void oneTimeStep(){
        movePolar(20,0);
        game.checkCollision(this);
    }
    
    /** stop the animation */
    public void die() {
        isAlive = false;
    }
    
    // the pea disappears
    private void disappear(){
        removeAll();
    }
}