import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameEntity {
    private final int speed;

    public Player(int x, int y) {
        super(x, y, 20, 50);
        this.speed = 10;
    }

    public void moveLeft(){
        x -= speed;
        // if (x > 10){
        //     x -= speed;
        // } else{
        //     x = 10;
        // }

        if(x < 0){
            x = 0;
        }

        // System.out.println(x);
    }
    public void moveRight(){
        x += speed;

        if(x > 800 - width){
            x = 800 - width;
        }

        // if (x < (790 - width)){
        //     x += speed;
        // } else {
        //     x = (800 - width - 10);
        // }


        //System.out.println(x);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
    
}
