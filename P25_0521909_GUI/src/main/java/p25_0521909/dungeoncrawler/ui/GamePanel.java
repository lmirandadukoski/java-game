package p25_0521909.dungeoncrawler.ui;

import javax.swing.JPanel;

import p25_0521909.dungeoncrawler.constants.PanelName;
import p25_0521909.dungeoncrawler.interfaces.Initialisable;

/**
 *
 * @author ludmi
 */
public abstract class GamePanel extends JPanel implements Initialisable{
    private final PanelName NAME;
    
    public GamePanel(PanelName name){
        this.NAME = name;
    }

    public PanelName getPanelName(){
        return NAME;
    }    
}
