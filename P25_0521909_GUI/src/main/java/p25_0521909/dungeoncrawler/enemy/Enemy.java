package p25_0521909.dungeoncrawler.enemy;

/**
 *
 * @author ludmi
 */
public class Enemy {
    private String name;
    
    private EnemyStats stats;
    private EnemyGraphics graphics;
    
    public Enemy(String name, EnemyStats stats, EnemyGraphics graphics) {
        this.name = name;
        this.stats = stats;
        this.graphics = graphics;
    }
    
    public EnemyGraphics getGraphics(){
        return graphics;
    }
    
    public EnemyStats getStats(){
        return stats;
    }
    
}
