package p25_0521909.dungeoncrawler.player;

import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.interfaces.Combatable;

/**
 *
 * @author ludmi
 */
public class PlayerStats implements Combatable{   
    private double defenseModifier;    
    private int currentHealthValue;

    public PlayerStats(){
        defenseModifier = Constants.PLAYER_BASE_DEFENSE;        
        currentHealthValue = Constants.PLAYER_HEALTH_VALUE;
    }
    
    @Override
    public void attack(Combatable combatable){
        combatable.takeDamage(Constants.PLAYER_ATTACK_VALUE);
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
    
    public void setDefenseModifier(double value){
        defenseModifier = value;
    }
    
    public void regenHealth(int regenAmount){
        int newHealthValue = currentHealthValue + regenAmount;
        
        if (newHealthValue > Constants.PLAYER_HEALTH_VALUE){
            currentHealthValue = Constants.PLAYER_HEALTH_VALUE;
        }
        else{
            currentHealthValue = newHealthValue;
        }    
    } 
}
