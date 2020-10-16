package p25_0521909.dungeoncrawler.game;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;
import p25_0521909.dungeoncrawler.enemy.Enemy;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.interfaces.Updatable;

/**
 *
 * @author ludmi
 */
public class EnemyController implements Updatable, Observer{
    public ArrayList<Enemy> enemies;
    
    private SpawnPoint[] spawnPoints;
    private Timer timer;
    
    private Instant lastSpawnTime;
    private long spawnRate = 3;

    public EnemyController(){
        spawnPoints = new SpawnPoint[]{new SpawnPoint(270, 320), new SpawnPoint(470, 330), new SpawnPoint(670, 320)}; 
        
        initialiseValues();
    }
    
    public void initialiseValues(){
        timer = new Timer(10, new GameLoop(this));
        
        Enemy enemy = new Enemy("Enemy 1");        
        enemies = new ArrayList<Enemy>();
        enemies.add(enemy);             
        enemies.get(0).getEnemySprite().setSpawnPoint(spawnPoints[0]);    
    }    

    @Override
    public void update() {
        Duration timeSinceSpawn = Duration.between(lastSpawnTime, Instant.now());
        
        if(timeSinceSpawn.getSeconds() >= spawnRate){
            int spawnPointIndex = (int) (Math.random() * spawnPoints.length);
            System.out.println("Spawn enemy at point " + spawnPointIndex);
            lastSpawnTime = Instant.now();
        }
    }

    @Override
    public void executeGameLoop() {
        update();
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvent event = (GameEvent) o;
        
        if(event.getEventName().equals("Start Game")){
            timer.start();
            lastSpawnTime = Instant.now();
        } 
        
        if(event.getEventName().equals("Stop Game")){
            timer.stop();
        } 
    }
}
