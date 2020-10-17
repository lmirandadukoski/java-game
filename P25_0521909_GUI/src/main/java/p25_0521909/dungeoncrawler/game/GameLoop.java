package p25_0521909.dungeoncrawler.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import p25_0521909.dungeoncrawler.interfaces.Loopable;


/**
 *
 * @author ludmi
 */
public class GameLoop implements ActionListener{
    
    private Loopable loopable;
    
    public GameLoop(Loopable updatable){
        this.loopable = updatable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loopable.loop();
    } 
}
