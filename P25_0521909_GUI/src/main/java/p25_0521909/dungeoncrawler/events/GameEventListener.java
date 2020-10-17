package p25_0521909.dungeoncrawler.events;

import java.util.Observer;

/**
 *
 * @author ludmi
 */
public interface GameEventListener{
    
    public void addEventListener(String eventName, Observer observer);
    
}
