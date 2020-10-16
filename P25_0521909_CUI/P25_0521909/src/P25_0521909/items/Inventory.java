package P25_0521909.items;

import P25_0521909.interfaces.IDebugable;
import P25_0521909.interfaces.IInitialisable;
import P25_0521909.interfaces.IOpenable;
import java.util.*;

/**
 * Inventory is a collection of items.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class Inventory implements IDebugable, IInitialisable, IOpenable{
    HashMap<Item, Integer> inventory;   // The collection that holds items and item quantities.
    
    public Inventory(){this.inventory = new HashMap<>();}
    
    /**
     *
     * @return the collection of items.
     * 
     */
    public HashMap<Item, Integer> getInventory(){
        return inventory;
    }

    /**
     *
     * @param index
     * @return an items by it's index location in the collection.
     * 
     */
    public Item getItemByIndex(int index){
        Item temp = null;
        int i = 0;
        for(Map.Entry<Item,Integer> entry: inventory.entrySet()){
            if (i == (index)){
                temp = entry.getKey();
                break;
            }
            i += 1;
        } 

        return temp;
    }
    
    /**
     * Adds an item to the inventory.
     * 
     * @param item
     */
    public void add(Item item){
        // Checks if the item is contained in the inventory.
        if (containsItem(item)){
            // If the inventory contains the item, the quantity of the item
            // in the inventory is increased.
            Item inventoryItem = getItem(item.getName());
            int quantity = inventory.get(inventoryItem);
            inventory.replace(inventoryItem, quantity + 1);
        }
        else{
            // If the inventory doesn't contain the item, it's added.
            inventory.put(item, 1);
        }   
    }
    
    /**
     * Removes an item from the inventory.
     * 
     * @param item
     */
    public void remove(Item item){
        // Checks if the item is contained in the inventory.
        if(containsItem(item)){
            // If the inventory contains the item, the quantity of the item
            // in the inventory is decreased or removed entirely.            
            Item inventoryItem = getItem(item.getName());
            int quantity = inventory.get(inventoryItem);
            if(quantity == 1){
                inventory.remove(item);
            }
            else{
                inventory.replace(item, quantity - 1);
            }
        }    
    }
    
    /**
     * 
     * @param item
     * @return whether the inventory contains the item or not.
     */    
    private boolean containsItem(Item item){
        boolean containsKey = false;
        String searchItemName = item.getName();
        
        for(Map.Entry<Item, Integer> entry : inventory.entrySet()){
            if(entry.getKey().getName().equals(searchItemName)){
                containsKey = true;
                break;
            }        
        }
        
        return containsKey;
    }

    /**
     * 
     * @param itemName
     * @return an item matching the item name searched for.
     */      
    private Item getItem(String itemName){
        Item temp = null;
        
        for(Map.Entry<Item, Integer> entry : inventory.entrySet()){
            if(entry.getKey().getName().equals(itemName)){
                temp = entry.getKey();
                break;
            }        
        }        
        return temp;
    }
    
}
