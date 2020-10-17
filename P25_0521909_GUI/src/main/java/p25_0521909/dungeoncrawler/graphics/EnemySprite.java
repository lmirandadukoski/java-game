package p25_0521909.dungeoncrawler.graphics;

import java.awt.Point;
import java.util.Vector;
import javax.swing.Timer;
import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.game.GameLoop;
import p25_0521909.dungeoncrawler.interfaces.Loopable;
import p25_0521909.dungeoncrawler.interfaces.Movable;

/**
 *
 * @author ludmi
 */
public class EnemySprite extends Sprite implements Movable, Loopable{
    private Point startPoint, targetPoint, currentLocation;
    private Timer timer;
    
    public EnemySprite(String spritePath, Point startPoint, Point targetPoint) {
        super(spritePath);
        this.startPoint = startPoint;
        this.targetPoint = targetPoint;
        
        currentLocation = startPoint;
        timer = new Timer(Constants.ENEMY_MOVE_SPEED, new GameLoop(this));
        timer.start();
    }
    
    @Override
    public void move(){
        double norm = Math.sqrt((targetPoint.x - startPoint.x) * (targetPoint.x - startPoint.x) + (targetPoint.y - startPoint.y) * (targetPoint.y - startPoint.y)); 
        double xdir = (targetPoint.x - startPoint.x) / norm;
        double ydir = (targetPoint.y - startPoint.y) / norm;
        
        currentLocation.x = startPoint.x + 10 * (int) xdir;
        currentLocation.y = startPoint.y + 10 * (int) ydir;
        
        if(currentLocation == targetPoint){
            timer.stop();
        }
    }   
    
    @Override
    public void loop() {
        move();
    }  
    
    public Point getCurrentLocation(){
        return currentLocation;
    }


}
