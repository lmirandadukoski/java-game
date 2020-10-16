package P25_0521909.enemies;

import P25_0521909.data.DataRow;
import java.util.ArrayList;

/**
 * CommonEnemy are enemies in random encounters. There can be multiple
 * common enemies in a dungeon.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class CommonEnemy extends Enemy {

    /**
     * To construct a common enemy, the user needs to pass a row of
     * data containing the enemy's stats, and the data field names
     * contained in a database schema.
     * 
     * @param commonEnemyData
     * @param dataFields
     */    
    public CommonEnemy(DataRow commonEnemyData, ArrayList<String> dataFields){
        super(commonEnemyData, dataFields);        
    }
}
