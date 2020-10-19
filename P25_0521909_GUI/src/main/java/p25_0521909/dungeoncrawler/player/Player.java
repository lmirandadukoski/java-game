package p25_0521909.dungeoncrawler.player;

import p25_0521909.dungeoncrawler.interfaces.Initialisable;
import p25_0521909.dungeoncrawler.item.*;

/**
 *
 * @author ludmi
 */
public class Player implements Initialisable{
    private final PlayerStats STATS;
    private final Inventory INVENTORY;
    
    private static Player instance;
    
    private Player(){
        STATS = new PlayerStats();
        INVENTORY = new Inventory(); 
    }
    
    @Override
    public void initialise() {
        INVENTORY.addItem(new HealthPotion(), ItemProperties.HEALTH_POTION_QUANTITY);
        INVENTORY.addItem(new DefensePotion(), ItemProperties.DEFENSE_POTION_QUANTITY);
        INVENTORY.addItem(new AttackPotion(), ItemProperties.ATTACK_POTION_QUANTITY);
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
        return INVENTORY;
    }
}
