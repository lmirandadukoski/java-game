package p25_0521909.dungeoncrawler.graphics;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author ludmi
 */
public abstract class Sprite {
    private Image sprite;
    
    public Sprite(String spritePath){
        sprite = new ImageIcon(spritePath).getImage();
    }
    
    public Image getSpriteImage(){
        return sprite;
    }  
}
