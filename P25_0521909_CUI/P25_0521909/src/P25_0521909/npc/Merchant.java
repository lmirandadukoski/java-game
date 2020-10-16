package P25_0521909.npc;

/**
 * Merchant is an NPC character that contains an inventory that the player
 * can buy items from. 
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class Merchant {    
    private final MerchantInventory inventory; // The merchant's collection of items that the user can buy things from.
    
    public Merchant(){
        inventory = new MerchantInventory();
        inventory.initialise();
    }

    /**
     *
     * @return the inventory of the merchant.
     */
    public MerchantInventory getInventory(){
        return inventory;
    }    
}
