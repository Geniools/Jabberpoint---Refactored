package presentation;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


/**
 * <p>The class for a Bitmap item</p>
 * <p>Bitmap items are responsible for drawing themselves.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class BitmapItem extends SlideItem {
    protected static final String FILE = "File ";
    protected static final String NOTFOUND = " not found";
    private BufferedImage bufferedImage;
    private ImageObserver imageObserver;

    private String imageName;


    //level indicates the item-level; name indicates the name of the file with the image
    public BitmapItem(int level, String name) {
        super(level);
        imageName = name;
        try {
            bufferedImage = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            System.err.println(FILE + imageName + NOTFOUND);
        }
    }

    //Returns the filename of the image
    public String getName() {
        return this.imageName;
    }

    public void setImageObserver(ImageObserver imageObserver) {
        this.imageObserver = imageObserver;
    }

    //Returns the bounding box of the image
    @Override
    public Rectangle getBoundingBox(Graphics g, float scale, Style myStyle) {
        return new Rectangle((int) (myStyle.getIndent() * scale), 0,
                (int) (bufferedImage.getWidth() * scale),
                ((int) (myStyle.getLeading() * scale)) +
                        (int) (bufferedImage.getHeight() * scale));
    }

    //Draws the image
    @Override
    public void draw(int x, int y, float scale, Graphics graphics, Style myStyle) {
        int width = x + (int) (myStyle.getIndent() * scale);
        int height = y + (int) (myStyle.getLeading() * scale);
        graphics.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth() * scale),
                (int) (bufferedImage.getHeight() * scale), this.imageObserver);
    }

    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
