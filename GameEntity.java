import java.awt.*;

public abstract class GameEntity {
    protected int x, y, width, height;
    // protected static int height;

    public GameEntity (int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public abstract void update();
    public abstract void draw(Graphics g);
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public boolean intersects(GameEntity other) {
        return this.getX() < other.getX() + other.getWidth() && 
        this.getX() + this.getWidth() > other.getX() &&
        this.getY() < other.getY() + other.getHeight() && 
        this.getY() + this.getHeight() > other.getY();

    }
}
