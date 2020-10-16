package P25_0521909.items;

import P25_0521909.interfaces.IEquipable;
import java.util.Scanner;

/**
 * EquipableItem is an item that the player can equip.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class EquipableItem extends Item implements IEquipable {
    
    /**
     * To construct an equipable item, the user needs to pass the item's
     * name, description, and purchase cost.
     * 
     * @param name
     * @param description
     * @param purchaseCost
     */
    public EquipableItem(String name, String description, int purchaseCost){
        super(name, description, purchaseCost);
    }
    
    @Override
    public String getType(){
        return "Equipable";
    }
    
    @Override
    public void promptForEquipping(){

        Scanner userInput = new Scanner(System.in);
        boolean isValidInput = false;
        String selection;
        
        // Prompts the player to see if they want to equip the item.
        do{
            System.out.println("Equip " + this.getName() + "? (Y / N). Select C to cancel.");
            selection = userInput.nextLine();
            switch(selection){
                case "Y": case "y":
                    // If yes, the item will be equipped.
                    this.equip();
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
}
