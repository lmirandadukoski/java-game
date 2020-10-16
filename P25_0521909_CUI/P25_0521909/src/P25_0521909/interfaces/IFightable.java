package P25_0521909.interfaces;

/**
 * IFightable represents an object that can be fought
 * against (i.e., attack).
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public interface IFightable {

    /**
     * The object attacks with a certain damage value.
     * 
     * @param damageAmount
     */
    public void attack(int damageAmount);
    
    /**
     * 
     * @return the object's attack value.
     * 
     */
    public int getAttackDamage();
    
}
