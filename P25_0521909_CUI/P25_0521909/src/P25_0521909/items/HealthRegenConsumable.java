package P25_0521909.items;

import P25_0521909.data.DataRow;
import P25_0521909.player.Player;
import P25_0521909.player.PlayerInventory;
import java.util.ArrayList;

/**
 * HealthRegenConsumable is a consumable item that regenerates health.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class HealthRegenConsumable extends ConsumableItem {
    private final int healthRegen;
    
    /**
     * To construct a health regen consumable, the user needs to pass a row of
     * data containing the item's stats, and the data field names
     * contained in a database schema.
     * 
     * @param healthRegenConsumableData
     * @param dataFields
     */      
    public HealthRegenConsumable(DataRow healthRegenConsumableData, ArrayList<String> dataFields){
        super((String)healthRegenConsumableData.getDatum(dataFields.indexOf("name")), 
                (String)healthRegenConsumableData.getDatum(dataFields.indexOf("description")),
                Integer.parseInt((String)healthRegenConsumableData.getDatum(dataFields.indexOf("purchaseCost"))));
        healthRegen = Integer.parseInt((String)healthRegenConsumableData.getDatum(dataFields.indexOf("healthRegen")));
    }

    @Override
    public void consume() {
        PlayerInventory inventory = Player.getInstance().getInventory();
        inventory.remove(this);
        
        Player.getInstance().regen(healthRegen);
        System.out.println(this.getName() + " consumed. ");
    }

    @Override
    public void printInfo() {
        System.out.println("Name: " + this.getName());
        System.out.println("Type: Health Regen Consumable");
        System.out.println("Health Return Value: " + this.healthRegen);
    }   
}
