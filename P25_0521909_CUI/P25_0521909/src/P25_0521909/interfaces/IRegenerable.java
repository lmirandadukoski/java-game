package P25_0521909.interfaces;

/**
 * IRegenerable represents an object that can regenerate
 * health.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public interface IRegenerable {
    
    /**
     * Regenerates health by a certain amount.
     * 
     * @param regenAmount
     */
    public void regen(int regenAmount);   
}
