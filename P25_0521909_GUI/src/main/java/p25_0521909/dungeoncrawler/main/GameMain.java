package p25_0521909.dungeoncrawler.main;

import java.awt.EventQueue;
import p25_0521909.dungeoncrawler.game.GameManager;
import p25_0521909.dungeoncrawler.ui.GameFrame;

/**
 *
 * @author ludmi
 */
public class GameMain {
    static GameFrame gf;
    static GameManager gm;
    
    public static void main(String[] args) {
        EventQueue.invokeLater( ()-> {
            gf = GameFrame.getInstance();
            gm = GameManager.getInstance();
            
            
            gf.initialiseValues();
            gm.initialiseValues();
        });   
    }
    
}
