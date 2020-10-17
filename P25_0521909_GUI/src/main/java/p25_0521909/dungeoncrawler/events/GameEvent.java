package p25_0521909.dungeoncrawler.events;

import java.util.Observable;
import p25_0521909.dungeoncrawler.constants.EventName;

/**
 *
 * @author ludmi
 */
public class GameEvent extends Observable{
    private EventName name;
    
    public GameEvent(EventName name){
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        EventName name = (EventName) o;
        
        boolean hasSameName = this.getEventName().toString().equals(name.toString());
        
        return hasSameName;       
    }
    
    public EventName getEventName(){
        return name;
    }
    
    public void invokeEvent() {
        setChanged();
        notifyObservers();
        clearChanged();
    }
}
