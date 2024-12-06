
import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameEntity{
    private int speed;

    public Enemy(int x, int y) {
        super(x, y, 50, 50);
        this.speed = 2;
    }
    
    public void move(){
        x += speed;

        if (x<=0 || x + width >=800){
            speed = -speed;

            y += 100;
            //TODO: update ability to stop at bottom
        }
    }

    public boolean isOutOfBounds() {
        return x<0 || x>800;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

}