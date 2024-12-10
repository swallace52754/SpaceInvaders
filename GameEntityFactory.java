public class GameEntityFactory {
    public static Player createPlayer(int x, int y){
        return new Player(x, y);
    }

    public static Enemy createEnemy(int x, int y, int speed){
        return new Enemy(x, y, speed);
    }

    public static Bullet createBullet(int x, int y){
        return new Bullet(x, y);
    }
}
