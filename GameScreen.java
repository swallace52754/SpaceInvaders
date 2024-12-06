import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GameScreen extends JPanel implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;

    public GameScreen() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        player = new Player(375, 550);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();

        for(int i=0; i<5; i++){
            enemies.add(new Enemy((100 + (i*60)), 100));
        }


        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> player.moveLeft();
                    case KeyEvent.VK_RIGHT -> player.moveRight();
                    case KeyEvent.VK_SPACE -> shootBullet();
                }
            }
        });
    }

    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    


    @Override
    public void run() {
        while (running) {

            update();
            repaint();
            try{
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        player.update();

        for (int i =0; i<enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            enemy.move();
            if(enemy.isOutOfBounds()){
                enemies.remove(i);
                i--;
            }
        }


        for (int i =0; i<bullets.size(); i++){
            Bullet bullet = bullets.get(i);

            bullet.move();
            if(bullet.isOutOfBounds()){
                bullets.remove(i);
                i--;
            }

            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                if(bullet.intersects(enemy)){
                    bullets.remove(i);
                    enemies.remove(j);
                    i--;

                    if (enemies.size() < 1){
                        for(int k=0; k<5; k++){
                            enemies.add(new Enemy((100 + (k*60)), 100));
                        }
                    }
                    break;
                }
                
            }
        }
    }

    private void shootBullet() {
        int bulletX = player.getX() + player.getWidth() / 2-5;
        int bulletY = player.getY() - 10;
        Bullet bullet = new Bullet(bulletX, bulletY);
        bullets.add(bullet);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        player.draw(g);

        for(Enemy enemy : enemies){
            enemy.draw(g);
        }

        for(Bullet bullet : bullets){
            bullet.draw(g);
        }
    }
    
}
