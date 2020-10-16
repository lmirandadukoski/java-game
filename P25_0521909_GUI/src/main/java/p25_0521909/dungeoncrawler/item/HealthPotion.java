package p25_0521909.dungeoncrawler.item;

/**
 *
 * @author ludmi
 */
public class HealthPotion extends Item{
    private int healthRegenAmount;
    
    public HealthPotion(String name, String description) {
        super(name, description);
    }

    @Override
    public void consume() {
        //Increase player health on consumption
    }
    
}
