import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GameScreen extends JPanel{
    private boolean running;
    private final GameRenderer renderer;
    private final Player player;
    private int score = 0;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Bullet> bullets;
    private boolean moveRight = true;

    public GameScreen() {
        running = true;
        //Creates a black background for the game
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        //Create renderer to render graphics on game screen
        renderer = new GameRenderer(this);

        // Create player at the given X and Y values
        player = GameEntityFactory.createPlayer(375, 550);
        //Create Enemy and Bullet array lists
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();

        //Add the number of inital enemies
        addenemies(2, 10, 1);
    }

    //Adds enemies to the Array list
    private void addenemies(int rows, int enemiesPerRow, int speed){
        enemies.clear();
        int startX = 50;
        int startY = 30;
        int xSpacing = 60;
        int ySpacing = 60;

        for (int row = 0; row < rows; row++){
            for(int col = 0; col < enemiesPerRow; col++){
                int x = startX+ col * xSpacing;
                int y = startY + row * ySpacing;
                enemies.add(GameEntityFactory.createEnemy(x, y, speed));
            }
        }
    }
    

    //Updates the game state
    public void update() {
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
                    if (enemies.isEmpty()){
                        score += 150;
                        int rows = 1 + enemy.getSpeed();
                        int speed = enemy.getSpeed() + 1;
                        addenemies(rows, 10, speed);
                    }
                    break;
                }
                
            }
        }
    }

    //Shoots a bullet from the centre of the player
    public void shootBullet() {
        int bulletX = player.getX() + player.getWidth() / 2-5;
        int bulletY = player.getY() - 10;
        Bullet bullet = GameEntityFactory.createBullet(bulletX, bulletY);
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

    //Getters and setters
    public Player getPlayer(){
        return this.player;
    }

    public ArrayList<Enemy> getEnemies(){
        return this.enemies;
    }

    public ArrayList<Bullet> getBullets(){
        return this.bullets;
    }

    public int getScore(){
        return this.score;
    }

    @Override
    protected void paintComponent(Graphics g){
        // Clear canvas before trying to repaint anything
        super.paintComponent(g);
        if (running){
            renderer.render(g);
        } else {
            // Called after running is toggled to false
            //Clear entities from screen, shows game over message, and displays user score
            renderer.renderGameOver(g);
        }
    }
    
}
