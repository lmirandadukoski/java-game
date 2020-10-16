package p25_0521909.dungeoncrawler.interfaces;

/**
 *
 * @author ludmi
 */
public interface Combatable {
    
    public abstract void attack(Combatable combatable);
    
    public abstract void takeDamage(int damageAmount);
    
    public abstract boolean isDead();
    
}
