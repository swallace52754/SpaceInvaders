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

        //Call to start the game
        gameScreen.startGame();

    }

}
