package p25_0521909.dungeoncrawler.graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

/**
 *
 * @author ludmi
 */
public abstract class Sprite {
    private ImageIcon sprite;
    private Dimension spriteDimension;
    
    public Sprite(String spritePath){
        sprite = new ImageIcon(spritePath);
        spriteDimension = new Dimension(sprite.getIconWidth(), sprite.getIconHeight());
    }
    
    public Image getSpriteImage(){
        return sprite.getImage();
    } 
    
    public Dimension getSpriteDimension(){
        return spriteDimension;
    }
}
