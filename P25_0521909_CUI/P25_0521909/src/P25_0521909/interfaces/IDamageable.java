package P25_0521909.interfaces;

/**
 * IDamageable represents an object that can be damaged
 * (i.e., have their health value decreased).
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public interface IDamageable {
    
    /**
     * Calls for the user to take a certain amount of
     * damage.
     * 
     * @param damageAmount
     */
    public void takeDamage(int damageAmount);
    
    /**
     *
     * @return whether the object has reached a health of 0 or not.
     * 
     */
    public boolean isDead();
    
}
