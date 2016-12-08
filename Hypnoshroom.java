import acm.program.*;
import acm.graphics.*;
import java.awt.*;
/**
 * Hypnoshroom.java.
 * 
 * A class for the Hypnoshroom. This is the mushroom that can hypnotize.
 */
public class Hypnoshroom extends GCompound implements Runnable 
{
    // constants
    private static final double DELAY = 50;
    // instance variables 
    private PlantVsZombie game; // the main game
    private boolean isAlive = true;

    /**
     * Constructor for objects of class Hypnoshroom
     * @param   game    the game PlantVsZombie
     */
    public Hypnoshroom(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;

        // create the sub flower, centered at the local origin
        GImage hypnoshroom = new GImage("hypnoshroom.png");
        hypnoshroom.setSize(80,80);
        add(hypnoshroom, -80/2, -80/2);
    }

    /** the run method */
    public void run() {
        while (isAlive && game.getGameOver() == false) {
            game.checkCollision(this);
        }
        disappear();
    }

    /** kill the hypnoshroom */
    public void die() {
        isAlive = false;
    }

    // get the value of isALive
    public boolean isAlive() {
        return isAlive;
    }

    // the hypnoshroom disappears
    private void disappear() {
        removeAll(); // remove the hypnoshroom
    }
}