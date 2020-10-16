package P25_0521909.items;

import P25_0521909.interfaces.IConsumable;
import java.util.Scanner;

/**
 * ConsumableItem is a consumable item that the player can use.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class ConsumableItem extends Item implements IConsumable{

    /**
     * To construct a consumable item, the user needs to pass the item's
     * name, description, ad purchase cost.
     * 
     * @param name
     * @param description
     * @param purchaseCost
     */
    public ConsumableItem(String name, String description, int purchaseCost){
        super(name, description, purchaseCost);
    }
    
    @Override
    public String getType(){
        return "Consumable";
    } 

    @Override
    public void promptForConsumption() {

        Scanner userInput = new Scanner(System.in);
        boolean isValidInput = false;
        String selection;
        
        // Prompts the player to see if they want to consume the item.
        do{
            System.out.println("Consume " + this.getName() + "? (Y / N). Select C to cancel.");
            selection = userInput.nextLine();
            switch(selection){
                case "Y": case "y":
                    // If yes, the item will be consumed.
                    this.consume();
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
