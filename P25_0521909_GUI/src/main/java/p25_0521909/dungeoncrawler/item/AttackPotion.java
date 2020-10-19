package p25_0521909.dungeoncrawler.item;

import java.time.Duration;
import java.time.Instant;
import p25_0521909.dungeoncrawler.player.Player;

/**
 *
 * @author ludmi
 */
public class AttackPotion extends ExpirableItem{
    private int previousAttackValue;
    
    public AttackPotion() {
        super(ItemProperties.ATTACK_POTION_NAME, ItemProperties.ATTACK_POTION_DESCRIPTION, ItemProperties.ATTACK_POTION_DURATION);
    }

    @Override
    public void consume() {
        previousAttackValue = Player.getInstance().getStats().getAttackValue();
        Player.getInstance().getStats().setAttackValue(previousAttackValue + ItemProperties.ATTACK_POTION_BOOST);
        
        timerStart = Instant.now();
        timer.start();
    }

    @Override
    public void loop() {
        Duration timeSinceStart = Duration.between(timerStart, Instant.now());
        
        if(timeSinceStart.getSeconds() >= getEffectDuration()){
            Player.getInstance().getStats().setAttackValue(previousAttackValue);
            timer.stop();
        }
    }
}
