package p25_0521909.dungeoncrawler.item;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ludmi
 */
public class Inventory {
    
    private HashMap<Item, Integer> inventory;
    
    public Inventory(){
        inventory = new HashMap<Item, Integer>();
    }
    
    public Item getItemByName(String name){
        Item item = null;
        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            item = entry.getKey();
            if(item.getName().equals(name)){
                break;
            }
        }
        
        return item;
    }
    
    public int getItemQuantity(Item item){
        return inventory.get(item);
    }
                
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
