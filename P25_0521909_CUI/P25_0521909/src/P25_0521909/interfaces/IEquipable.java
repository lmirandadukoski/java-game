package P25_0521909.interfaces;

/**
 * IEquipable represents an object that can be equipped by
 * the player.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public interface IEquipable {
    
    /**
    * Checks whether the user wants to equip the object.
    * 
    */
    public void promptForEquipping();
    
    /**
     * Equips the object.
     * 
     */
    public void equip();
    
    /**
     * Unequips the object.
     * 
     */
    public void unequip();
    
}
