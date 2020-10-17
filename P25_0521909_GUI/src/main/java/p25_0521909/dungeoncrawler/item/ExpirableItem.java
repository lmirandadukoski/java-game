package p25_0521909.dungeoncrawler.item;

/**
 *
 * @author ludmi
 */
public abstract class ExpirableItem extends Item{
    int effectDuration;

    public ExpirableItem(String name, String description, int effectDuration) {
        super(name, description);
        this.effectDuration = effectDuration;
    }
}
