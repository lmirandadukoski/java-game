package p25_0521909.dungeoncrawler.item;

/**
 * Item is an object that a player or an NPC can acquire.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class Item{
    private final String name, description;

    /**
     * To construct an item, the user needs to pass the item's
     * name, and description.
     * 
     * @param name
     * @param description
     */    
    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }
    
    public abstract void consume();
}
