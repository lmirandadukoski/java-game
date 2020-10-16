package p25_0521909.dungeoncrawler.graphics;

import java.awt.Point;
import p25_0521909.dungeoncrawler.game.SpawnPoint;

/**
 *
 * @author ludmi
 */
public class EnemySprite extends Sprite{
    private Point spawnPoint, targetPoint, currentLocation;
    private boolean isSpawned;
    
    public EnemySprite(String spritePath) {
        super(spritePath);
        isSpawned = false;
    }

    public void setSpawnPoint(SpawnPoint point){
        spawnPoint = point;
        
        if(currentLocation == null){
            currentLocation = new Point(point.x, point.y);
        }
    }    
    
    public void setTargetPoint(Point point){
        targetPoint = point;
    }
    
    public Point getCurrentLocation(){
        return currentLocation;
    }
    
    public void spawn(){
        isSpawned = true;
    }
    
    public boolean getIsSpawned(){
        return isSpawned;
    }
    
    public void move(){
        
    }
}
