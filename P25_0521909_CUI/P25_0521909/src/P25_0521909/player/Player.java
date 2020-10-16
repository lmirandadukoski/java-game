package P25_0521909.player;

import P25_0521909.interfaces.IRegenerable;
import P25_0521909.interfaces.IDebugable;
import P25_0521909.interfaces.IInitialisable;
import P25_0521909.interfaces.IDamageable;
import P25_0521909.interfaces.IFightable;
import P25_0521909.data.DataFileReader;
import P25_0521909.data.Database;
import P25_0521909.main.Game;

/**
 * Player is an object that represents the user while the game is running. The Player class is a singleton,
 * meaning that there can only be one instance of it in any game.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class Player implements IDamageable, IFightable, IInitialisable, IRegenerable, IDebugable{
    private static Player instance;     // The singleton instance of the class.
    
    private String playerName;          // The name of the player's character.
    private PlayerStats stats;          // The stats (attack, defense, gold) of the player's character.  
    private PlayerEquipment equipment;  // The equipment that the player's character is holding - these are used in battles.
    private PlayerInventory inventory;  // A collection of items that the player's character has.
    
    private Player(){}
    
    /**
     *
     * @return the singleton instance of the class, if it exists, or creates a new instance of the class.
     */
    public static Player getInstance(){
        if(instance == null){
            instance = new Player();
        }
        return instance;
    }
    
    @Override
    public void initialise() {
        // Gets the Player Stats data and populates a database of these items to choose one from.
        DataFileReader fileReader = new DataFileReader("gamedata/PlayerStatsTable.txt");
        fileReader.readFileData();
        Database database = fileReader.database;
        
        // Gets the Player Stats data that corresponds to the difficulty level chosen for the current game.
        String difficultyLevelLabel = Game.difficultyLevel.difficulty;        
        instance.stats = new PlayerStats(database.getDataRow(difficultyLevelLabel), database.getDataSchema());
        
        // Creates the player's inventory.
        instance.inventory = new PlayerInventory();
        instance.inventory.initialise();
        
        // Creates the player's equipment.
        instance.equipment = new PlayerEquipment();
        instance.equipment.initialise();
    }
          
    @Override
    public void takeDamage(int damageAmount) {
        instance.stats.decreaseCurrentHealthValue(damageAmount);
        System.out.println(instance.playerName + "'s health is now " + instance.stats.getCurrentHealthValue() + "!");
    }

    @Override
    public boolean isDead() {
        return instance.stats.getCurrentHealthValue() <= 0;
    }

    @Override
    public void attack(int damageAmount) {        
        System.out.println(instance.playerName + " attacks using " + instance.equipment.getAttackItem().getAttackName() + "!");
        System.out.println(instance.playerName + " deals " + damageAmount + " damage.");
    }
    
    @Override
    public int getAttackDamage() {
        int attackDamage = instance.stats.getBaseAttackValue();
        if(instance.equipment.getAttackItem()!= null){attackDamage += instance.equipment.getAttackItem().getModifier();}
        
        return attackDamage;
    }
    
    @Override
    public void regen(int regenAmount) {
        instance.stats.increaseCurrentHealthValue(regenAmount);
        System.out.println(instance.playerName + "'s health is now " + instance.stats.getCurrentHealthValue() + "!");
    }
    
    @Override
    public void printInfo(){
        System.out.println("Player Name: " + instance.playerName);
        instance.stats.printInfo();
        instance.equipment.printInfo();
    }
    
    /**
     *
     * @return the name of the player's character.
     */
    public String getName(){
        return instance.playerName;
    }
    
    /**
     * Sets the name of the player's character to the chosen value.
     * 
     * @param value
     */
    public void setName(String value){
        instance.playerName = value;
    }
    
    /**
     *
     * @return the player's stats (attack, defense, gold).
     */
    public PlayerStats getStats(){
        return instance.stats;
    }
    
    /**
     *
     * @return the player's equipment (attack item, defense item).
     */
    public PlayerEquipment getEquipment(){
        return instance.equipment;
    }
    
    /**
     *
     * @return the player's inventory.
     */
    public PlayerInventory getInventory(){
        return instance.inventory;
    }
    
}
