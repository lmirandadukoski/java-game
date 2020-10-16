package P25_0521909.player;

import P25_0521909.items.Inventory;
import P25_0521909.items.ConsumableItem;
import P25_0521909.items.HealthRegenConsumable;
import P25_0521909.items.Item;
import P25_0521909.items.EquipableItem;
import P25_0521909.data.DataFileReader;
import P25_0521909.data.Database;
import java.util.*;

/**
 * PlayerInventory is a collection of items belonging to the player.
 * When a player inventory object is created, it is populated with 2 potion
 * items (Health Regen Consumables).
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class PlayerInventory extends Inventory{
    private PlayerStats playerStats;    // The stats of the player (attack, health, gold).
    
    public PlayerInventory(){}
    
    @Override
    public void initialise(){
        // Gets the Health Regen Consumable Item data and populates a database of these items to choose one from.
        DataFileReader fileReader = new DataFileReader("gamedata/HealthRegenConsumableTable.txt");
        fileReader.readFileData();
        Database itemDatabase = fileReader.database;        
        
        // Gets the Potion health regen consumable and assigns 2 copies to the player's starting inventory.
        HealthRegenConsumable hrc = new HealthRegenConsumable(itemDatabase.getDataRow("Potion"), itemDatabase.getDataSchema());
        this.add(hrc);
        this.add(hrc);  

        playerStats = Player.getInstance().getStats();
    }

    @Override
    public void open() {
        Scanner userInput = new Scanner(System.in);
        boolean isValidInput = false;
        String selection;
        
        // Prints all the items in the player's inventory.
        // The player can choose to inspect any of the items in order to decide whether they want to use it or not.        
        do{
            System.out.println("---------------------------------------------------------");
            System.out.println("Health: " + playerStats.getCurrentHealthValue() + ", Gold: " + playerStats.getGoldValue());
            System.out.println("---------------------------------------------------------");
            System.out.println("Select: ");
            int index = 1;

            for(Map.Entry<Item,Integer> entry: this.getInventory().entrySet()){
                System.out.println(index + " - " + entry.getKey().getName() + " x " + entry.getValue());
                index += 1;
            }
            System.out.println("C - Close Inventory");
            
            selection = userInput.nextLine();
            if(selection.equalsIgnoreCase("c")){
                isValidInput = true;
                break;
            }
            else{
                try{
                    // When an item from the player's inventory is inspected, the player is told information
                    // about it so that they can decide if they want to use it.                   
                    int itemIndex = Integer.parseInt(selection.trim());
                    Item item = this.getItemByIndex(itemIndex - 1);
                    String itemType = item.getType();
                    item.open();
                    
                    switch(itemType){
                        case "Equipable":
                            // If the item is an equipable item, the player is asked whether they want to equip it.
                            EquipableItem e = (EquipableItem) item;
                            e.promptForEquipping();
                            isValidInput = true;
                            break;
                        
                        case "Consumable":
                            // If the item is a consumable item, the player is asked whether they want to consume it.
                            ConsumableItem c = (ConsumableItem) item;
                            c.promptForConsumption();
                            break;
                    }  
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid input!");
                }
            }
        } while(!isValidInput);  // Loops until the player closes their inventory.      
    } 
    
    @Override
    public void printInfo(){
        for(Map.Entry<Item,Integer> entry: this.getInventory().entrySet()){
            entry.getKey().printInfo();
            System.out.println("Quantity: " + entry.getValue());
        }   
    }
    
}
