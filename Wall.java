import acm.program.*;
import acm.graphics.*;
import java.awt.*;
/**
 * Wall.java.
 * 
 * A class for the Wall
 */
public class Wall extends GCompound implements Runnable 
{
    // constants
    private static final double DELAY = 50;
    // instance variables 
    private PlantVsZombie game; // the main game
    private boolean isAlive = true;

    /**
     * Constructor for objects of class Wall
     */
    public Wall(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;

        // create the sub flower, centered at the local origin
        GImage wall = new GImage("wall.png");
        wall.setSize(80,80);
        add(wall, -80/2, -80/2);
    }

    /** the run method */
    public void run() {
        while (isAlive && game.getGameOver() == false) {
            oneTimeStep();
            //pause(DELAY);
        }
        disappear();
    }

    public void oneTimeStep(){
        game.checkCollision(this);

    }

    /** kill the wall */
    public void die() {
        isAlive = false;
    }

    // get the value of isALive
    public boolean isAlive() {
        return isAlive;
    }

    // the wall disappears
    private void disappear() {
        removeAll();
    }
}

