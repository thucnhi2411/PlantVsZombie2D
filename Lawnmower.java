import acm.program.*;
import acm.graphics.*;
import java.awt.*;
/**
 * Lawnmower.java.
 * 
 * A class for the Lawnmower
 */
public class Lawnmower extends GCompound implements Runnable 
{
    // constants
    private static final double DELAY = 20;
    // instance variables 
    private PlantVsZombie game; // the main game
    private boolean isAlive = true;
    private boolean moving = false;

    /**
     * Constructor for objects of class Lawnmower
     * @param game The game PlantVsZombie
     */
    public Lawnmower(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;

        // create the sub flower, centered at the local origin
        GImage lawnmower = new GImage("lawnmower.png");
        lawnmower.setSize(80,80);
        add(lawnmower, -80/2, -80/2);
    }

    /** the run method */
    public void run() {
        while (isAlive && game.getGameOver() == false) {
            if (moving == true) {
                move();
               game.checkCollision(this);
            } else {
                game.checkCollision(this);
            }
            pause(DELAY);
        }
        disappear();
    }
    
    // change the value of moving
    public void moving(){
        moving = true;
    }
    
    /** move the lawnmower */
    private void move(){
        movePolar(5,0);
    }
    
    /** kill the lawnmower */
    public void die() {
        isAlive = false;
    }

    // get the value of isALive
    public boolean isAlive() {
        return isAlive;
    }

    // the lawnmower disappears
    private void disappear() {
        removeAll(); // remove the lawnmower
    }
}

