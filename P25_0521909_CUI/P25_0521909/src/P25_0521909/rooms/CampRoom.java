package P25_0521909.rooms;

import P25_0521909.player.PlayerStats;
import P25_0521909.player.Player;
import java.util.Scanner;

/**
 * CampRoom represents a room where the player can rest in order to 
 * gain some health back.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class CampRoom extends Room {
    private final double regenMultiplier = 0.25;    // The % of the player's max health value that the player can regenerate in this room.
    
    private Player player;                          // A reference for the player.
    private PlayerStats playerStats;                // A reference for the player's stats.
    
    private int regenAmount;                        // The amount of health the player will regenerate in this room.
    
    public CampRoom(){}
    
    @Override
    public void initialise() {
        player = Player.getInstance();
        playerStats = player.getStats();
        
        regenAmount = (int) (playerStats.getBaseHealthValue() * regenMultiplier); // The amount of health the player can gain back as a % of their max health value.
    }

    @Override
    public void start() {
        Scanner userInput = new Scanner(System.in);
        System.out.println(player.getName() + " came across a camp!");
        
        // The main function of the Camp room is to give the player the opportunity to gain some health back or
        // inspect their inventory.
        // The player can inspect their inventory as many times as they choose to, but they can only rest once per
        // camp room.
        // Once the player rests, the camp room will be completed.
        do{
            System.out.println("---------------------------------------------------------");
            System.out.println("Select:");
            System.out.println("R - Rest (gain 25% health back)");
            System.out.println("O - Open Inventory");
            System.out.println("L - Leave");
            
            String selection = userInput.nextLine().trim();
            
            switch(selection){                
                case "R": case "r":
                    // The player chose to rest to regenerate some health.
                    System.out.println(player.getName() + " begins to relax and settle into a good night's sleep.");
                    player.regen(regenAmount);
                    System.out.println("=========================================================");
                    this.complete();
                    break;
                    
                case "O": case "o":
                    // The player chose to open their inventory.
                    player.getInventory().open();
                    break;
                    
                case "L": case "l":
                    // The player chose to leave the room without resting.
                    System.out.println("\"I'll rest when this is over...\" thinks " + player.getName() + ".");
                    System.out.println("=========================================================");
                    this.complete();
                    break;
                    
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while(!isComplete());  // Loops until the player rests or leaves the room.      
        
    }

    @Override
    public void printInfo() {
        System.out.print("Camp Room. ");
        System.out.println("The regen multiplier is " + regenMultiplier + ". The player can reger up to " + regenAmount + " health.");    
    }
    
}
