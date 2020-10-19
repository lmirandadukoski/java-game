package p25_0521909.dungeoncrawler.game;

/**
 *
 * @author ludmi
 */
public class Difficulty {
    private static int difficultyIndex;
    
    private Difficulty(){}
    
    public static int getDifficultyIndex(){
        return difficultyIndex;
    }
    
    public static void setDifficultyIndex(int value){
        difficultyIndex = value;
    } 
}
