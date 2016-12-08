import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Pea.java <p>
 * 
 * A class for pea shot from peashooter in PlantVsZombie. <p>
 */
public class Pea extends GCompound implements Runnable {
    // constants
    private static final double DELAY = 30;
    // instance variables
    private PlantVsZombie game; // the main game
    private boolean isAlive = true; // whether the pea die

    /** 
     * The constructor, create the pea
     * @param   game    the game PlantVsZombie
     */
    public Pea(PlantVsZombie game) {
        // save the parameters in instance variables
        this.game = game;

        // create the ball centered at the local origin
        GOval pea = new GOval(-20/2,-20/2,20, 20);
        add(pea);
        pea.setFilled(true);
        pea.setFillColor(Color.GREEN);  
    }

    /** the run method, to animate the ball */
    public void run(){
        while(isAlive){
            oneTimeStep();
            pause(DELAY);
        }
        disappear();
    }

    // control the animation
    private void oneTimeStep(){
        movePolar(20,0); // move the pea
        game.checkCollision(this); // check the collision with the zombie
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