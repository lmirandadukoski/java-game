package p25_0521909.dungeoncrawler.graphics;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author ludmi
 */
public class Sprite {
    private final ImageIcon SPRITE;
    private final Dimension SPRITE_DIMENSION;
    
    public Sprite(String spritePath){
        SPRITE = new ImageIcon(spritePath);
        SPRITE_DIMENSION = new Dimension(SPRITE.getIconWidth(), SPRITE.getIconHeight());
    }
    
    public Image getSpriteImage(){
        return SPRITE.getImage();
    } 
    
    public Dimension getSpriteDimension(){
        return SPRITE_DIMENSION;
    }
}
