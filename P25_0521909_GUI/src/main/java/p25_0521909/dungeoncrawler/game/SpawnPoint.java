package p25_0521909.dungeoncrawler.game;

import java.awt.Point;

/**
 *
 * @author ludmi
 */
public class SpawnPoint extends Point{
    private boolean isAvailable;
    
    public SpawnPoint(int x, int y){
        isAvailable = true;
    }
    
    public boolean getAvailability(){
        return isAvailable;
    }
    
    public void setAvailability(boolean value){
        isAvailable = value;
    }
}
