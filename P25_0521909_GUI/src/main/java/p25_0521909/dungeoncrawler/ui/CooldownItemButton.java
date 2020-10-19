package p25_0521909.dungeoncrawler.ui;

import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.Instant;
import javax.swing.Timer;

import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.game.GameLoop;
import p25_0521909.dungeoncrawler.interfaces.Loopable;
import p25_0521909.dungeoncrawler.item.ExpirableItem;
import p25_0521909.dungeoncrawler.item.Item;
import p25_0521909.dungeoncrawler.player.Player;

/**
 *
 * @author ludmi
 */

public class CooldownItemButton extends ItemButton implements Loopable{
    private final Timer timer;
    private Instant timerStart;
    
    private final long cooldownDuration;
    
    public CooldownItemButton(Item item, int quantity){
        super(item, quantity);
        
        ExpirableItem eItem = (ExpirableItem) item;
        cooldownDuration = eItem.getEffectDuration();
        timer = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ITEM.consume();
        
        Player.getInstance().getInventory().removeItem(ITEM);
        quantity --;
        setText(ITEM.getName() + "(" + quantity + ")");

        setEnabled(false);
        timerStart = Instant.now();
        timer.start();
    }  

    @Override
    public void loop() {
        Duration timeSinceStart = Duration.between(timerStart, Instant.now());
        
        if(timeSinceStart.getSeconds() >= cooldownDuration){
            if(quantity > 0){
                setEnabled(true);
            }
            timer.stop();
        }
    }
}
