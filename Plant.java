import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Plant.java
 * 
 * A class for the plant including sunflowers and peashooters. 
 */
public class Plant extends GCompound implements Runnable
{
    // constants
    private static final double DELAY = 3000;
    // instance variables 
    private PlantVsZombie game; // the main game
    private String type;
    private String name; 
    private boolean isAlive = true;
    private int lifeSpan; 
    /**
     * Constructor for objects of class Plant
     * @param   type    the type of the plant
     * @param   game    the game PlantVsZombie
     */
    public Plant(String type, PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.type = type;
        this.game = game;
        if (type == "sunflower") name = "sunflower.gif";
        if (type == "peashooter") name = "peashooter.gif";
        // create the plant, centered at the local origin
        GImage plant = new GImage(name);
        plant.setSize(80,80);
        add(plant, -80/2, -80/2);
    }

    /** The run method */
    public void run() {
        while (isAlive && game.getGameOver() == false) {
            oneTimeStep();
        }
        disappear();
    }

    /** Control the animation */
    public void oneTimeStep(){
        // create sun if type is sunflower
        if (type == "sunflower") game.createSun(this);
        // create pea if type is peashooter
        if (type == "peashooter") {
            game.createPea(this);
            pause(DELAY);
        }
        
    }
    
    /** Get the type */
    public String getType(){
        return type;
    }
    
    /** Kill the plant */
    public void die() {
        isAlive = false;
    }

    // get the value of isALive
    public boolean isAlive() {
        return isAlive;
    }

    // the plant disappears
    private void disappear() {
        removeAll();
    }
}
