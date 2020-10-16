package P25_0521909.items;

import P25_0521909.data.DataRow;
import P25_0521909.player.Player;
import P25_0521909.player.PlayerEquipment;
import P25_0521909.player.PlayerInventory;
import java.util.ArrayList;

/**
 * AttackEquipable is an equipable item that has an attack value.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class AttackEquipable extends EquipableItem {
    private final int attackModifier;
    private final String attackName;
    
    /**
     * To construct an attack equipable, the user needs to pass a row of
     * data containing the item's stats, and the data field names
     * contained in a database schema.
     * 
     * @param attackEquipableData
     * @param dataFields
     */
    public AttackEquipable(DataRow attackEquipableData, ArrayList<String> dataFields){
        super((String)attackEquipableData.getDatum(dataFields.indexOf("name")), 
                (String)attackEquipableData.getDatum(dataFields.indexOf("description")),
                Integer.parseInt((String)attackEquipableData.getDatum(dataFields.indexOf("purchaseCost"))));
        attackModifier = Integer.parseInt((String)attackEquipableData.getDatum(dataFields.indexOf("attackModifier")));
        attackName = (String)attackEquipableData.getDatum(dataFields.indexOf("attackName"));
    }

    @Override
    public void equip() {
        PlayerEquipment equipment = Player.getInstance().getEquipment();
        PlayerInventory inventory = Player.getInstance().getInventory();
        
        // When this item is equiped, the currently equiped item is unequiped
        // and put in the player's inventory.
        inventory.add(equipment.getAttackItem());
        inventory.remove(this);
        equipment.setAttackItem(this);        
    }

    @Override
    public void unequip() {
        PlayerEquipment equipment = Player.getInstance().getEquipment();
        PlayerInventory inventory = Player.getInstance().getInventory();

        // When this item is unequiped, the item is put in the player's inventory.        
        if(equipment.getAttackItem() == this){
            inventory.add(this);
            equipment.setAttackItem(null);
        }     
    }  

    @Override
    public void printInfo() {
        System.out.println("Name: " + this.getName());
        System.out.println("Type: Attack Equipable");
        System.out.println("Attack Modifier: " + this.attackModifier);
        System.out.println("Attack Name: " + this.getAttackName());   
    }

    /**
     *
     * @return the attack name of this item.
     * 
     */
    public String getAttackName(){
        return attackName;
    }
    
    /**
     *
     * @return the attack damage bonus of this item.
     * 
     */
    public int getModifier(){
        return attackModifier;
    }
    
}
