package p25_0521909.dungeoncrawler.enemy;

import java.util.Observer;
import p25_0521909.dungeoncrawler.constants.EventName;
import p25_0521909.dungeoncrawler.events.GameEventListener;

/**
 *
 * @author ludmi
 */
public class Enemy{
    private String name;
    
    private EnemyStats stats;
    private EnemyGraphics graphics;
    
    public Enemy(String name, EnemyStats stats, EnemyGraphics graphics) {
        this.name = name;
        this.stats = stats;
        this.graphics = graphics;
        
        graphics.enemyAttackStart.addObserver(stats);
    }

    public EnemyGraphics getGraphics(){
        return graphics;
    }
    
    public EnemyStats getStats(){
        return stats;
    }    
}
