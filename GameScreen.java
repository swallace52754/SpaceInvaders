import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameScreen extends JPanel implements Runnable {
    private boolean running;
    private Thread gameThread;
    private Player player;

    public GameScreen() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        player = new Player(375, 550);

        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> player.moveLeft();
                    case KeyEvent.VK_RIGHT -> player.moveRight();
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
            // try{
            //     Thread.sleep(16);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
    }

    private void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        player.draw(g);
    }
    
}
