package p25_0521909.dungeoncrawler.game;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import javax.swing.Timer;

import p25_0521909.dungeoncrawler.constants.Constants;

/**
 *
 * @author ludmi
 */
public class SpawnPoint extends Point implements ActionListener{
    private final Timer COOLDOWN_TIMER;
    
    private boolean isAvailable;    
    private Instant startTime;
    
    public SpawnPoint(int x, int y){
        this.x = x;
        this.y = y;
        
        isAvailable = true;
        COOLDOWN_TIMER = new Timer(Constants.SPAWN_POINT_COOLDOWN_DURATION, this);
    }
    
    @Override
    public Point getLocation(){
        return new Point(this.x, this.y);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Duration timeSinceCooldownStart = Duration.between(startTime, Instant.now());
        
        if(timeSinceCooldownStart.getSeconds() >= Constants.SPAWN_POINT_COOLDOWN_DURATION){
            isAvailable = true;
            COOLDOWN_TIMER.stop();
        }
    }
    
    public boolean isAvailable(){
        return isAvailable;
    }

    public void onCooldown(){
        startTime = Instant.now();
        isAvailable = false;
        COOLDOWN_TIMER.start();
    }
}
