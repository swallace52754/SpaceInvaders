import java.awt.*;

public abstract class GameEntity {
    protected int x, y, width, height;

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
}
