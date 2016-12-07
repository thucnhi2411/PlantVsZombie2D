import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Plant.java
 * 
 * A class for the plant that creates the sun
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
     */
    public Plant(String type, PlantVsZombie game)
    {
        // save the paramerters in instance variables
        this.type = type;
        this.game = game;
        if (type == "sunflower") name = "sunflower.gif";
        if (type == "peashooter") name = "peashooter.gif";
        if (type == "wall") name = "wall.png";
        if (type == "hypnoshroom") name = "hypnoshroom.png";
        // create the plant, centered at the local origin
        GImage plant = new GImage(name);
        plant.setSize(80,80);
        add(plant, -80/2, -80/2);
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
        if (type == "sunflower") game.createSun(this);
        if (type == "peashooter") {
            game.createPea(this);
            pause(DELAY);
        }
        if (type == "wall" ||type == "hypnoshroom") return;
    }
    
    /** get the type */
    public String getType(){
        return type;
    }
    
    /** kill the plant */
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
