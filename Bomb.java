import acm.program.*;
import acm.graphics.*;
import java.awt.*;
/**
 * Bomb.java.
 * 
 * A class for the Bomb
 */
public class Bomb extends GCompound implements Runnable 
{
    // constants
    private static final double DELAY = 50;
    // instance variables 
    private PlantVsZombie game; // the main game
    private boolean isAlive = true;

    /**
     * Constructor for objects of class Bomb
     */
    public Bomb(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;

        // create the sub flower, centered at the local origin
        GImage bomb = new GImage("bomb.png");
        bomb.setSize(80,80);
        add(bomb, -80/2, -80/2);
    }

    /** the run method */
    public void run() {
        while (isAlive && game.getGameOver() == false) {
            game.checkCollision(this);
        }
        disappear();
    }

    /** kill the bomb */
    public void die() {
        isAlive = false;
    }

    // get the value of isALive
    public boolean isAlive() {
        return isAlive;
    }

    // the bomb disappears
    private void disappear() {
        // show explosion and disappear
        removeAll(); // remove the bomb
        // draw an explosion
        GImage explosion = new GImage("bigexplosion.gif");
        explosion.setSize(100, 100);
        add(explosion, -100/2, -100/2);
        pause(500);
        removeAll();
    
    }
}

