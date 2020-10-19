package p25_0521909.dungeoncrawler.item;

/**
 *
 * @author ludmi
 */
public class DefensePotion extends ExpirableItem{

    public DefensePotion(String name, String description, int effectDuration) {
        super(name, description, effectDuration);
    }

    @Override
    public void consume() {
        //increase defense modifier for x seconds
    }
}
