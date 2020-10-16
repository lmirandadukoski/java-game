package P25_0521909.items;

import P25_0521909.interfaces.IDebugable;
import P25_0521909.interfaces.IOpenable;

/**
 * Item is an object that a player or an NPC can acquire.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class Item implements IDebugable, IOpenable{
    private final String name, description;
    private final int purchaseCost;

    /**
     * To construct an item, the user needs to pass the item's
     * name, description, ad purchase cost.
     * 
     * @param name
     * @param description
     * @param purchaseCost
     */    
    public Item(String name, String description, int purchaseCost){
        this.name = name;
        this.description = description;
        this.purchaseCost = purchaseCost;
    }
    
    @Override
    public void open(){
        System.out.println("---------------------------------------------------------");
        System.out.println(name + ": " + description);
    }
    
    /**
     * @return the name of the item.
     */
    public String getName() {
        return name;
    }
    
    /**
     *
     * @return the purchase cost of the item.
     */
    public int getPurchaseCost(){
        return purchaseCost;
    }
    
    /**
     *
     * @return the type of the item.
     */
    public abstract String getType();

}
