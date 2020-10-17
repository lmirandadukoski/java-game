package p25_0521909.dungeoncrawler.game;

import java.awt.*;
import java.time.*;
import java.util.*;
import javax.swing.Timer;

import p25_0521909.dungeoncrawler.constants.*;
import p25_0521909.dungeoncrawler.enemy.Enemy;
import p25_0521909.dungeoncrawler.enemy.EnemyStats;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.graphics.EnemySprite;
import p25_0521909.dungeoncrawler.interfaces.Initialisable;
import p25_0521909.dungeoncrawler.interfaces.Loopable;

/**
 *
 * @author ludmi
 */
public class EnemyController implements Initialisable, Loopable, Observer{
    public ArrayList<Enemy> enemies;

    private SpawnPoint[] spawnPoints;
    private Point[] targetPoints;
    private Timer timer;
    
    private Instant lastSpawnTime;

    public EnemyController(){
        spawnPoints = new SpawnPoint[]{Constants.SPAWN_POINT_1, Constants.SPAWN_POINT_2, Constants.SPAWN_POINT_3}; 
        targetPoints = new Point[]{Constants.TARGET_POINT_1, Constants.TARGET_POINT_2, Constants.TARGET_POINT_3};
        
        initialise();
    }
    
    @Override
    public void initialise(){
        timer = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));        
        enemies = new ArrayList<Enemy>(); 
        
        GameManager.getInstance().addEventListener(EventName.START_GAME, this);
        GameManager.getInstance().addEventListener(EventName.STOP_GAME, this);
    }    

    @Override
    public void loop() {
        Duration timeSinceSpawn = Duration.between(lastSpawnTime, Instant.now());
        
        if(timeSinceSpawn.getSeconds() >= Constants.ENEMY_SPAWN_RATE){
            int spawnAttempts = 0;
            while(spawnAttempts < Constants.SPAWN_ATTEMPTS){
                int index = (int) (Math.random() * spawnPoints.length);
                
                if(spawnPoints[index].isAvailable()){
                    Enemy enemy = spawnEnemy(spawnPoints[index].getLocation(), targetPoints[index]);
                    enemies.add(enemy);
                    spawnPoints[index].onCooldown();
                    lastSpawnTime = Instant.now();                    
                    break;
                }
                
                else{
                    spawnAttempts++;
                }
            }
        }
        
        if(enemies.size() > 0){
            Iterator<Enemy> iterator = enemies.iterator();
            
            while(iterator.hasNext()){
                Enemy enemy = iterator.next();
                if(enemy.getStats().isDead()){
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvent event = (GameEvent) o;
        
        if(event.equals(EventName.START_GAME)){
            timer.start();
            lastSpawnTime = Instant.now();
        } 
        
        if(event.equals(EventName.STOP_GAME)){
            timer.stop();
        } 
    }

    private Enemy spawnEnemy(Point spawnPoint, Point targetPoint) {
        String name = "Enemy 1";
        EnemyStats stats = new EnemyStats(1, 3);
        EnemySprite sprite = new EnemySprite("sprites/enemy1.png", spawnPoint, targetPoint);
        
        return new Enemy(name, stats, sprite);
    }
}
