package p25_0521909.dungeoncrawler.constants;

/**
 *
 * @author ludmi
 */
public enum EventName {
    START_GAME ("START_GAME"),
    STOP_GAME ("STOP_GAME");
    
    private final String name;
    EventName(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
