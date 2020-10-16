package P25_0521909.items;

import P25_0521909.data.DataRow;
import P25_0521909.player.Player;
import P25_0521909.player.PlayerEquipment;
import P25_0521909.player.PlayerInventory;
import java.util.ArrayList;

/**
 * DefenseEquipable is an equipable item that has a defense value.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class DefenseEquipable extends EquipableItem {
    private final double defenseModifier;

    /**
     * To construct a defense equipable, the user needs to pass a row of
     * data containing the item's stats, and the data field names
     * contained in a database schema.
     * 
     * @param defenseEquipableData
     * @param dataFields
     */    
    public DefenseEquipable(DataRow defenseEquipableData, ArrayList<String> dataFields){
        super((String)defenseEquipableData.getDatum(dataFields.indexOf("name")), 
                (String)defenseEquipableData.getDatum(dataFields.indexOf("description")),
                Integer.parseInt((String)defenseEquipableData.getDatum(dataFields.indexOf("purchaseCost"))));
        defenseModifier = Double.parseDouble((String)defenseEquipableData.getDatum(dataFields.indexOf("defenseModifier")));
    }
    
    @Override
    public void equip() {
        PlayerEquipment equipment = Player.getInstance().getEquipment();
        PlayerInventory inventory = Player.getInstance().getInventory();

        // When this item is equiped, the currently equiped item is unequiped
        // and put in the player's inventory.
        inventory.add(equipment.getDefenseItem());
        inventory.remove(this);
        equipment.setDefenseItem(this);        
    }

    @Override
    public void unequip() {
        PlayerEquipment equipment = Player.getInstance().getEquipment();
        PlayerInventory inventory = Player.getInstance().getInventory();

        // When this item is unequiped, the item is put in the player's inventory.          
        if(equipment.getDefenseItem() == this){
            inventory.add(this);
            equipment.setDefenseItem(null);
        }     
    }  

    @Override
    public void printInfo() {
        System.out.println("Name: " + this.getName());
        System.out.println("Type: Defense Equipable");
        System.out.println("Defense Modifier: " + this.defenseModifier);
    }
    
    /**
     *
     * @return the defense modifier of the item. This is a % damage decrease value.
     * 
     */
    public double getModifier(){
        return defenseModifier;
    }
}
