package p25_0521909.dungeoncrawler.enemy;

import java.awt.Point;
import p25_0521909.dungeoncrawler.graphics.EnemySprite;

/**
 *
 * @author ludmi
 */
public class Enemy {
    private String enemyName;
    
    private EnemyStats stats;
    private EnemySprite enemySprite;
    
    public Enemy(String enemyName) {
        this.enemyName = enemyName;
        stats = new EnemyStats();
        
        enemySprite = new EnemySprite("sprites/enemy2.png");
        enemySprite.setTargetPoint(new Point(470, 420));
    }
    
    public EnemySprite getEnemySprite(){
        return enemySprite;
    }
    
    public EnemyStats getEnemyStats(){
        return stats;
    }
    
}
