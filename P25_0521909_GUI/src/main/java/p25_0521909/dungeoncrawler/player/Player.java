package p25_0521909.dungeoncrawler.player;

import p25_0521909.dungeoncrawler.interfaces.Initialisable;
import p25_0521909.dungeoncrawler.item.Inventory;

/**
 *
 * @author ludmi
 */
public class Player {
    private final PlayerStats STATS;
    private Inventory inventory;
    
    private static Player instance;
    
    private Player(){
        STATS = new PlayerStats();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
        
    public static synchronized Player getInstance(){
        if(instance == null){
            instance = new Player();
        }
        return instance;
    }

    public PlayerStats getStats(){
        return STATS;
    }
    
    public Inventory getInventory(){
        return inventory;
    }
}
