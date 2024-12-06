import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameEntity {
    private final int speed = 5;

    public Bullet(int x, int y) {
        super(x, y, 10, 5);
    }

    public void move() {
        y -= speed;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isOutOfBounds(){
        return y < 0; 
    }

}
