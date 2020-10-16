package P25_0521909.rooms;

import P25_0521909.interfaces.IDebugable;
import P25_0521909.interfaces.IInitialisable;
import P25_0521909.interfaces.ICompletable;

/**
 * Room represents an object where certain types of events can happen. Rooms must be
 * completed in order for the player to progress in the dungeon.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public abstract class Room implements ICompletable, IInitialisable, IDebugable{
    private boolean isComplete; // Stores whether the room is complete or not.
    
    public Room(){
        isComplete = false;
    }
    
    @Override
    public void complete() {
        isComplete = true;
    }
    
    @Override
    public boolean isComplete() {
        return isComplete;
    }
    
}
