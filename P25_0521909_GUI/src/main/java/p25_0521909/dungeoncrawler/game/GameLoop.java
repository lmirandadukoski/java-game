package p25_0521909.dungeoncrawler.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import p25_0521909.dungeoncrawler.interfaces.Updatable;


/**
 *
 * @author ludmi
 */
public class GameLoop implements ActionListener{
    
    private Updatable updatable;
    
    public GameLoop(Updatable updatable){
        this.updatable = updatable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updatable.executeGameLoop();
    }
    
}
