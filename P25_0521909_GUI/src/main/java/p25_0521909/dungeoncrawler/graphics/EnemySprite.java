package p25_0521909.dungeoncrawler.graphics;

import java.awt.Point;

/**
 *
 * @author ludmi
 */
public class EnemySprite extends Sprite{
    private Point startPoint, targetPoint, currentLocation;
    
    public EnemySprite(String spritePath, Point startPoint, Point targetPoint) {
        super(spritePath);
        this.startPoint = startPoint;
        this.targetPoint = targetPoint;
        
        currentLocation = startPoint;
    }
    
    public Point getCurrentLocation(){
        return currentLocation;
    }

    public void move(){
        
    }
}
