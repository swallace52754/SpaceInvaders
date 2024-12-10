import java.awt.Graphics;
import java.awt.Image;

public class Player extends GameEntity {
    private final int speed;
    private final Image image;

    //Constructor for the player
    public Player(int x, int y) {
        super(x, y, 20, 50);
        this.speed = 10;
        this.image = ImageLoader.loadImage("images/player.png");
    }

    //Moves the player left by decreasing the X value 
    public void moveLeft(){
        x -= speed;

        //Ensures the player can't move outside of the game screen
        if(x < 0){
            x = 0;
        }
    }

    //Moves the player right by increasing the X value 
    public void moveRight(){
        x += speed;

        //Ensures the player can't move outside of the game screen
        if(x > 800 - width){
            x = 800 - width;
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    //When called, draws the player on the screen
    @Override
    public void draw(Graphics g) {
            g.drawImage(image, x, y, width, height, null);
        }
}
