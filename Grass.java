import acm.program.*;
import acm.graphics.*;
import java.awt.*;
/*
 * The class to control the grass
 */
public class Grass extends GCompound {
    // instance variables:
    int row; // the row of the cell;
    int col; // the column of the cell;
    PlantVsZombie game; // the main game
    boolean darker = false;
    boolean filled = false;
    GRect grass;

    /** 
     * The constructor creates the grass cell
     * @param   row     the row in the grid
     * @param   col     the column in the grid
     * @param   darker  thether the cell is darker
     * @param   game    the game PlantVsZombie
     */
    public Grass(int row, int col, boolean darker, PlantVsZombie game){
        this.row = row;
        this.col = col;
        this.darker = darker;
        this.game = game;

        grass = new GRect(100,100);
        grass.setFilled(true);
        grass.setFillColor(new Color(196, 249, 126));
        add(grass);
    }

    /** The changeColor method to change the color of darker grass */
    public void changeColor(){
        grass.setFillColor(new Color(175, 251, 45));
    }

    /** The isFilled method returns whether the cell is filled */
    public boolean isFilled(){
        return filled;
    }

    /** The fill method changes the value of filled to true */
    public void fill(){
        filled = true;
    }

    /** Change the filled value back to false */
    public void vacant(){
        filled = false;
    }
}