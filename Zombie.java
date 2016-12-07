import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * Zombie.java <p>
 * 
 * A class for zombie. <p>
 */
public class Zombie extends GCompound implements Runnable {
    // constants
    private static final double DELAY = 20;
    // instance variables
    private PlantVsZombie game; // the main game
    private boolean pause = false; // pause
    private boolean resume = false; // resume
    private double speed, angle; // the speed and the angle
    private boolean stronger = false; // whether the zombie is speedy zombie
    private String name; // the name of different zombie
    private boolean stopMoving = false; // whether the zombies are moving
    private int lives; // the lives of zombie
    private boolean hypnotized = false; // whether the zombie is hypnotized

    /** the constructor, create the zombie */
    public Zombie(double speed, 
    double angle, boolean stronger, PlantVsZombie game) {
        // save the parameters in instance variables
        this.game = game;
        this.speed = speed;
        this.angle = angle;
        this.stronger = stronger;
        lives = 3;

        // create the zombie, centered at the local origin
        if (stronger){
            name = "strongerzombie.gif";
        }
        else {
            name = "zombie.gif";
        }
        GImage zombie = new GImage(name);
        zombie.setSize(90, 90);
        add(zombie, -90/2, -90/2); 
    }

    public boolean isStronger(){
        return stronger;
    }
    // set the speed of the speedy zombies faster
    public void stronger(){
        speed = speed*0.75;
        lives = 5;
    }

    /** the run method, to animate the zombie */
    public void run() {
        while (!stopMoving && lives > 0) {
            while (pause == true) {
                pause(3000);
                resume = true;
                pause = false;
            } 
            oneTimeStep();
            pause(DELAY);
        }   
        removeAll();
    }

    // get the value of resume
    public boolean getResume(){
        return resume;
    }

    // change the value of pause
    public void pause(){
        pause = true;
    }

    // decrease lives of zombie
    public void decreaseLives(){
        lives--;
        if (lives == 0) game.zombieDead();
    }

    // stop the zombies when the game is over
    public void stopMoving(){
        stopMoving = true;
    }

    /** return the angle of movement */
    public double getAngle() {
        return angle;
    }
    
    /** change the angle of movement when bounce */
    public void setAngle(double angle) {
        this.angle = 180-angle;
    }
    
    /** zombie is hypnotized */
    public void hypnotized(){
        hypnotized = true;
        this.angle = 180 - angle;
    }
    
    /** zombie is not hypnotized */
    public void notHypnotized(){
        hypnotized = false;
    }
    
    /** whether the zombie is hypnotized */
    public boolean isHypnotized(){
        return hypnotized;
    }
    
    // in each time step, move the zombie and check the collision
    private void oneTimeStep() {
        if (game.checkFill()>=0) {
            movePolar(speed, angle);
            game.checkCollision(this);
        } else {
            movePolar(speed, angle);
        }
    }
    
}