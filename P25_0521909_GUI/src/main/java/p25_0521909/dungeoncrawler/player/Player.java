package p25_0521909.dungeoncrawler.player;

import p25_0521909.dungeoncrawler.item.Inventory;

/**
 *
 * @author ludmi
 */
public class Player {
    private Inventory inventory;
    private PlayerStats stats;
    
    private static Player instance;
    
    private Player(){
        stats = new PlayerStats();
    }
    
    public void initialiseValues(){
        
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
        return stats;
    }
    
    public Inventory getInventory(){
        return inventory;
    }
}
