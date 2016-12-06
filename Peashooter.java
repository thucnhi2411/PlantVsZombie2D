import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Peashooter.java
 * 
 * A class for the peashooter that creates the sun
 */
public class Peashooter extends GCompound implements Runnable
{
    // constants
    private static final double DELAY = 500;
    // instance variables 
    private PlantVsZombie game; // the main game
    private boolean isAlive = true;
    /**
     * Constructor for objects of class peashooter
     */
    public Peashooter(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;
        
        // create the sub flower, centered at the local origin
        GImage peashooter = new GImage("peashooter.gif");
        peashooter.setSize(80,80);
        add(peashooter, -80/2, -80/2);
    }
    
    
    /** the run method */
    public void run() {
        while (isAlive && game.getGameOver() == false) {
            oneTimeStep();
            pause(DELAY);
        }
        disappear();
    }
    
    public void oneTimeStep(){
        game.checkCollision(this);
        game.createPea(this);
    }
    
    /** kill the peashooter */
    public void die() {
        isAlive = false;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    // the peashooter disappears
    private void disappear() {
        removeAll();
    }
}
