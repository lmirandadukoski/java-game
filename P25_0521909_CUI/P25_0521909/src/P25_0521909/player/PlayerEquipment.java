package P25_0521909.player;

import P25_0521909.items.AttackEquipable;
import P25_0521909.items.DefenseEquipable;
import P25_0521909.data.DataFileReader;
import P25_0521909.data.Database;
import P25_0521909.interfaces.IDebugable;
import P25_0521909.interfaces.IInitialisable;

/**
 * PlayerEquipment is an object that hold's the player's hand-held items.
 * The player can only hold one attack equipable and one defense equipable at a time.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class PlayerEquipment implements IDebugable, IInitialisable{
    private AttackEquipable attackItem;     // The hand-held attack equipable item of the player.
    private DefenseEquipable defenseItem;   // The hand-held defense equipable item of the player.
    
    public PlayerEquipment(){}
    
    @Override
    public void initialise() {
        // Gets the Attack Equipable data and populates a database of these items to choose one from.
        DataFileReader fileReader = new DataFileReader("gamedata/AttackEquipableTable.txt");
        fileReader.readFileData();
        Database itemDatabase = fileReader.database;
        
        // Gets the Rusty Sword attack equipable and assigns it to the player's hand-held weapon.
        attackItem = new AttackEquipable(itemDatabase.getDataRow("Rusty Sword"), itemDatabase.getDataSchema());
        
        // Gets the Defense Equipable data and populates a database of these items to choose one from.
        fileReader = new DataFileReader("gamedata/DefenseEquipableTable.txt");
        fileReader.readFileData();
        itemDatabase = fileReader.database;        
        
        // Gets the Rusty Shield attack equipable and assigns it to the player's hand-held shield.
        defenseItem = new DefenseEquipable(itemDatabase.getDataRow("Rusty Shield"), itemDatabase.getDataSchema());
    }
    
    @Override
    public void printInfo() {
        System.out.println("Attack Item: Name = " + attackItem.getName() + ", Attack Modifier = " + attackItem.getModifier());
        System.out.println("Defense Item: Name = " + defenseItem.getName() + ", Defense Modifier = " + defenseItem.getModifier());
    }
    
    /**
     *
     * @return the player's hand-held weapon.
     */
    public AttackEquipable getAttackItem(){
        return attackItem;
    }
    
    /**
     * Sets the player's hand-held weapon to the chosen Attack Equipable item.
     * @param item
     */
    public void setAttackItem(AttackEquipable item){
        attackItem = item;
    }
    
    /**
     *
     * @return the player's hand-held defense item.
     */
    public DefenseEquipable getDefenseItem(){
        return defenseItem;
    }
    
    /**
     *Sets the player's hand-held defense item to the chosen Defense Equipable item.
     * @param item
     */
    public void setDefenseItem(DefenseEquipable item){
        defenseItem = item;
    }

}
