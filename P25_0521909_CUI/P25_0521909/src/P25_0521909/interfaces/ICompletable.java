package P25_0521909.interfaces;

/**
 * ICompletable represents an object that can have 2 states:
 * incomplete and complete.
 * The object can be started so that a switch between the
 * two states can happen.
 * 
 * @author Ludmila Miranda-Dukoski (ID# 0521909)
 *
 */
public interface ICompletable {
    
    /**
     * Intended to hold the main functions and features of the
     * object.
     * 
     */
    public void start();
    
    /**
     *
     * @return whether the object is in the complete or incomplete
     * state.
     * 
     */
    public boolean isComplete();
    
    /**
     * Switches the object to the complete state.
     * 
     */
    public void complete();
    
}
