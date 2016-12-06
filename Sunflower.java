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
    private static final double DELAY = 3000;
    // instance variables 
    private PlantVsZombie game; // the main game
    private boolean isAlive = true;
    private int lifeSpan; 
    /**
     * Constructor for objects of class Sunflower
     */
    public Sunflower(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;
        lifeSpan = 4;
        // create the sub flower, centered at the local origin
        GImage sunflower = new GImage("sunflower.gif");
        sunflower.setSize(80,80);
        add(sunflower, -80/2, -80/2);
    }

    /** the run method */
    public void run() {
        while (isAlive && game.getGameOver() == false) {
            oneTimeStep();
        }
        disappear();
    }

    // control the animation
    public void oneTimeStep(){
        game.checkCollision(this);
        //game.createSun(this);
    }

    /** kill the sunflower */
    public void die() {
        isAlive = false;
    }

    // get the value of isALive
    public boolean isAlive() {
        return isAlive;
    }

    // the sunflower disappears
    private void disappear() {
        removeAll();
    }
}
