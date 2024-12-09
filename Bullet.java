import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameEntity {
    //Speed of the bullet
    private final int speed = 5;

    //Bullet constructor
    public Bullet(int x, int y) {
        super(x, y, 10, 5);
    }

    //Moves the bullet by deceasing the Y value (moving up)
    public void move() {
        y -= speed;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    //When called, draws the bullet on the screen
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    //Checks if the bullet is out of the game screen
    public boolean isOutOfBounds(){
        return y < 0; 
    }

}
