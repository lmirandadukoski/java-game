package P25_0521909.enemies;

import P25_0521909.data.DataRow;
import java.util.ArrayList;

/**
 * BossEnemy is the boss of the dungeon. There is only one
 * boss enemy per dungeon.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class BossEnemy extends Enemy {
    
    /**
     * To construct a boss enemy, the user needs to pass a row of
     * data containing the enemy's stats, and the data field names
     * contained in a database schema.
     * 
     * @param bossEnemyData
     * @param dataFields
     */
    public BossEnemy(DataRow bossEnemyData, ArrayList<String> dataFields){
        super(bossEnemyData, dataFields);        
    }
}
