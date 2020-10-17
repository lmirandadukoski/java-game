package p25_0521909.dungeoncrawler.events;

import java.util.Observer;
import p25_0521909.dungeoncrawler.constants.EventName;

/**
 *
 * @author ludmi
 */
public interface GameEventListener{
    
    public void addEventListener(EventName eventName, Observer observer);
    
}
