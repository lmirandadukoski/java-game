package p25_0521909.dungeoncrawler.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import p25_0521909.dungeoncrawler.interfaces.Loopable;

/**
 *
 * @author ludmi
 */
public class GameLoop implements ActionListener{
    
    private final Loopable LOOPABLE;
    
    public GameLoop(Loopable updatable){
        this.LOOPABLE = updatable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOOPABLE.loop();
    } 
}
