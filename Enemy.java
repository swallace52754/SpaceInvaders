
import java.awt.Graphics;
import java.awt.Image;

public class Enemy extends GameEntity{
    private int speed;
    private Image image;

    //Constructor for enemies
    public Enemy(int x, int y, int speed) {
        super(x, y, 50, 50);
        this.image = ImageLoader.loadImage("images/alien.png");
        this.speed = speed;
    }
    
    //Move the enemies in specific direction
    public void move(){
        x += speed;

        //When the edge of the screen is reached, enemies change direction and move down
        if (x<=0 || x + width >=800){
            speed = -speed;
            y += 80;
        }
    }

    public void setY(int y){
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }
    
    //Check if the enemy has reached the player
    //This will end the game if it happens
    public boolean isOutOfBounds() {
        return getY() > 500;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //When called, draws the enemy on the screen
    @Override 
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);    
    }
}