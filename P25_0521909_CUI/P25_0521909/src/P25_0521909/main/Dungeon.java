package P25_0521909.main;

import P25_0521909.rooms.BattleRoom;
import P25_0521909.rooms.Room;
import P25_0521909.rooms.MerchantRoom;
import P25_0521909.rooms.CampRoom;
import P25_0521909.interfaces.IDebugable;
import P25_0521909.interfaces.IInitialisable;
import P25_0521909.interfaces.ICompletable;
import P25_0521909.enemies.BossEnemy;
import P25_0521909.enemies.CommonEnemy;
import P25_0521909.data.DataFileReader;
import P25_0521909.data.Database;
import P25_0521909.player.Player;
import java.util.*;

/**
 * Dungeon is an object that contains rooms that the player will need to navigate
 * through in order to win the game.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class Dungeon implements ICompletable, IDebugable, IInitialisable{
    public boolean playerQuit;  // Stores whether the user quit before the dungeon was complete or not.
    
    private boolean isComplete; // Stores whether the dungeon is complete or not.
    private Room[] rooms;       // The array of rooms that the user needs to complete in order to finish the dungeon.
    private int nextRoomIndex;  // The index of the next room the player will encounter.
    
    public Dungeon(){
        isComplete = false;
        playerQuit = false;
    }
    
    @Override
    public void initialise() {
        // Constructs a database of common-enemy data so that common enemies can be created in battle rooms.
        DataFileReader commonEnemyReader = new DataFileReader("gamedata/CommonEnemyTable.txt");
        commonEnemyReader.readFileData();
        Database commonEnemyDatabase = commonEnemyReader.database;
        
        // Constructs a database of boss-enemy data so that the last rooms in the dungeon can contain an instance of boss enemy.
        DataFileReader bossEnemyReader = new DataFileReader("gamedata/BossEnemyTable.txt");
        bossEnemyReader.readFileData();
        Database bossEnemyDatabase = bossEnemyReader.database;
        
        // Gets the number of rooms the dungeon will contain from the difficulty level selected by the player, and initialised the rooms array.
        int nRooms = Game.difficultyLevel.roomNumber;        
        rooms = new Room[nRooms];
        
        // Stores the probability of each type of room occurring in an array for ease of access.
        Double[] roomProbabilities = new Double[]{Game.difficultyLevel.pBattleRoom, Game.difficultyLevel.pCampRoom, Game.difficultyLevel.pMerchantRoom};
        
        // Loop through all the rooms in the room array to assign what kind of room it will be.
        for(int i = 0; i < rooms.length - 1; i++){
            double randomProbability = Math.random();   // Get a random probability.
            double cumulativeProbability = 0.0;

            // For each room type, if the random probability is within the range of the cumulative probability
            // the room will be assigned a room type.
            for(int j = 0; j < roomProbabilities.length; j++){
                cumulativeProbability += roomProbabilities[j];
                if(randomProbability <= cumulativeProbability){
                    switch(j){
                        case 0:
                            // A common enemy battle room is created.
                            rooms[i] = new BattleRoom(new CommonEnemy(commonEnemyDatabase.getRandomDataRow(), commonEnemyDatabase.getDataSchema()));
                            break;
                            
                        case 1:
                            // A camp room is created.
                            rooms[i] = new CampRoom();
                            break;
                            
                        case 2:
                            // A merchant room is created.
                            rooms[i] = new MerchantRoom();
                            break;
                    }
                    break;
                }  
            }              
        }
        
        // Assigns a boss enemy battle to the last room in the rooms array.
        rooms[nRooms - 1] = new BattleRoom(new BossEnemy(bossEnemyDatabase.getRandomDataRow(), bossEnemyDatabase.getDataSchema()));
        nextRoomIndex = 0;
    }

    @Override
    public void start() {
        Player player = Player.getInstance();        
        Scanner userInput = new Scanner(System.in); 
        
        // The main function of the dungeon is to go through each room in the rooms array, initialise it, and start it.
        // While not inside a room, the player can open their inventory or quit the game.
        do {
            System.out.println("What will " + Player.getInstance().getName() + " do?");
            System.out.println("---------------------------------------------------------");
            System.out.println("Select:");
            System.out.println("N - Enter the next room");
            System.out.println("O - Open Inventory");
            System.out.println("Q - Quit Game");
            
            Room nextRoom;            
            String selection = userInput.nextLine();
            switch(selection){
                case "N": case "n":
                    // The player chose to go to the next room.
                    System.out.println("=========================================================");
                    nextRoom = rooms[nextRoomIndex];
                    nextRoom.initialise();
                    nextRoom.start();
                    
                    nextRoomIndex += 1;
                    if(nextRoomIndex >= rooms.length){
                        complete();
                        break;
                    }                   
                    break;
                
                case "O": case "o":
                    // The player chose to open their inventory.
                    player.getInventory().open();
                    break;
                
                case "Q": case "q":
                        // The player chose to quit the game.
                        String confirmation = "";
                        boolean isValidInput = false;
                        do{
                            System.out.println("Are you sure you want to abandon the current run? (Y / N)");
                            confirmation = userInput.nextLine().trim();

                            switch(confirmation){
                                case "Y": case "y":
                                    // The player confirmed that they want to quit the game.
                                    System.out.println("Quitting...");
                                    isValidInput = true;
                                    playerQuit = true;
                                    this.complete();
                                    break;

                                case "N": case "n":
                                    // The player confirmed that the want to continue the game.
                                    System.out.println("Resuming current run...");
                                    isValidInput = true;
                                    break;

                                default:
                                    System.out.println("Invalid input!");
                                    break;
                            }
                        } while(!isValidInput);
                    break;
                    
                default:
                    System.out.println("Invalid input!");
            }
        } while(!this.isComplete() && !Player.getInstance().isDead()); // Loop through rooms array stops if the player is dead or if there are no more rooms to complete.
        
        complete(); // The player is dead so the game can be completed.
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void complete() {
        isComplete = true;
    }
    
    @Override
    public void printInfo(){
        System.out.println("Number of Rooms: " + rooms.length);
        
        for(int i = 0; i < rooms.length; i++){
            rooms[i].printInfo();
        }
    }
}
