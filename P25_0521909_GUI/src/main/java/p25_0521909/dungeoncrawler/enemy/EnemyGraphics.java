package p25_0521909.dungeoncrawler.enemy;

import java.awt.Point;
import javax.swing.Timer;
import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.constants.EventName;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.game.GameLoop;
import p25_0521909.dungeoncrawler.graphics.Sprite;
import p25_0521909.dungeoncrawler.interfaces.Loopable;
import p25_0521909.dungeoncrawler.interfaces.Movable;

/**
 *
 * @author ludmi
 */
public class EnemyGraphics implements Movable, Loopable{
    private final Sprite sprite;
    private final Point startPoint, targetPoint, currentLocation;
    private final double deltaX, deltaY;    
   
    private Timer timer;
    
    final GameEvent enemyAttackStart;
    
    public EnemyGraphics(Sprite sprite, Point startPoint, Point targetPoint) {
        this.sprite = sprite;
        this.startPoint = startPoint;
        this.targetPoint = targetPoint;
        currentLocation = this.startPoint;
        
        deltaX = targetPoint.x - startPoint.x;
        deltaY = targetPoint.y - startPoint.y; 
        
        enemyAttackStart = new GameEvent(EventName.ENEMY_ATTACK);

        timer = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));
        timer.start();
    }
    
    @Override
    public void move(){
        if(deltaX == 0.0){
            currentLocation.setLocation(currentLocation.x, currentLocation.y + Constants.ENEMY_MOVE_SPEED * deltaY);
        }
        
        else{            
            currentLocation.setLocation(currentLocation.x + Constants.ENEMY_MOVE_SPEED * deltaX, currentLocation.y + Constants.ENEMY_MOVE_SPEED * deltaY);
        }

        if(currentLocation.distance(targetPoint) <= 0.0){
            timer.stop();
            enemyAttackStart.invokeEvent();
        }
    }   
    
    @Override
    public void loop() {
        move();
    }  
    
    public Point getCurrentLocation(){
        return currentLocation;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
