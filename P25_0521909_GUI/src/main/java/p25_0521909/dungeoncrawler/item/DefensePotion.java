package p25_0521909.dungeoncrawler.item;

import java.time.Duration;
import java.time.Instant;
import p25_0521909.dungeoncrawler.player.Player;

/**
 *
 * @author ludmi
 */
public class DefensePotion extends ExpirableItem{
    private double previousDefenseModifier;
    
    public DefensePotion() {
        super(ItemProperties.DEFENSE_POTION_NAME, ItemProperties.DEFENSE_POTION_DESCRIPTION, ItemProperties.DEFENSE_POTION_DURATION);
    }

    @Override
    public void consume() {
        previousDefenseModifier = Player.getInstance().getStats().getDefenseModifier();
        Player.getInstance().getStats().setDefenseModifier(previousDefenseModifier - ItemProperties.DEFENSE_POTION_BOOST);
        
        timerStart = Instant.now();
        timer.start();
    }

    @Override
    public void loop() {
        Duration timeSinceStart = Duration.between(timerStart, Instant.now());
        
        if(timeSinceStart.getSeconds() >= getEffectDuration()){
            Player.getInstance().getStats().setDefenseModifier(previousDefenseModifier);
            timer.stop();
        }
    }
}
