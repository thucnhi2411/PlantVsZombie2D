import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.util.*;
/**
 * The PlantVsZombie game.
 * 
 * Description: The game starts with 8x5 grid of grass, 5 choices of plants, 50 suns. Different numbers of suns
 * allow player to drag different types of plants to prevent the zombies from entering the house. The suns are
 * by the sunflower. One sunflower creates 25 suns every 5 seconds. The peashooter creates the pea that can kill
 * the zombie. The wall pauses the movement of zombie in 3 seconds. The bomb kills all the zombies that are in the 
 * grid of grass. The hypnoshroom which is the mushroom that can hypnotize the zombie makes the zombie move the
 * opposite way out of the screen. When the zombie hits the sunflower or the peashooter, the sunflower and the 
 * peashooter will disappear. The wall, the bomb and the hypnoshroom will disappear after affecting the zombie. When
 * the zombie hits the lawn mower, the lawn mower will move and kill all the zombies and the plants on the same
 * row. When the zombie reaches the house, the game is over. When all the zombies are killed, the player wins. 
 * 
 * Thuc Nhi Le
 */
public class PlantVsZombie extends GraphicsProgram
{
    // initial size of the window
    public static int 
    APPLICATION_WIDTH = 1000,
    APPLICATION_HEIGHT = 720;   

    // instance variables
    private Plant[] plant = new Plant[40]; // the array of sunflower and peashooter
    private Lawnmower[] lawnmower = new Lawnmower[5]; // the array of lawn mower
    private int fill=0; // whether the array of plant is filled
    Grass[][] grass = new Grass[5][8]; // array of grass
    PlantChoice[] plantChoice = new PlantChoice[5]; // array of plant choice
    private int sun = 50; // the number of sun
    private GLabel sunNumber; // the label showing the number of sun
    private GImage sunflowerChoice; // the choice of sunflower
    private GImage peashooterChoice; // the choice of peashooter
    private GImage wallChoice; // the choice of wall
    private GImage bombChoice; // the choice of bomb
    private GImage hypnoshroomChoice; // the choice of mushroom that can hypnotize
    private GPoint lastPoint; // last point
    private boolean 
        sunflowerDragged = false, // whether sunflower is dragged
        peashooterDragged = false, // whether peashooter is dragged
        wallDragged = false, // whether wall is dragged
        bombDragged = false, // whether bomb is dragged
        hypnoshroomDragged = false; // whether the hypnoshroom is dragged
    private Zombie[] zombie = new Zombie[20]; // the array of zombie
    private RandomGenerator rand = RandomGenerator.getInstance(); //the random generator
    private boolean gameOver = false; // whether the game is over
    private int zombieDead = 0; // the number of dead zombie 
  

    /** the init method */
    public void run(){
        drawGraphics(); // draw the graphics
    }

    /** 
     * The mousePressed starts dragging if the object is pressed on 
     * @param   point   the location of the mouse
     */
    public void mousePressed(GPoint point){
        // if the object is pressed, set the boolean value to true
        if (sunflowerChoice.contains(point) && sun>=50) sunflowerDragged = true;
        if (peashooterChoice.contains(point) && sun >= 100) peashooterDragged = true;
        if (wallChoice.contains(point) && sun >= 150) wallDragged = true;
        if (bombChoice.contains(point) && sun >= 200) bombDragged = true;
        if (hypnoshroomChoice.contains(point) && sun >= 250) hypnoshroomDragged = true;
        lastPoint = point;

    }

    /** 
     * Move the object when it is dragged
     * @param   point   the location of the mouse
     */
    public void mouseDragged(GPoint point){
        // if sunflower is dragged
        if (sunflowerDragged){
            sunflowerChoice.move(point.getX()-lastPoint.getX(),
                point.getY()-lastPoint.getY());
        }
        // if peashooter is dragged
        if (peashooterDragged){
            peashooterChoice.move(point.getX()-lastPoint.getX(),
                point.getY()-lastPoint.getY());
        }
        // if wall is dragged
        if (wallDragged){
            wallChoice.move(point.getX()-lastPoint.getX(),
                point.getY()-lastPoint.getY());
        }
        // if bomb is dragged
        if (bombDragged){
            bombChoice.move(point.getX()-lastPoint.getX(),
                point.getY()-lastPoint.getY());
        }
        // if hypnoshroom is dragged
        if (hypnoshroomDragged){
            hypnoshroomChoice.move(point.getX()-lastPoint.getX(),
                point.getY()-lastPoint.getY());
        }
        // assign point to lastpoint
        lastPoint = point;
    }

    /** 
     * Stop dragging
     * @param   point   the location of the mouse
     */
    public void mouseReleased(GPoint point) {
        double x = point.getX(); // the x location of the mouse
        double y = point.getY(); // the y location of the mouse
        double xLocation = 0; // the x location of the plant
        double yLocation = 0; // the y location of the plant
        int col = 0; // the column of the grass cell
        int row = 0; // the row of the grass cell

        // find the location to place to plant 
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) { 
                if (x>j*100+160 && x<(j+1)*100+160 && y>i*100+200 && y<(i+1)*100+200) {
                    xLocation = j*100+10+200; // set the x location in the cell
                    yLocation = i*100+50+200; // set the y location in the cell
                    row = i; // find the row
                    col = j; // find the column
                }
            }
        }

        // if the sunflower is dragged
        if (sunflowerDragged){
            // if out of grid
            if (grass[row][col].isFilled() == true || x<160 || x>960 || y<200 || y>700) {
                sunflowerChoice.setLocation(170,50);
                sunflowerDragged = false;
            } else {
                sun = sun - 50; // reduce the sun
                sunNumber.setLabel(sun+"");
                // create the sunflower if the mouse is released in the grid
                plant[fill] = new Plant("sunflower",this);
                add(plant[fill],xLocation,yLocation);
                new Thread(plant[fill]).start();
                fill++;
                sunflowerChoice.setLocation(170,50);
                // set the grass to filled status
                grass[row][col].fill(); 
                // stop dragging 
                sunflowerDragged = false; 
            }
        }

        // if the peashooter is dragged
        if (peashooterDragged){
            // if out of grid
            if (grass[row][col].isFilled() == true || x<160 || x>960 || y<200 || y>700) {
                peashooterChoice.setLocation(270,50);
                peashooterDragged = false;
            } else {
                sun = sun - 100; // reduce the sun
                sunNumber.setLabel(sun+"");
                // create the peashooter if the mouse is released in the grid
                plant[fill] = new Plant("peashooter",this);
                add(plant[fill],xLocation,yLocation);
                new Thread(plant[fill]).start();
                fill++;
                peashooterChoice.setLocation(270,50);
                // set the grass to filled status
                grass[row][col].fill();
                // stop dragging 
                peashooterDragged = false;
            }
        }

        // if the wall is dragged
        if (wallDragged){
            // if out of grid
            if (grass[row][col].isFilled() == true || x<160 || x>960 || y<200 || y>700) {
                wallChoice.setLocation(370,50);
                wallDragged = false;
            } else {
                sun = sun -150;// reduce the sun
                sunNumber.setLabel(sun+"");
                // create the wall if the mouse is released in the grid
                Wall wall = new Wall(this);
                add(wall,xLocation,yLocation);
                new Thread(wall).start();
                wallChoice.setLocation(370,50);
                // set the grass to filled status
                grass[row][col].fill();
                // stop dragging 
                wallDragged = false;
            }
        }

        // if the bomb is dragged
        if (bombDragged){
            // if out of grid
            if (grass[row][col].isFilled() == true || x<160 || x>960 || y<200 || y>700) {
                bombChoice.setLocation(470,50);
                bombDragged = false;
            } else {
                sun = sun - 200;// reduce the sun
                sunNumber.setLabel(sun+"");
                // create the bomb if the mouse is released in the grid
                Bomb bomb = new Bomb(this);
                add(bomb,xLocation,yLocation);
                new Thread(bomb).start();
                bombChoice.setLocation(470,50);
                // set the grass to filled status
                grass[row][col].fill();
                // stop dragging 
                bombDragged = false;
            }
        }

        // if the hypnoshroom is dragged
        if (hypnoshroomDragged){
            // if out of grid
            if (grass[row][col].isFilled() == true || x<160 || x>960 || y<200 || y>700) {
                hypnoshroomChoice.setLocation(570,50);
                hypnoshroomDragged = false;
            } else {
                sun = sun - 200;
                sunNumber.setLabel(sun+"");
                // create the hypnoshroom if the mouse is released in the grid
                Hypnoshroom hypnoshroom = new Hypnoshroom(this);
                add(hypnoshroom,xLocation,yLocation);
                new Thread(hypnoshroom).start();
                hypnoshroomChoice.setLocation(570,50);
                // set the grass to filled status
                grass[row][col].fill();
                setVacant(hypnoshroom.getX(),hypnoshroom.getY()); //set the grass to vacant status
                // stop dragging 
                hypnoshroomDragged = false;
            }
        }
    }

    /** 
     * Check collision of the wall
     * @param   wall    the object of the Wall class
     */ 
    public void checkCollision(Wall wall){
        // check if the zombie hit the wall
        for (int i=0; i<20; i++){
            // if the zombie is in the screen
            if (zombie[i].getX()<980 && zombie[i].getBounds().intersects(wall.getBounds())){
                zombie[i].pause(); // pause the zombie
                // after pausing the zombie, kill the wall and set the grass to vacant status
                if (zombie[i].getResume() == true) {
                    wall.die();
                    setVacant(wall.getX(), wall.getY());
                }
            }
        }
    }

    /** 
     * Check collision of the hypnoshroom
     * @param   hypnoshroom     the object of the Hypnoshroom class
     */
    public void checkCollision(Hypnoshroom hypnoshroom){
        // check if the zombie hit the hypnoshroom
        for (int i=0; i<20; i++){
            // if the zombie is in the screen
            if (zombie[i].getX()<980 && zombie[i].isHypnotized()==false 
            && zombie[i].getBounds().intersects(hypnoshroom.getBounds())){
                hypnoshroom.die(); // the hypnoshroom disappears
                zombie[i].hypnotized();
                setVacant(hypnoshroom.getX(),hypnoshroom.getY()); 
            }
        }
    }

    /** 
     * The bomb is launched
     * @param   bomb    the object of the Bomb class
     */
    public void checkCollision(Bomb bomb){
        for (int i=0; i<20; i++){
            double xZombie = zombie[i].getX();
            double yZombie = zombie[i].getY();
            // if the zombie is in the screen
            if (xZombie<1000){
                zombie[i].decreaseLives();
                zombie[i].setLocation(getWidth()+rand.nextDouble(300,800),yZombie); // the zombie is killed
                bomb.die();
                setVacant(bomb.getX(),bomb.getY()); 
            } else {
                bomb.die();
                setVacant(bomb.getX(),bomb.getY()); 
            }

        }
    }

    /** Whether the array of plant is filled*/
    public int checkFill(){
        return fill;
    }

    /** 
     * Check collision of the zombie
     * @param   zombie  the object of the Zombie class
     */
    public void checkCollision(Zombie zombie){
        double x = zombie.getX();
        double y = zombie.getY();
        // check if the zombie hits the house
        if (x-zombie.getWidth()/2<20) {
            gameOver();
        }
        // check if the zombie is hypnotized and out of the screen
        if (zombie.isHypnotized()==true && zombie.getX()>1000) {
            zombie.setAngle(zombie.getAngle()); // change the angle
            zombie.notHypnotized(); // set it not hypnotized
        }
        // check if the zombie hits the sunflower or the peashooter
        for (int i = 0; i< fill; i++){
            if (zombie.getBounds().intersects(plant[i].getBounds())){
                plant[i].die();
                setVacant(plant[i].getX(),plant[i].getY()); 
            }
        }
    }

    /** 
     * Check collision of the pea
     * @param   pea     the object of the Pea class
     */
    public void checkCollision(Pea pea){
        // check if the pea hit the zombie
        for (int i=0; i<20; i++){
            // if the zombie is in the screen
            if (zombie[i].getX()<980 && pea.getBounds().intersects(zombie[i].getBounds())){
                zombie[i].decreaseLives();
                zombie[i].setLocation(getWidth()+rand.nextDouble(300,800),zombie[i].getY()); // the zombie is killed
                pea.die(); // the pea disappears
            }
        }
        if (pea.getX()>1000) pea.die(); // out of screen
    }

    /** 
     * Check collision of the lawnmower
     * @param   lawnmower   the object of the Lawnmower class
     */
    public void checkCollision(Lawnmower lawnmower){
        // when the lawnmower hits the zombie
        for (int i=0; i<20; i++){
            // if the zombie is in the screen
            if (zombie[i].getX()<980 && lawnmower.getBounds().intersects(zombie[i].getBounds())){
                lawnmower.moving();
                zombie[i].decreaseLives();
                zombie[i].setLocation(getWidth()+rand.nextDouble(300,800),zombie[i].getY()); // the zombie is killed
            }
        }
        // whether the lawn mower hits the plant
        for (int i = 0; i< fill; i++){
            if (lawnmower.getBounds().intersects(plant[i].getBounds())){
                plant[i].die();
                setVacant(plant[i].getX(),plant[i].getY()); 
            }
        }
        // out of screen
        if (lawnmower.getX()>getWidth()) lawnmower.die();
    }

    /** 
     * Set the grass cell vacant
     * @param   x   the x location of the plant
     * @param   y   the y location of the plant
     */
    private void setVacant(double x,double y){
        int col = 0; // the column of the grass cell
        int row = 0; // the row of the grass cell
        // find the location to place to plant 
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) { 
                if (x>j*100+160 && x<(j+1)*100+160 && y>i*100+200 && y<(i+1)*100+200) {
                    row = i;
                    col = j;
                }
            }
        }
        // set the filled status to false
        grass[row][col].vacant();
    }

    /** 
     * Create the sun and animate it
     * @param   plant   the plant that creates the sun
     */
    public void createSun(Plant plant){
        Sun sun = new Sun(this);
        add(sun,plant.getX(),plant.getY());
        new Thread(sun).start();
        increaseSun();
        pause(3000);
    }

    /** 
     * Create the pea and animate it
     * @param   plant   the plant that creates the pea
     */
    public void createPea(Plant plant){
        Pea pea = new Pea(this);
        add(pea,plant.getX(),plant.getY());
        new Thread(pea).start();
        pause(2500);
    }

    /** 
     * Remove the sun if it is out of screen
     * @param   sun     the object of the Sun class
     */
    public void outOfScreen(Sun sun){
        if (sun.getY()<0) sun.removeAll();
    }

    // increase the sun
    private void increaseSun(){
        sun = sun + 25;
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

        // draw the board showing the sun acquired
        GRect sunLabel = new GRect(120,80);
        sunLabel.setFilled(true);
        sunLabel.setFillColor(new Color(255, 166, 77));
        add(sunLabel,20,40);

        sunNumber = new GLabel("50");
        sunNumber.setFont(new Font("Sanserif", Font.BOLD, 30));
        sunNumber.setColor(Color.BLACK);
        add(sunNumber,50,75);

        GImage sun = new GImage("sun.png");
        sun.setSize(40,40);
        add(sun,60,80);

        // draw the 5 choices of plant
        drawPlantChoice();

        // draw the sunflower choice and label 
        sunflowerChoice = new GImage("sunflower.gif");
        sunflowerChoice.setSize(80,80);
        add(sunflowerChoice,170,50);

        GLabel flowerCost = new GLabel("50");
        flowerCost.setFont(new Font("Sanserif", Font.BOLD, 20));
        flowerCost.setColor(Color.BLACK);
        add(flowerCost,195,160);

        // draw the peashooter choice and label
        peashooterChoice = new GImage("peashooter.gif");
        peashooterChoice.setSize(80,80);
        add(peashooterChoice,270,50);

        GLabel peashooterCost = new GLabel("100");
        peashooterCost.setFont(new Font("Sanserif", Font.BOLD, 20));
        peashooterCost.setColor(Color.BLACK);
        add(peashooterCost,295,160);

        // draw the wall choice and label
        wallChoice = new GImage("wall.png");
        wallChoice.setSize(80,80);
        add(wallChoice,370,50);

        GLabel wallCost = new GLabel("150");
        wallCost.setFont(new Font("Sanserif", Font.BOLD, 20));
        wallCost.setColor(Color.BLACK);
        add(wallCost,390,160);

        // draw the bomb choice
        bombChoice = new GImage("bomb.png");
        bombChoice.setSize(80,80);
        add(bombChoice,470,50);

        GLabel bombCost = new GLabel("200");
        bombCost.setFont(new Font("Sanserif", Font.BOLD, 20));
        bombCost.setColor(Color.BLACK);
        add(bombCost,490,160);

        // draw the hypnoshroom choice and label
        hypnoshroomChoice = new GImage("hypnoshroom.png");
        hypnoshroomChoice.setSize(80,80);
        add(hypnoshroomChoice,570,50);

        GLabel hypnoshroomCost = new GLabel("250");
        hypnoshroomCost.setFont(new Font("Sanserif", Font.BOLD, 20));
        hypnoshroomCost.setColor(Color.BLACK);
        add(hypnoshroomCost,590,160);

        // add the zombie
        for (int i = 0; i<20; i++){
            boolean stronger = false;
            if (i%3==0) stronger = true;
            zombie[i] = new Zombie(1.5,180,stronger,this);
            add(zombie[i],rand.nextDouble(getWidth()+800,getWidth()*3),250+ (i/4)*100);
            if (stronger == true) zombie[i].stronger();
            new Thread(zombie[i]).start();
        }

        // add the lawnmower
        for (int i = 0; i<5; i++){
            lawnmower[i] = new Lawnmower(this);
            add(lawnmower[i],100,100*i+240);
            new Thread(lawnmower[i]).start();
        }
    }

    // draw the 5 choices of plants
    private void drawPlantChoice(){
        for (int i = 0; i<5; i++){
            boolean draggable = false; 
            plantChoice[i] = new PlantChoice(draggable,this);
            add(plantChoice[i],160 + i*100,40);
            new Thread(plantChoice[i]).start();
        }

    }

    // Check if the plant choice is draggable
    public void checkDraggable(){
        for (int i = 0; i<5; i++){
            if (plantChoice[i] == null) {
                return ;
            } else {
                if (sun>=50+i*50){
                    plantChoice[i].setDraggable();
                } else {
                    plantChoice[i].notDraggable();
                }
                // change the color is the choice is draggable
                plantChoice[i].changeColor();
            } 
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
                grass[i][j] = new Grass(i,j,darker,this);
                add(grass[i][j], x, y);
                if (darker) {
                    grass[i][j].changeColor();   
                }
            }

        }
    }

    // get the value of gameOver
    public boolean getGameOver(){
        return gameOver;
    }

    /** The gameOver method when the game is Over */
    private void gameOver(){
        gameOver = true;
        // stop the zombie
        for (int i = 0;i<20;i++){
            zombie[i].stopMoving(); 
        }

        // draw the black screen
        GRect lostblackscreen = new GRect(1000,720);
        lostblackscreen.setFilled(true);
        lostblackscreen.setColor(Color.BLACK);
        add(lostblackscreen,0,0);

        // the label that appears when the game is over
        GImage gameOverImage = new GImage("gameOver.png");
        gameOverImage.setSize(1000,720);
        add(gameOverImage,0,0);

    }

    /** Count the number of dead zombie */
    public void zombieDead(){
        zombieDead++;
        if (zombieDead == 20) win();
    }

    /** When the player win*/
    public void win(){
        gameOver = true;
        // draw the black screen
        GRect wonblackscreen = new GRect(1000,720);
        wonblackscreen.setFilled(true);
        wonblackscreen.setColor(Color.BLACK);
        add(wonblackscreen,0,0);

        // the label that appears when the game is over
        GImage winlabel = new GImage("winlabel.png");
        winlabel.setSize(1000,720);
        add(winlabel,0,0);

    }

}
