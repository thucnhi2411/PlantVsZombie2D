import acm.program.*;
import acm.graphics.*;
import java.awt.*;
/**
 * Sun.java.
 * 
 * A class for the sun
 */
public class Sun extends GCompound implements Runnable
{
    // constants
    private static final double DELAY = 50;
    // instance variables 
    private PlantVsZombie game; // the main game

    /**
     * Constructor for objects of class Sun
     */
    public Sun(PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.game = game;

        // create the sub flower, centered at the local origin
        GImage sun = new GImage("sun.png");
        sun.setSize(40,40);
        add(sun, -40/2, -40/2);
    }

    /** the run method*/
    public void run(){
        while(true){
            oneTimeStep();
            pause(DELAY);
        }
    }
    // control the animation
    private void oneTimeStep(){
        movePolar(30,90);
    }
}
