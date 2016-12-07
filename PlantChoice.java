import acm.program.*;
import acm.graphics.*;
import java.awt.*;
/*
 * The class to control the plantChoice
 */
public class PlantChoice extends GCompound implements Runnable{
    // instance variables:
    double size = 5; // the size of one cell of plantChoice
    PlantVsZombie game; // the main game
    boolean draggable = false;
    GRect plantChoice;

    /** The constructor creates the plantChoice cell */
    public PlantChoice(boolean draggable, PlantVsZombie game){
        this.draggable = draggable;
        this.game = game;

        plantChoice = new GRect(100,140);
        plantChoice.setFilled(true);
        plantChoice.setFillColor(new Color(225, 225, 208));
        add(plantChoice);
    }

    /** The changeColor method to change the color of draggable plantChoice */
    public void changeColor(){
        if (draggable == true){
            plantChoice.setFillColor(new Color(216, 249, 126));
        } else {
            plantChoice.setFillColor(new Color(225, 225, 208));
        }
    }

    /** Set draggable */
    public void setDraggable(){
        draggable = true;
    }
    
    public void notDraggable(){
        draggable = false;
    }
    
    /** The run method */
    public void run(){
        while(true){
            game.checkDraggable();
        }
    }

}