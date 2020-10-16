package P25_0521909.rooms;

import P25_0521909.player.Player;
import P25_0521909.npc.Merchant;
import java.util.Scanner;

/**
 * MerchantRoom represents a room where the player can purchase items from 
 * a merchant.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class MerchantRoom extends Room {
    private final Merchant merchant;    // The reference to a merchant.
    
    private Player player;              // The reference to the player.
    
    public MerchantRoom(){
        merchant = new Merchant();   
    }
    
    @Override
    public void initialise() {
        player = Player.getInstance();
    }

    @Override
    public void start() {
        Scanner userInput = new Scanner(System.in);
        System.out.println(player.getName() + " encountered a friendly merchant!");
        
        // The main function of the Merchant room is to give the player the opportunity to purchase some random
        // items from a merchant.
        // The player can purchase as many items as they can afford or they can leave the room.
        // Once the player leaves the room, the merchant room will be completed.        
        do{
            System.out.println("Merchant: Y'ello, friend! Would you like to see what I got?");
            System.out.println("---------------------------------------------------------");
            System.out.println("Select:");
            System.out.println("V - View Merchant's wares");
            System.out.println("L - Leave");
            
            String selection = userInput.nextLine().trim();

            switch(selection){
                case "V": case "v":
                    // The player chose to view the merchant's inventory.
                    System.out.println("Merchant: Alright, alright, I'll show you what I got!");
                    merchant.getInventory().open();
                    break;

                case "L": case "l":
                    // The player chose to leave the room.
                    System.out.println("Merchant: Suit yourself, friend....");
                    System.out.println("=========================================================");
                    this.complete();
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while(!isComplete()); // Loops until the player leaves the room.        
    }

    @Override
    public void printInfo() {
        System.out.println("Merchant Room. Merchant Inventory: ");
        merchant.getInventory().printInfo();
    }
    
}
