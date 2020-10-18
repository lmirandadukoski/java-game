package p25_0521909.dungeoncrawler.player;

import p25_0521909.dungeoncrawler.interfaces.Combatable;

/**
 *
 * @author ludmi
 */
public class PlayerStats implements Combatable{
    private int attackDamage;
    private double defenseModifier;
    private int baseHealthValue, currentHealthValue;

    public PlayerStats(){
        attackDamage = 1;
        defenseModifier = 1.0;
        baseHealthValue = 10;
        currentHealthValue = baseHealthValue;
    }
    
    @Override
    public void attack(Combatable combatable){
        combatable.takeDamage(attackDamage);
    }
    
    @Override
    public void takeDamage(int damageAmount){
        int newHealthValue = (int) (currentHealthValue - Math.round(damageAmount * defenseModifier));
        
        if(newHealthValue < 0){
            currentHealthValue = 0;
        }
        else{
            currentHealthValue = newHealthValue;
        } 
    }
    
    @Override
    public boolean isDead(){
        return currentHealthValue <= 0;
    }    
    
    @Override
    public int getCurrentHealth() {
        return currentHealthValue;
    }
    
    public void regenHealth(int regenAmount){
        int newHealthValue = currentHealthValue + regenAmount;
        
        if (newHealthValue > baseHealthValue){
            currentHealthValue = baseHealthValue;
        }
        else{
            currentHealthValue = newHealthValue;
        }    
    } 
}
