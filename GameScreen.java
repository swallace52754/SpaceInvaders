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
    private boolean moveRight = true;

    public GameScreen() {
        //Creates a black background for the game
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        // Create player at the given X and Y values
        player = new Player(375, 550);
        //Create Enemy and Bullet array lists
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();

        //Add the number of inital enemies
        for(int i=0; i<10; i++){
            enemies.add(new Enemy((10 + (i*60)), 30));
        }

        // Create Key listener to take input for actions of the game
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

    // Method called to start the game thread
    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    


    //Game loop
    @Override
    public void run() {
        while (running) {
            update();
            //Repaints the canvas each time it is called, to update position 
            repaint();
            try{
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Update method which updates the game state
    private void update() {
        //Updates player positions
        player.update();

        boolean changeDirection = false;

        for (Enemy enemy:enemies){
            if ((moveRight && enemy.getX() + enemy.getWidth() >= 800) || (!moveRight && enemy.getX() <= 0)) {
                changeDirection = true;
                break;
            }
        }

        if (changeDirection) {
            moveRight = !moveRight;
            for (Enemy enemy : enemies) {
                enemy.setY(enemy.getY() + 50); // Move enemies down
            }
        }

        for (Enemy enemy : enemies) {
            enemy.setX(enemy.getX() + (moveRight ? enemy.getSpeed() : -enemy.getSpeed()));
            if (enemy.isOutOfBounds()) {
                endGame();
            }
        }

        //Loops through each enemy
        //Tells each one to move
        //Checks if they out of bounds, if they are the game is over
        // for (int i =0; i<enemies.size(); i++){
        //     Enemy enemy = enemies.get(i);
        //     enemy.move();
        //     if(enemy.isOutOfBounds()){
        //         enemies.remove(i);
        //         i--;
        //         endGame();
        //     }
        // }

        //Loops through the bullets in the game
        //Tells each one to move
        //If they reach the top of the screen without hitting an enemy they are removed
        for (int i =0; i<bullets.size(); i++){
            Bullet bullet = bullets.get(i);
            bullet.move();
            if(bullet.isOutOfBounds()){
                bullets.remove(i);
                i--;
            }

            //Loops through each enemy, and through each bullet to check if they are in the same location (intersect)
            //if a bullet intersects, it removes the enemy and the bullet
            // For each enemy hit, score is added the counter
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                if(bullet.intersects(enemy)){
                    bullets.remove(i);
                    enemies.remove(j);
                    score += 50;
                    i--;
                    //When all enemies are cleared, a bonus score is added
                    //More enemies are added, to keep the game playing
                    if (enemies.size() < 1){
                        score += 150;
                        for(int k=0; k<5; k++){
                            enemies.add(new Enemy((100 + (k*60)), 100));
                        }
                    }
                    break;
                }
                
            }
        }
    }

    //Shoots a bullet from the centre of the player
    private void shootBullet() {
        int bulletX = player.getX() + player.getWidth() / 2-5;
        int bulletY = player.getY() - 10;
        Bullet bullet = new Bullet(bulletX, bulletY);
        bullets.add(bullet);
    }

    //Called when the game is ended by the enemy
    private void endGame(){
        running = false;

        //Remove stored enemies and bullets
        enemies.removeAll(enemies);
        bullets.removeAll(bullets);

        //Remove all entities from screen, show gameover screen
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        // Clear canvas before trying to repaint anything
        super.paintComponent(g);
        if (running){
            //Draw the player entity
            player.draw(g);

            //Loop through the Enemy array, drawing each enemy
            for(Enemy enemy : enemies){
                enemy.draw(g);
            }

            // Draw the bullets on the screen
            for(Bullet bullet : bullets){
                bullet.draw(g);
            }

            //Show the player's score in the corner of the screen
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, 10, 20);

        } else {
            // Called after running is toggled to false
            //Clear entities from screen, shows game over message, and displays user score
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", getWidth() / 2 - 150, getHeight() / 2 - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Your Score: " + score, getWidth() / 2 - 100, getHeight() / 2 + 40);
        }
    }
    
}
