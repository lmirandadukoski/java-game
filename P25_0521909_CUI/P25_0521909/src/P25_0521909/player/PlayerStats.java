package P25_0521909.player;

import P25_0521909.data.DataRow;
import P25_0521909.interfaces.IDebugable;
import java.util.ArrayList;

/**
 * PlayerStats represents all of the player's in-game stats, such as attack value,
 * health value, and gold amount.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class PlayerStats implements IDebugable {
    private final int baseHealthValue;  // The amount of health the player starts the game with.    
    private final int baseAttackValue;  // The base attack value of the player.
    
    private int goldValue;              // The amount of gold the player has.    
    private int currentHealthValue;     // The current health of the player.
    
    /**
     * To construct a player stats, the user needs to pass a row of
     * data containing the player's base stats, and the data field names
     * contained in a database schema.
     * 
     * @param playerStatsData
     * @param dataFields
     */    
    public PlayerStats(DataRow playerStatsData, ArrayList<String> dataFields){
        baseAttackValue = Integer.parseInt((String)playerStatsData.getDatum(dataFields.indexOf("attackValue")));
        baseHealthValue = Integer.parseInt((String)playerStatsData.getDatum(dataFields.indexOf("healthValue")));
        goldValue = Integer.parseInt((String)playerStatsData.getDatum(dataFields.indexOf("goldStash")));
        this.currentHealthValue = baseHealthValue;
    }
    
    @Override
    public void printInfo() {
        System.out.println("Attack Value: " + baseAttackValue);
        System.out.println("Health Value: " + baseHealthValue);
        System.out.println("Gold Stash: " + goldValue);        
    }  
    
    /**
     *
     * @return the base attack value of the player.
     */
    protected int getBaseAttackValue(){
        return baseAttackValue;
    }
    
    /**
     *
     * @return the current health of the player.
     */
    protected int getCurrentHealthValue(){
        return currentHealthValue;
    }
    
    /**
     * Increases the current health of the player up to their max health.
     * 
     * @param value
     */
    protected void increaseCurrentHealthValue(int value){
        int newHealthValue = currentHealthValue + value;
        
        if(newHealthValue > baseHealthValue){
            currentHealthValue = baseHealthValue;
        }
        else{
            currentHealthValue = newHealthValue;
        }
    }
    
    /**
     * Decreases the current health of the player up to 0.
     * 
     * @param value
     */
    protected void decreaseCurrentHealthValue(int value){           
        int newHealthValue = currentHealthValue - value;
        
        if(newHealthValue < 0){
            currentHealthValue = 0;
        }
        else{
            currentHealthValue = newHealthValue;
        }
    }
    
    /**
     *
     * @return the base or max health of the player.
     */
    public int getBaseHealthValue(){
        return baseHealthValue;
    }
    
    /**
     *
     * @return the amount of gold the player has.
     */
    public int getGoldValue(){
        return goldValue;
    }
    
    /**
     * Increases the amount of gold the player has by a value.
     * 
     * @param value
     */
    public void increaseGoldValue(int value){
        goldValue += value;
    }
    
    /**
     * Decreases the amount of gold the player has by a value.
     * 
     * @param value
     */
    public void decreaseGoldValue(int value){
        goldValue -= value;
    }
    
}
