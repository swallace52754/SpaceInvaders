import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class GameScreen extends JPanel implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Player player;
    private int score = 0;
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
                endGame();
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
                    score += 50;
                    i--;
                    if (enemies.size() < 1){
                        for(int k=0; k<5; k++){
                            score += 150;
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

    private void endGame(){
        running = false;

        //Remove stored enemies and bullets
        enemies.removeAll(enemies);
        bullets.removeAll(bullets);

        //Repaint canvas showing game over screen and score
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (running){
            player.draw(g);

            for(Enemy enemy : enemies){
                enemy.draw(g);
            }

            for(Bullet bullet : bullets){
                bullet.draw(g);
            }

            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 10, 20);

        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN);
            g.drawString("GAME OVER", getWidth() / 2 - 50, getHeight() / 2 - 20);
            g.drawString("Your Score: " + score, getWidth() / 2 - 50, getHeight() / 2);
        }
    }
    
}
