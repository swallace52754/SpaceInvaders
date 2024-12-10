
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Handles keyboard input for the game
public class GameInputHandler extends KeyAdapter{
    private final Player player;
    private final GameScreen gameScreen;

    public GameInputHandler(Player player, GameScreen gameScreen){
        this.player = player;
        this.gameScreen = gameScreen;
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> player.moveLeft();
            case KeyEvent.VK_RIGHT -> player.moveRight();
            case KeyEvent.VK_SPACE -> gameScreen.shootBullet();
        }
    }
}
