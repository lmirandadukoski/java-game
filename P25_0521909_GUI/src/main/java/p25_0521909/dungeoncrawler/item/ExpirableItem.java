package p25_0521909.dungeoncrawler.item;

import java.time.Instant;
import javax.swing.Timer;
import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.game.GameLoop;

import p25_0521909.dungeoncrawler.interfaces.Loopable;

/**
 *
 * @author ludmi
 */
public abstract class ExpirableItem extends Item implements Loopable{
    private final long EFFECT_DURATION;
    
    final Timer timer;
    Instant timerStart;
    
    public ExpirableItem(String name, String description, long effectDuration) {
        super(name, description);
        EFFECT_DURATION = effectDuration;
        
        timer = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));
    }
    
    public long getEffectDuration(){
        return EFFECT_DURATION;
    }
}
