package p25_0521909.dungeoncrawler.enemy;

import java.time.*;
import java.util.*;
import javax.swing.Timer;

import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.constants.EventName;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.game.GameLoop;
import p25_0521909.dungeoncrawler.interfaces.Combatable;
import p25_0521909.dungeoncrawler.interfaces.Loopable;
import p25_0521909.dungeoncrawler.player.Player;

/**
 *
 * @author ludmi
 */
public class EnemyStats implements Combatable, Loopable, Observer{
    private final int ATTACK_DAMAGE;
    private final Timer ATTACK_TIMER;
    
    private int healthValue, attackTimes;
    private Instant lastAttackTime;
    
    public EnemyStats(int attackDamage, int healthValue){
        this.ATTACK_DAMAGE = attackDamage;
        this.healthValue = healthValue;
        
        ATTACK_TIMER = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));
        attackTimes = 0;
    }
    
    @Override
    public void attack(Combatable combatable) {
        combatable.takeDamage(ATTACK_DAMAGE);
    }

    @Override
    public void takeDamage(int damageAmount) {
        int newHealthValue = healthValue - damageAmount;
        
        if(newHealthValue < 0){
            healthValue = 0;
        }
        else{
            healthValue = newHealthValue;
        } 
    }

    @Override
    public boolean isDead() {
        return healthValue <= 0;
    }    

    @Override
    public int getCurrentHealth() {
        return healthValue;
    }

    @Override
    public void loop() {
        Duration timeSinceAttack = Duration.between(lastAttackTime, Instant.now());
        
        if(attackTimes < EnemyProperties.ENEMY_ATTACKS_NUMBER){
            if(timeSinceAttack.getSeconds() >= EnemyProperties.ENEMY_ATTACK_SPEED){
                attack(Player.getInstance().getStats());
                attackTimes++;    
            }
        }
        
        else{
            healthValue = 0;
            ATTACK_TIMER.stop();
        }        
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvent event = (GameEvent) o;
        
        if(event.equals(EventName.ENEMY_ATTACK)){
            ATTACK_TIMER.start();
            lastAttackTime = Instant.now();
        }         
    }
}
