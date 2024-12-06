import javax.swing.*;

public class Main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        GameScreen gameScreen = new GameScreen();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameScreen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        gameScreen.startGame();

    }

}
