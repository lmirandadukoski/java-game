package P25_0521909.main;

import P25_0521909.player.Player;
import P25_0521909.interfaces.IInitialisable;
import P25_0521909.interfaces.ICompletable;
import P25_0521909.data.DataFileReader;
import P25_0521909.data.Database;
import P25_0521909.data.DataFileWriter;
import java.io.*;
import java.util.*;
import java.time.*;

/**
 * Game is an object that represents the entire dungeon crawler RPG game.
 * Game generates a dungeon once the player has chosen a difficulty level,
 * and a name for their character.
 * 
 * If the user has player the game before, the game will ask the player if
 * they want to re-use their previous character name.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class Game implements ICompletable, IInitialisable {
    private static Dungeon dungeon;                     // The dungeon for the current game.
    private static Player player;                       // A player instance for the current game.
    private static String playerName;                   // The player's character name for the current game.
    private static boolean isComplete;                  // Stores whether the game is complete or not.
    private static Duration gameDuration;               // Stores the game's run time.
    private static Database lastGameData;               // A database containing previous game data, if it exists.
    public static DifficultyLevel difficultyLevel;      // The difficulty level selected for the current game.
    
    public Game(){}

    @Override
    public void initialise() {
        player = Player.getInstance();                  // Creates the singleton instance of the player.
        dungeon = new Dungeon();                        // Creates a new dungeon.
        
        if(lastGameExists()){
            lastGameData = getLastGameResults();        // Gets the last game data, if it exists.
        }
    }
    
    public static void main(String[] args) {
        Game game = new Game();                         // Creates a new game.
        game.initialise();                              // Initialised the game's properties.
        game.start();                                   // Starts the game.
    }

    @Override
    public void start() {
        Instant startTime = Instant.now();              // Records the time at which the game started. Used to calculate game run time.
        Scanner userInput = new Scanner(System.in);
        
        // The main function of the Game is to get user inputs to define what kind of game the player wants to play (i.e., difficulty
        // level), and what character name the player wants to use.
        // Once these two properties are defined, a difficulty level can be created, which can be assigned to a new dungeon.        
        
        
        // The start "screen" for the dungeon.
        // The screen displays different text depending on whether the player has played the game before or not.
        System.out.println("=========================================================");
        if(lastGameExists()){           
            System.out.println("Welcome back, hero...");
            System.out.println("Care to test your fate again?");
            System.out.println("");
        }
        
        else{
            System.out.println("Welcome to the...");
            System.out.println("HAUNTED DUNGEON");
            System.out.println("");
            System.out.println("Navigate your way to safety, but be weary of monsters!");
        }
        
        // Checks whether the user's input matches "S" (lower case is accepted).
        System.out.println("Enter S to start a new run.");         
        String selection = "";
        boolean isValidInput = false;
        do{
            selection = userInput.nextLine().trim();
            if(!selection.equalsIgnoreCase("S")){
                System.out.println("Invalid input!");
            }
            else{
                isValidInput = true;
                break;
            }
        } while(!isValidInput); // Loops until the player has entered the correct input.
        System.out.println("=========================================================");
        
        // The player name selection "screen".
        // If the player has played the game before, there will be a prompt to check to see if the player wants to re-use the same
        // character name as before or use a new name. Otherwise, the player gets asked to choose a character name for the current
        // game.
        playerName = "";
        selection = "";
        isValidInput = false;
        if(lastGameExists()){
            playerName = (String)lastGameData.getDataRow("Last Game").getDatum(lastGameData.getDataSchema().indexOf("playerName"));
            System.out.println("The dungeon remembers the name " + playerName + "..."); 
        }
        else{
            System.out.println("Enter your player name.");
            playerName = userInput.nextLine().trim();            
        }
        
        do{
            System.out.println("Do you want to proceed with the name " + playerName + "? (Y / N)");
            selection = userInput.nextLine().trim();

            switch(selection){
                case "Y": case "y":
                    isValidInput = true;
                    break;

                case "N": case "n":
                    System.out.println("Enter your player name.");
                    playerName = userInput.nextLine().trim();
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while(!isValidInput); // Loops until the player confirms their character name input.
        System.out.println("=========================================================");
        
        // The Difficulty Level selection "screen".
        // The player is asked what difficulty they want to set the current game to. Based on their selection,
        // a row from the database containing difficulty level preset data is fetched and parsed into a 
        // difficulty level object.
        DataFileReader fileReader = new DataFileReader("gamedata/DifficultyLevelTable.txt");
        fileReader.readFileData();
        Database database = fileReader.database;
        
        int difficultySelection;
        isValidInput = false;
        do{
            System.out.println("Select a difficulty level:");
            System.out.println("1 - Easy");
            System.out.println("2 - Normal");
            System.out.println("3 - Hard");  
            difficultySelection = userInput.nextInt();

            switch(difficultySelection){
                case 1: case 2: case 3:
                    // A valid difficulty level option is selected, so the Difficulty Level object for the current game is constructed.
                    difficultyLevel = new DifficultyLevel(database.getDataRow(difficultySelection), database.getDataSchema());
                    System.out.println("Alright, " + playerName + ", your adventure begins now.");
                    System.out.println("Best of luck - you will need it...");
                    isValidInput = true;
                    break;
                    
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while(!isValidInput); // Loops until the player selects a valid difficulty level option.
        System.out.println("=========================================================");

        player.initialise();        // With a difficulty level selected, the player's base stats can be populated as these depend on the difficulty level.
        player.setName(playerName); // The name of the player singleton instance is assigned.
        
        dungeon.initialise();       // With a difficulty level selected, the dungeon for the current game can be initialised.
        dungeon.start();            // After initialisation, the dungeon can start.
        
        while(!dungeon.isComplete()){}      // A empty loop to wait until the dungeon is complete.
        
        Instant endTime = Instant.now();    // Records the end time of the game. Used in the calculation of game run time.    
        gameDuration = Duration.between(startTime, endTime);    // Calculation of game run time.
        
        // If the player didn't quit the game, they're congratulated for finishing it.
        if(!dungeon.playerQuit && !player.isDead()){
            System.out.println(playerName + " completed the dungeon!");
        }
        
        System.out.println("---------------------------------------------------------");
        System.out.println("Saving game results...");        
        this.complete();    // The game is completed.
        saveGameResults();  // The data for the current game is saved.  
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void complete() {
        isComplete = true;
    }
    /**
     * 
     * @return whether the player has played the game before or not based on whether a user data file exists.
     */    
    private boolean lastGameExists(){
        File tempFile = new File("userdata/runstats.txt");
        
        return tempFile.exists();   
    }
    
    /**
     * Gets the last game's data (player name and game duration) from a .txt file.
     */
    private Database getLastGameResults() {
        String filePath = "userdata/runstats.txt";
        
        DataFileReader fileReader = new DataFileReader(filePath);
        fileReader.readFileData();
        Database temp = fileReader.database;
        
        return temp;        
    }
    
    /**
     * Saves the current game's data (player name and game duration) to a .txt file.
     */
    private void saveGameResults(){
        String filePath = "userdata/runstats.txt";
        
        ArrayList<String> schema = new ArrayList<>();
        schema.add(0, "playerName");
        schema.add(1, "gameDuration");
        
        DataFileWriter dfw = new DataFileWriter("userdata/runstats.txt", schema);
        Database runData = dfw.database;
        String dataRow = playerName + ", " + gameDuration.getSeconds();

        runData.addDataRow("Last Game", dataRow);
        dfw.writeFileData();    
    }
    
}
