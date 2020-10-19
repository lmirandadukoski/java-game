package p25_0521909.dungeoncrawler.constants;

/**
 *
 * @author ludmi
 */
public enum PanelName {
    START ("START"),
    BATTLE ("BATTLE"),
    WIN ("WIN"),
    LOSS ("LOSS");
    
    private final String name;
    PanelName(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
