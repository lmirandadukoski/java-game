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
    private final Timer MOVE_TIMER;
    final GameEvent ENEMY_ATTACK_START;
    
    private final Sprite SPRITE;
    private final Point START_POINT, TARGET_POINT;
    private final double DELTA_X, DELTA_Y;   
    
    private Point currentLocation;
     
    public EnemyGraphics(Sprite sprite, Point startPoint, Point targetPoint) {
        this.SPRITE = sprite;
        this.START_POINT = startPoint;
        this.TARGET_POINT = targetPoint;
        currentLocation = this.START_POINT;
        
        DELTA_X = targetPoint.x - startPoint.x;
        DELTA_Y = targetPoint.y - startPoint.y; 
        
        ENEMY_ATTACK_START = new GameEvent(EventName.ENEMY_ATTACK);

        MOVE_TIMER = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));
        MOVE_TIMER.start();
    }
    
    @Override
    public void move(){
        if(DELTA_X == 0.0){
            currentLocation.setLocation(currentLocation.x, currentLocation.y + EnemyProperties.ENEMY_MOVE_SPEED * DELTA_Y);
        }
        
        else{            
            currentLocation.setLocation(currentLocation.x + EnemyProperties.ENEMY_MOVE_SPEED * DELTA_X, currentLocation.y + EnemyProperties.ENEMY_MOVE_SPEED * DELTA_Y);
        }

        if(currentLocation.distance(TARGET_POINT) <= 0.0){
            MOVE_TIMER.stop();
            ENEMY_ATTACK_START.invokeEvent();
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
        return SPRITE;
    }
}
