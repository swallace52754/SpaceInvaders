import javax.swing.*;

public class Main {
    
    public static void main(String[] args) {
        //Create frame to hold game screen 
        JFrame frame = new JFrame("Space Invaders");
        GameScreen gameScreen = new GameScreen();

        //Settings for frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameScreen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        GameInputHandler inputHandler = new GameInputHandler(gameScreen.getPlayer(), gameScreen);
        gameScreen.addKeyListener(inputHandler);
        gameScreen.setFocusable(true);
        gameScreen.requestFocusInWindow();

        //Call to start the game
        GameEngine gameEngine = new GameEngine(gameScreen);
        gameEngine.start();
    }

}
