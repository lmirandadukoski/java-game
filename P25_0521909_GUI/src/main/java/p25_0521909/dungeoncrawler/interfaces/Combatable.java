package p25_0521909.dungeoncrawler.interfaces;

/**
 *
 * @author ludmi
 */
public interface Combatable {
    
    public void attack(Combatable combatable);
    
    public void takeDamage(int damageAmount);
    
    public boolean isDead();
    
    public int getCurrentHealth();
}
