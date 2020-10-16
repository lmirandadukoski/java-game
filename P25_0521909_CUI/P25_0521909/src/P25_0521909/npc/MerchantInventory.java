package P25_0521909.npc;

import P25_0521909.items.Inventory;
import P25_0521909.items.HealthRegenConsumable;
import P25_0521909.items.AttackEquipable;
import P25_0521909.items.Item;
import P25_0521909.items.DefenseEquipable;
import P25_0521909.data.DataFileReader;
import P25_0521909.data.Database;
import P25_0521909.player.Player;
import P25_0521909.interfaces.IExchangeable;
import java.util.*;

/**
 * MerchantInventory is a collection of items belonging to the merchant NPC.
 * When a merchant inventory object is created, it is populated with 3 random
 * items (one for each type of item in the game).
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class MerchantInventory extends Inventory implements IExchangeable{
    private Player player;
    private int playerGoldValue;
    
    public MerchantInventory(){}
    
    @Override
    public void initialise(){
        // Gets the Attack Equipable Item data and populates a database of these items to randomly choose one from.
        DataFileReader fileReader = new DataFileReader("gamedata/AttackEquipableTable.txt");
        fileReader.readFileData();
        Database itemDatabase = fileReader.database;
        
        AttackEquipable ae = new AttackEquipable(itemDatabase.getRandomDataRow(), itemDatabase.getDataSchema());
        this.add(ae); // The randomly chosen Attack Equipable Item is added to the merchant's inventory.
        
        // Gets the Defense Equipable Item data and populates a database of these items to randomly choose one from.
        fileReader = new DataFileReader("gamedata/DefenseEquipableTable.txt");
        fileReader.readFileData();
        itemDatabase = fileReader.database;        
        
        DefenseEquipable de = new DefenseEquipable(itemDatabase.getRandomDataRow(), itemDatabase.getDataSchema());
        this.add(de); // The randomly chosen Defense Equipable Item is added to the merchant's inventory.
        
        // Gets the Health Regen Consumable Item data and populates a database of these items to randomly choose one from.
        fileReader = new DataFileReader("gamedata/HealthRegenConsumableTable.txt");
        fileReader.readFileData();
        itemDatabase = fileReader.database;        
        
        HealthRegenConsumable hrc = new HealthRegenConsumable(itemDatabase.getRandomDataRow(), itemDatabase.getDataSchema());
        this.add(hrc); // The randomly chosen Health Regen Consumable Item is added to the merchant's inventory.
        
        player = Player.getInstance();
        playerGoldValue = player.getStats().getGoldValue();
    }

    @Override
    public void open() { 
        Scanner userInput = new Scanner(System.in);
        boolean isValidInput = false;
        String selection;
        
        // Prints all the items in the merchant's inventory.
        // The player can choose to inspect any of the items in order to decide whether they want to purchase it or not.
        do{
            System.out.println("---------------------------------------------------------");
            System.out.println("Select: ");
            int index = 1;
            
            for(Map.Entry<Item,Integer> entry: this.getInventory().entrySet()){
                System.out.println(index + " - " + entry.getKey().getName() + " x " + entry.getValue());
                index += 1;
            }
            System.out.println("C - Close");
            
            selection = userInput.nextLine();
            if(selection.equalsIgnoreCase("c")){
                isValidInput = true;
                break;
            }
            else{
                try{
                    // When an item from the merchant's inventory is inspected, the player is told information
                    // about it so that they can decide if they want to purchase it.
                    // The purchasing of the item is handled by promptForExchange function.
                    int itemIndex = Integer.parseInt(selection.trim());
                    Item item = this.getItemByIndex(itemIndex - 1);
                    item.open();
                    promptforExchange(item);
                }
                
                catch(NumberFormatException e){
                    System.out.println("Invalid input!");
                }
            }
        } while(!isValidInput); // Loops until the player closes the merchant's inventory.       
    }
    
    @Override
    public void promptforExchange(Item item) {
        Scanner userInput = new Scanner(System.in);
        boolean isValidInput = false;
        String selection;
        
        // Prints the cost of purchasing the selected item and the amount of gold the player has.
        // Prompts the player to see if they want to buy the item.
        do{
            System.out.println(item.getName() + " costs " + item.getPurchaseCost() + ".");
            System.out.println(player.getName() + " has " + playerGoldValue + " gold.");
            System.out.println("Would you like to purchase " + item.getName() + "? (Y / N). Select C to cancel.");            

            selection = userInput.nextLine();
            switch(selection){
                case "Y": case "y":
                    // The item is purchased and removed from the merchant's inventory, and added to the player's inventory.
                    exchange(item);
                    isValidInput = true;
                    break;

                case "N": case "C": case "n": case "c":
                    isValidInput = true;
                    break;

                default:
                    System.out.println("Invalid input!");
            } 
        } while(!isValidInput);        
    }

    @Override
    public void exchange(Item item) {
        Player player = Player.getInstance();
        
        // If the purchase of an iteam is valid, the item is removed from the merchant's inventory and added to the player's inventory.
        if(playerGoldValue >= item.getPurchaseCost()){
            System.out.println(player.getName() + " purchased " + item.getName() + "!");
            player.getStats().decreaseGoldValue(item.getPurchaseCost());
            playerGoldValue = player.getStats().getGoldValue();
            System.out.println(player.getName() + " has " + playerGoldValue + " gold left.");
            player.getInventory().add(item);
            this.remove(item);
        }
        else{
            System.out.println(player.getName() + " does not have enough gold!");
        }
    }
    
    @Override
    public void printInfo(){
        for(Map.Entry<Item,Integer> entry: this.getInventory().entrySet()){
            entry.getKey().printInfo();
            System.out.println("Quantity: " + entry.getValue());
        }   
    }
    
}
