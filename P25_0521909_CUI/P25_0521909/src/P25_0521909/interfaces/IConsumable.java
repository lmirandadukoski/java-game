package P25_0521909.interfaces;

/**
 * ICosumable represents an object that can be consumed by
 * the player.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public interface IConsumable {
    
    /**
     * Checks whether the user wants to consume the object.
     * 
     */
    public void promptForConsumption();
    
    /**
     * Consumes the object.
     * 
     */
    public void consume();
    
}
