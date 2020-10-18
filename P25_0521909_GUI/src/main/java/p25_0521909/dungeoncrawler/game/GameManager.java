package p25_0521909.dungeoncrawler.game;

import java.time.*;
import java.util.*;
import javax.swing.Timer;
import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.constants.EventName;
import p25_0521909.dungeoncrawler.constants.PanelName;
import p25_0521909.dungeoncrawler.events.*;
import p25_0521909.dungeoncrawler.interfaces.*;
import p25_0521909.dungeoncrawler.player.*;
import p25_0521909.dungeoncrawler.ui.GameFrame;

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
        startGame = new GameEvent(EventName.START_GAME);
        stopGame = new GameEvent(EventName.STOP_GAME);
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
            
            GameFrame.getInstance().switchGamePanels(PanelName.WIN);
        }
        
        if(playerStats.isDead()){
            endTime = Instant.now();
            timer.stop();
            stopGame.invokeEvent();
            
            GameFrame.getInstance().switchGamePanels(PanelName.LOSS);
        }
    }
    
    @Override
    public void addEventListener(EventName eventName, Observer observer) {
        for(GameEvent gameEvent : gameEvents){
            if(gameEvent.getEventName().toString().equals(eventName.toString())){
                gameEvent.addObserver(observer);
                break;
            }
        }
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
    
    public void startGame(){
        startTime = Instant.now();
        timer.start();
        startGame.invokeEvent();
    }
    
    public EnemyController getEnemyController(){
        return enemyController;
    } 
    
    public String getTimeLeft(){
        String timeLeft = "60";
        
        try{
        Duration timeElapsed = Duration.between(startTime, Instant.now());
        
        long s = 60 - timeElapsed.getSeconds();
        
        timeLeft = Long.toString(s);
        }
        catch(NullPointerException e){}

        return timeLeft;
    }
}
