public class GameEngine implements Runnable{
    private boolean running;
    private final GameScreen gameScreen;

    //Handles the game loop
    public GameEngine(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    public void start(){
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            gameScreen.update();
            gameScreen.repaint();
            try{
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        running = false;
    }
    
}
