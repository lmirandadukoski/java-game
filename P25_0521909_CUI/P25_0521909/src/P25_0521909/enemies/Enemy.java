package P25_0521909.enemies;

import P25_0521909.interfaces.IDebugable;
import P25_0521909.interfaces.IDamageable;
import P25_0521909.interfaces.IFightable;
import P25_0521909.data.DataRow;
import java.util.ArrayList;

/**
 * Enemy represents the blueprint for enemies in the dungeon.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class Enemy implements IDamageable, IFightable, IDebugable{   
    private final String enemyName;
    private final int attackValue;
    private int healthValue;
    private final int rewardValue;
    private final String attackName;
    
    /**
     * To construct an enemy, the user needs to pass a row of
     * data containing the enemy's stats, and the data field names
     * contained in a database schema.
     * 
     * @param enemyData
     * @param dataFields
     */
    public Enemy(DataRow enemyData, ArrayList<String> dataFields){
        enemyName = (String) enemyData.getDatum(dataFields.indexOf("enemyName"));
        attackValue = Integer.parseInt((String)enemyData.getDatum(dataFields.indexOf("attackValue")));
        rewardValue = Integer.parseInt((String)enemyData.getDatum(dataFields.indexOf("rewardValue")));
        attackName = (String) enemyData.getDatum(dataFields.indexOf("attackName"));
        
        healthValue = Integer.parseInt((String)enemyData.getDatum(dataFields.indexOf("healthValue")));
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
        
        System.out.println(enemyName + "'s health is now " + healthValue + "!");
    }

    @Override
    public boolean isDead() {
        return healthValue <= 0;
    }

    @Override
    public void attack(int damageAmount) {
        System.out.println(enemyName + " attacks with " + attackName + "!");
        System.out.println(enemyName + " deals " + damageAmount + " damage.");
    }

    @Override
    public int getAttackDamage() {
        return attackValue;
    }
    
    @Override
    public void printInfo(){
        System.out.println("Enemy Name: " + enemyName);
        System.out.println("Attack Value: " + attackValue);
        System.out.println("Health Value: " + healthValue);
        System.out.println("Reward Value: " + rewardValue);
        System.out.println("Attack Name: " + attackName);
    }
    
    /**
     * @return the enemy's name.
     */
    public String getName() {
        return enemyName;
    }

    /**
     * @return the amount of gold the user gets for defeating the enemy.
     */
    public int getRewardValue() {
        return rewardValue;
    }
}
