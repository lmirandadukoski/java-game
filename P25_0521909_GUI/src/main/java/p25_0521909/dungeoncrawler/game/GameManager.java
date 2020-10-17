package p25_0521909.dungeoncrawler.game;

import java.time.*;
import java.util.*;
import javax.swing.Timer;
import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.events.*;
import p25_0521909.dungeoncrawler.interfaces.*;
import p25_0521909.dungeoncrawler.player.*;

/**
 *
 * @author ludmi
 */
public class GameManager implements Initialisable, Loopable, GameEventListener{
    private static GameManager instance;
    
    private PlayerStats playerStats;
    private EnemyController enemyController;
    
    private Timer timer;
    private Duration duration;                                
    private Instant startTime, endTime;
    private GameEvent startGame, stopGame;
    private ArrayList<GameEvent> gameEvents;

    private GameManager(){}
    
    @Override
    public void initialise(){
        gameEvents = new ArrayList<GameEvent>();
        startGame = new GameEvent("Start Game");
        stopGame = new GameEvent("Stop Game");
        gameEvents.add(startGame);
        gameEvents.add(stopGame);
        
        playerStats = Player.getInstance().getStats();
        enemyController = new EnemyController();        
        timer = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));  
    }
    
    @Override
    public void loop() {
        duration = Duration.between(startTime, Instant.now());

        if(duration.getSeconds() >= Constants.GAME_DURATION){
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
    public void addEventListener(String eventName, Observer observer) {
        for(int i = 0; i < gameEvents.size(); i++){
            if(gameEvents.get(i).getEventName().equals(eventName)){
                gameEvents.get(i).addObserver(observer);
                break;
            }
        }
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }

    public void startGame(){
        startTime = Instant.now();
        timer.start();
        startGame.invokeEvent();
    }
    
    public EnemyController getEnemyController(){
        return enemyController;
    } 
    
    public static synchronized GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }
}
