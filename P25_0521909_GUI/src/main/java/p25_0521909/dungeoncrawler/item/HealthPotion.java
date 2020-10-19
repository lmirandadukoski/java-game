package p25_0521909.dungeoncrawler.item;

import p25_0521909.dungeoncrawler.player.Player;

/**
 *
 * @author ludmi
 */
public class HealthPotion extends Item{
    
    public HealthPotion() {
        super(ItemProperties.HEALTH_POTION_NAME, ItemProperties.HEALTH_POTION_DESCRIPTION);
    }

    @Override
    public void consume() {
        Player.getInstance().getStats().regenHealth(ItemProperties.HEALTH_POTION_REGEN_AMOUNT);
    } 
}
