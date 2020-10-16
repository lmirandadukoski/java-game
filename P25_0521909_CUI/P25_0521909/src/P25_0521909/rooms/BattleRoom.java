package P25_0521909.rooms;

import P25_0521909.player.PlayerStats;
import P25_0521909.player.PlayerEquipment;
import P25_0521909.player.Player;
import P25_0521909.enemies.Enemy;
import java.util.Scanner;

/**
 * BattleRoom represents a room containing enemy battles.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class BattleRoom extends Room {
    private final Enemy enemy;                  // The enemy for this room.
    
    private Player player;                      // A reference for the player.
    private PlayerStats playerStats;            // A reference for the stats of the player.
    private PlayerEquipment playerEquipment;    // A reference for the equipment of the player.
    
    private double playerDefense;               // User in the calculation of the amount of damage that enemies deal to the player.
    
    public BattleRoom(Enemy enemy){
        this.enemy = enemy;    
    }
    
    @Override
    public void initialise() {
        player = Player.getInstance();
        playerStats = player.getStats();
        playerEquipment = player.getEquipment();
        
        // Decreases the player defence value by the defense modifier of the hand-help defense item (if there is one).
        playerDefense = 1.0;
        if(playerEquipment.getDefenseItem()!= null){playerDefense -= playerEquipment.getDefenseItem().getModifier();}
    }

    @Override
    public void start(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("A " + enemy.getName() + " appears!");
        
        // The main function of the Battle room is to go between player and enemy turns until either the player or the
        // enemy dies.
        do{ 
            // Gives the player a turn if they're not dead.
            if(!player.isDead()){
                playerTurn();
                System.out.println("");
                
                // If the enemy dies during the player's turn, the battle is over and the room is complete.
                // The player receives the gold reward value of the enemy.
                if(enemy.isDead()){
                    System.out.println(player.getName() + " defeated " + enemy.getName() + "!");
                    System.out.println(player.getName() + " received " + enemy.getRewardValue() + " gold as a reward.");
                    playerStats.increaseGoldValue(enemy.getRewardValue());
                    System.out.println("=========================================================");
                    this.complete();
                    break;
                }
            }
            
            // The enemy takes a turn.
            enemyTurn();
            
            //If the player dies during the enemy's turn, the battle is over, the room is comple, and the game is lost.
            if(player.isDead()){
                System.out.println(player.getName() + " died!");
                System.out.println("Better luck next time.....");

                this.complete();                
            }

        } while(!isComplete()); // Loops until either the player or the enemy dies.
    }
    
    @Override
    public void printInfo() {
        System.out.print("Battle Room: ");
        System.out.print("enemy = " + enemy.getName() + ", ");
        System.out.println("isComplete = " + this.isComplete());        
    }
    /**
     * The player's turn consists of attacking an enemy and inspecting their inventory.
     * The player can inspect their inventory as many times as they choose to, but they
     * can only attack once per turn.
     * 
     * When the player attacks, the player's turn is over.
     * 
     */
    private void playerTurn(){
        Scanner userInput = new Scanner(System.in);
        boolean endPlayerTurn = false;
        String selection;
        
        do{
            System.out.println("---------------------------------------------------------");
            System.out.println("Select:");
            System.out.println("A - Attack");
            System.out.println("O - Open Inventory");

            selection = userInput.nextLine().trim();

            switch(selection){
                case "A": case "a":
                    player.attack(player.getAttackDamage());
                    enemy.takeDamage(player.getAttackDamage());
                    endPlayerTurn = true;
                    break;

                case "O": case "o":
                    player.getInventory().open();
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while(!endPlayerTurn);
    }
    
    /**
     * The enemy's turn consists of attacking the player. The enemy's attack value is lowered
     * by the defense modifier of the player's hand-held defense item.
     * 
     */
    private void enemyTurn(){
        int enemyDamage = (int)(Math.round(enemy.getAttackDamage() * playerDefense));
        enemy.attack(enemyDamage);
        player.takeDamage(enemyDamage);        
    }
}
