package p25_0521909.dungeoncrawler.item;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ludmi
 */
public class Inventory {
    
    private HashMap<Item, Integer> inventory;
    
    public void addItem(Item item, int qty){
        if(inventory.containsKey(item)){
            int quantity = inventory.get(item) + qty;
            inventory.replace(item, quantity);
        }
        
        else{
            inventory.put(item, qty);
        }
    }
    
    public void removeItem(Item item){
        if(inventory.containsKey(item)){
            int quantity = inventory.get(item) - 1;
            
            if(quantity <= 0){
                inventory.remove(item);
            }            
            else{
                inventory.replace(item, quantity);
            }
        }        
    }
}
