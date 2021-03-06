package p25_0521909.dungeoncrawler.main;

import java.awt.EventQueue;

import p25_0521909.dungeoncrawler.game.GameManager;
import p25_0521909.dungeoncrawler.player.Player;
import p25_0521909.dungeoncrawler.ui.GameFrame;

/**
 *
 * @author ludmi
 */
public class GameMain {
    private static GameFrame gf;
    private static GameManager gm;
    private static Player p;
    
    public static void main(String[] args) {
        EventQueue.invokeLater( ()-> {
            gm = GameManager.getInstance();
            gm.initialise();
            
            p = Player.getInstance();
            p.initialise();
            
            gf = GameFrame.getInstance();
            gf.initialise();
            
            
        });   
    }
    
}
