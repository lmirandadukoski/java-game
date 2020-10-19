package p25_0521909.dungeoncrawler.enemy;

/**
 *
 * @author ludmi
 */
public class Enemy{
    private final String NAME;
    private final EnemyStats STATS;
    private final EnemyGraphics GRAPHICS;
    
    public Enemy(String name, EnemyStats stats, EnemyGraphics graphics) {
        this.NAME = name;
        this.STATS = stats;
        this.GRAPHICS = graphics;
        
        graphics.ENEMY_ATTACK_START.addObserver(stats);
    }

    public EnemyGraphics getGraphics(){
        return GRAPHICS;
    }
    
    public EnemyStats getStats(){
        return STATS;
    }    
}
