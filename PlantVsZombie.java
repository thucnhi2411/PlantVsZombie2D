import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;
/**
 * Write a description of class PlantVsZombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlantVsZombie extends GraphicsProgram
{
    // initial size of the window
    public static int 
    APPLICATION_WIDTH = 1000,
    APPLICATION_HEIGHT = 720;   
    
    // instance variables
    Grass[][] grass = new Grass[5][8]; // array of grass
    private int sun = 0; // the number of sun
    private GLabel sunNumber; // the label showing the number of sun
    private GImage sunflowerChoice; // the choice of sunflower
    private GImage peashooterChoice; // the choice of peashooter
    private GPoint lastPoint;
    private boolean 
        sunflowerDragged = false,
        peashooterDragged = false;
    
    public void init(){
        drawGraphics();
        
    }
    /** start dragging if the object is pressed on */
    public void mousePressed(GPoint point){
        if (sunflowerChoice.contains(point)) sunflowerDragged = true;
        if (peashooterChoice.contains(point)) peashooterDragged = true;
        lastPoint = point;
    }
    
    /** move the object when it is dragged */
    public void mouseDragged(GPoint point){
        if (sunflowerDragged){
            sunflowerChoice.move(point.getX()-lastPoint.getX(),
            point.getY()-lastPoint.getY());
        }
        
        if (peashooterDragged){
            peashooterChoice.move(point.getX()-lastPoint.getX(),
            point.getY()-lastPoint.getY());
        }
        
        
        lastPoint = point;
    }
    
     /** stop dragging */
    public void mouseReleased(GPoint point) {
        double x = point.getX();
        double y = point.getY();
        double xLocation = 0;
        double yLocation = 0;
        // if the sunflower is dragged
        if (sunflowerDragged){
            if (x<160 || x>960 || y<200 || y>700) {
                sunflowerChoice.setLocation(170,50);
                sunflowerDragged = false;
            } else {
                Sunflower sunflower = new Sunflower(this);
                add(sunflower,xLocation(x),yLocation(y));
                new Thread(sunflower).start();
                sunflowerChoice.setLocation(170,50);
                fillGrass(xLocation(x),yLocation(y));
                sunflowerDragged = false;
            }
        }
        
        // if the peashooter is dragged
        if (peashooterDragged){
            if (x<160 || x>960 || y<200 || y>700) {
                peashooterChoice.setLocation(270,50);
                peashooterDragged = false;
            } else {
                Peashooter peashooter = new Peashooter(this);
                add(peashooter,xLocation(x),yLocation(y));
                new Thread(peashooter).start();
                peashooterChoice.setLocation(270,50);
                fillGrass(xLocation(x),yLocation(y));
                peashooterDragged = false;
            }
        }
        
    }
    
    // find the x location in the grid
    public int xGrass(double x){
        int output=0;
        for (int i = 0; i<8;i++){
            if (x>i*100+160 && x<(i+1)*100+160)  output = i;
        }
        return output;
    }
    // find the y location in the grid
    public int yGrass(double y){
        int output=0;
        for (int i = 0; i<5;i++){
            if (y>i*100+200 && y<(i+1)*100+200)  output = i;
        }
        return output;
    }
    
    // fill the grass if the plant is placed on the grid of grass
    public void fillGrass(double x, double y){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) { 
                if (x>j*100+160 && x<(j+1)*100+160 && y>i*100+200 && y<(i+1)*100+200) grass[i][j].fill();
            }
        }
    }
    
    // find the x location in the grid
    private double xLocation(double x){
        double xLocation = 0;
        for (int i = 0; i<8;i++){
            if (x>i*100+160 && x<(i+1)*100+160)  xLocation = i*100+10+200;
        }
        return xLocation;
    }
    // find the y location in the grid
    private double yLocation(double y){
        double yLocation = 0;
        for (int i = 0; i<5;i++){
            if (y>i*100+200 && y<(i+1)*100+200)  yLocation = i*100+50+200;
        }
        return yLocation;
    }
    // increase the sun
    public void increaseSun(){
        sun++;
        sunNumber.setLabel(sun+"");
    }
    // draw the graphics
    private void drawGraphics(){
        // draw the grass
        drawGrass();
        
        // draw the house
        GRect house = new GRect(40,500);
        house.setFilled(true);
        house.setFillColor(new Color(239, 102, 45));
        add(house,20,200);
        
        GImage houseLabel = new GImage("houseLabel.png");
        houseLabel.setSize(40,500);
        add(houseLabel,15,200);
        
        
        // draw the choice of plant
        drawPlantChoice();
        
        // draw the board showing the sun acquired
        GRect sunLabel = new GRect(120,80);
        sunLabel.setFilled(true);
        sunLabel.setFillColor(new Color(255, 166, 77));
        add(sunLabel,20,40);
        
        sunNumber = new GLabel("0");
        sunNumber.setFont(new Font("Sanserif", Font.BOLD, 30));
        sunNumber.setColor(Color.BLACK);
        add(sunNumber,70,75);

        GImage sun = new GImage("sun.png");
        sun.setSize(40,40);
        add(sun,60,80);
        
        sunflowerChoice = new GImage("sunflower.gif");
        sunflowerChoice.setSize(80,80);
        add(sunflowerChoice,170,50);
        
        // draw the peashooter choice
        peashooterChoice = new GImage("peashooter.gif");
        peashooterChoice.setSize(80,80);
        add(peashooterChoice,270,50);
        
        GImage zombie = new GImage("zombie.gif");
        zombie.setSize(80,80);
        add(zombie,370,50);
    }
    
    
    // draw the choice of plants
    private void drawPlantChoice(){
        GRect[] choice = new GRect[6];
        for (int i = 0; i<6; i++){
            choice[i] = new GRect(100,140);
            choice[i].setFilled(true);
            choice[i].setFillColor(new Color(225, 225, 208));
            add(choice[i],160 + i*100,40);
        }
    }
    
    // draw the grass
    private void drawGrass() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) { 
                boolean darker = false;
                if ((i%2==0 && j%2!=0) || (j%2==0 && i%2!=0)){
                    darker = true;
                }
                double x = 160 + j*100;
                double y = 200 + i*100;      
                // customize the grass
                grass[i][j] = new Grass(100,i,j,darker,this);
                add(grass[i][j], x, y);
                if (darker) {
                    grass[i][j].changeColor();   
                }
            }
           
        }
    }
}
