package P25_0521909.interfaces;

import P25_0521909.items.Item;

/**
 * IExchangeable represents an object that can be exchanged.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public interface IExchangeable {
    /**
    * Checks whether the user wants to exchange the object.
    * 
    */
    public void promptforExchange(Item item);
    
    /**
     * Exchanges the specified item.
     * 
     * @param item
     */
    public void exchange(Item item);
    
}
