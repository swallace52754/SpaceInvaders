import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

//Handles the images for the game
public class ImageLoader {
    public static Image loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
