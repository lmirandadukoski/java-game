package p25_0521909.dungeoncrawler.game;

import java.time.Duration;
import java.time.Instant;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.interfaces.Updatable;
import p25_0521909.dungeoncrawler.player.Player;
import p25_0521909.dungeoncrawler.player.PlayerStats;
import p25_0521909.dungeoncrawler.ui.BattlePanel;
import p25_0521909.dungeoncrawler.ui.GameFrame;

/**
 *
 * @author ludmi
 */
public class GameManager implements Updatable, Observer{
    
    private static GameManager instance;
    
    private PlayerStats playerStats;
    private EnemyController enemyController;
    
    private Timer timer;
    private Duration duration;                                
    private Instant startTime, endTime;
    private GameEvent startGame, stopGame;
    private long maxGameDuration = 60;

    private GameManager(){}
    
    public void initialiseValues(){
        timer = new Timer(10, new GameLoop(this));
        enemyController = new EnemyController();
        playerStats = Player.getInstance().getStats();
        
        startGame = new GameEvent("Start Game");
        startGame.addObserver(enemyController);
        startGame.addObserver((BattlePanel) GameFrame.getInstance().getGamePanel("Battle"));
        
        stopGame = new GameEvent("Stop Game");
        stopGame.addObserver(enemyController);
        stopGame.addObserver((BattlePanel) GameFrame.getInstance().getGamePanel("Battle"));
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
        
    public static synchronized GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }
    
    public EnemyController getEnemyController(){
        return enemyController;
    }

    @Override
    public void update() {
        duration = Duration.between(startTime, Instant.now());

        if(duration.getSeconds() >= maxGameDuration){
            endTime = Instant.now();
            timer.stop();
            stopGame.invokeEvent();  
            
        }
        
        if(playerStats.isDead()){
            endTime = Instant.now();
            timer.stop();
            stopGame.invokeEvent();
            
            System.out.println("Player lost the game");
        }
    }

    @Override
    public void executeGameLoop() {
        update();
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvent event = (GameEvent) o;
        
        if(event.getEventName().equals("Start Button Pressed")){
            startTime = Instant.now();
            timer.start();
            startGame.invokeEvent();
        }          
    }
}
