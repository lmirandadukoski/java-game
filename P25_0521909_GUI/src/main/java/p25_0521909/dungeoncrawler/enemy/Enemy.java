package p25_0521909.dungeoncrawler.enemy;

import java.awt.Point;
import p25_0521909.dungeoncrawler.graphics.EnemySprite;

/**
 *
 * @author ludmi
 */
public class Enemy {
    private String name;
    
    private EnemyStats stats;
    private EnemySprite sprite;
    
    public Enemy(String name, EnemyStats stats, EnemySprite sprite) {
        this.name = name;
        this.stats = stats;
        this.sprite = sprite;
    }
    
    public EnemySprite getSprite(){
        return sprite;
    }
    
    public EnemyStats getStats(){
        return stats;
    }
    
}
