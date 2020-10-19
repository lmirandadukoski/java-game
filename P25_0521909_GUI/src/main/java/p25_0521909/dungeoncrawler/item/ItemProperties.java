package p25_0521909.dungeoncrawler.item;

/**
 *
 * @author ludmi
 */
public class ItemProperties {
    
    private ItemProperties(){}
    
    public static final String HEALTH_POTION_NAME = "Health Potion";
    public static final String HEALTH_POTION_DESCRIPTION = "Restores 3 health.";
    public static final int HEALTH_POTION_REGEN_AMOUNT = 3;
    public static final int HEALTH_POTION_QUANTITY = 3;
    
    public static final String DEFENSE_POTION_NAME = "Defense Potion";
    public static final String DEFENSE_POTION_DESCRIPTION = "Reduces 30% of enemy damage for 3 seconds.";
    public static final double DEFENSE_POTION_BOOST = 0.3;
    public static final long DEFENSE_POTION_DURATION = 3;
    public static final int DEFENSE_POTION_QUANTITY = 5;
    
    public static final String ATTACK_POTION_NAME = "Attack Potion";
    public static final String ATTACK_POTION_DESCRIPTION = "Increases damage by 2 for 3 seconds.";
    public static final int ATTACK_POTION_BOOST = 2;
    public static final long ATTACK_POTION_DURATION = 3;
    public static final int ATTACK_POTION_QUANTITY = 5;   
}
