package p25_0521909.dungeoncrawler.item;

/**
 * Item is an object that a player or an NPC can acquire.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class Item{
    private final String NAME, DESCRIPTION;

    /**
     * To construct an item, the user needs to pass the item's
 NAME, and DESCRIPTION.
     * 
     * @param name
     * @param description
     */    
    public Item(String name, String description){
        NAME = name;
        DESCRIPTION = description;
    }
    
    public String getName(){
        return NAME;
    }

    public String getDescription(){
        return DESCRIPTION;
    }
    
    public abstract void consume();
}
