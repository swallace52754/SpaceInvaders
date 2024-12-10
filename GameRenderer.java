import java.awt.*;

//Renders entities and score for the game
public class GameRenderer {
    private final GameScreen gameScreen;

    public GameRenderer(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    public void render(Graphics g){
        gameScreen.getPlayer().draw(g);

        //Loop through the Enemy array, drawing each enemy
        for(Enemy enemy : gameScreen.getEnemies()){
            enemy.draw(g);
        }

        // Draw the bullets on the screen
        for(Bullet bullet : gameScreen.getBullets()){
            bullet.draw(g);
        }

        //Show the player's score in the corner of the screen
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + gameScreen.getScore(), 10, 20);
    }
    
    public void renderGameOver(Graphics g){
        //Clears the screen and displays the user's score and a "game over" message
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gameScreen.getWidth(), gameScreen.getHeight());
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", gameScreen.getWidth() / 2 - 150, gameScreen.getHeight() / 2 - 20);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Your Score: " + gameScreen.getScore(), gameScreen.getWidth() / 2 - 100, gameScreen.getHeight() / 2 + 40);
    }
}
