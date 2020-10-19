package p25_0521909.dungeoncrawler.item;

/**
 *
 * @author ludmi
 */
public abstract class ExpirableItem extends Item{
    private final long EFFECT_DURATION;

    public ExpirableItem(String name, String description, long effectDuration) {
        super(name, description);
        EFFECT_DURATION = effectDuration;
    }
}
