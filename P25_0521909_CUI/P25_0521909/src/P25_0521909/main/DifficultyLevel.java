package P25_0521909.main;

import P25_0521909.data.DataRow;
import P25_0521909.interfaces.IDebugable;
import java.util.ArrayList;

/**
 * DifficultyLevel is an object that represents the properties of a dungeon.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public class DifficultyLevel implements IDebugable {
    public final String difficulty;     // The label of the difficulty level.
    public final int roomNumber;        // The number of rooms the dungeon has.
    public final double pBattleRoom;    // The probability that the player will encounter a common enemy battle.
    public final double pCampRoom;      // The probability that the player will encounter a camp.
    public final double pMerchantRoom;  // The probability that the player will encounter a merchant.
    
    /**
     * To construct a difficulty level, the user needs to pass a row of
     * data containing the difficulty level's values, and the data field names
     * contained in a database schema.
     * 
     * @param difficultyLevelData
     * @param dataFields
     */
    public DifficultyLevel(DataRow difficultyLevelData, ArrayList<String> dataFields){        
        difficulty = (String) difficultyLevelData.getDatum(dataFields.indexOf("difficulty"));
        roomNumber = Integer.parseInt((String)difficultyLevelData.getDatum(dataFields.indexOf("roomNumber")));
        pBattleRoom = Double.parseDouble((String)difficultyLevelData.getDatum(dataFields.indexOf("pBattleRoom")));
        pCampRoom = Double.parseDouble((String)difficultyLevelData.getDatum(dataFields.indexOf("pCampRoom")));
        pMerchantRoom = Double.parseDouble((String)difficultyLevelData.getDatum(dataFields.indexOf("pMerchantRoom")));
    }   
    
    @Override
    public void printInfo(){
        System.out.println("Difficulty Level: " + difficulty);
        System.out.println("Number of Rooms: " + roomNumber);
        System.out.println("Probability of Battle Room: " + pBattleRoom);
        System.out.println("Probability of Event Room: " + pCampRoom);
        System.out.println("Probability of Merchant Room: " + pMerchantRoom);
    }    
}
