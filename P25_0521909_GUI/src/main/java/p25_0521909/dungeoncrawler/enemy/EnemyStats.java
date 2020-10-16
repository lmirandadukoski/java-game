package p25_0521909.dungeoncrawler.enemy;

import p25_0521909.dungeoncrawler.interfaces.Combatable;

/**
 *
 * @author ludmi
 */
public class EnemyStats implements Combatable{
    private int attackDamage, healthValue;
    
    EnemyStats(){
        attackDamage = 1;
        healthValue = 3;
    }
    
    @Override
    public void attack(Combatable combatable) {
        combatable.takeDamage(attackDamage);
    }

    @Override
    public void takeDamage(int damageAmount) {
        int newHealthValue = healthValue - damageAmount;
        
        if(newHealthValue < 0){
            healthValue = 0;
        }
        else{
            healthValue = newHealthValue;
        } 
    }

    @Override
    public boolean isDead() {
        return healthValue <= 0;
    }    
}
