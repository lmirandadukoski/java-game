package p25_0521909.dungeoncrawler.events;

import java.util.Observable;

/**
 *
 * @author ludmi
 */
public class GameEvent extends Observable{
    protected String eventName;
    
    public GameEvent(String eventName){
        this.eventName = eventName;
    }
    
    public String getEventName(){
        return eventName;
    }
    
    public void invokeEvent() {
        setChanged();
        notifyObservers();
        clearChanged();
    }
}
