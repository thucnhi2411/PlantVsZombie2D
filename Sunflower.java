import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Sunflower.java
 * 
 * A class for the sunflower that creates the sun
 */
public class Sunflower extends GCompound implements Runnable
{
    // constants
    private static final double DELAY = 500;
    // instance variables 
    private PlantVsZombie game; // the main game
    private boolean isAlive = true;
    /**
     * Constructor for objects of class Sunflower
     */
    public Sunflower(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;
        
        // create the sub flower, centered at the local origin
        GImage sunflower = new GImage("sunflower.gif");
        sunflower.setSize(80,80);
        add(sunflower, -80/2, -80/2);
    }
    
    /** the run method */
    public void run() {
        while (isAlive) {
            return;
        }
        disappear();
    }
    
    /** kill the sunflower */
    public void die() {
        isAlive = false;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    
    // the sunflower disappears
    private void disappear() {
        removeAll();
    }
}
