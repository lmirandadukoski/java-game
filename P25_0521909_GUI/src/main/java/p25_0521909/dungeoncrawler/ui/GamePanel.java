package p25_0521909.dungeoncrawler.ui;

import javax.swing.JPanel;

/**
 *
 * @author ludmi
 */
public abstract class GamePanel extends JPanel{
    private String panelName;
    
    public GamePanel(String panelName){
        this.panelName = panelName;
    }
    
    abstract void initialiseValues();
    
    public String getPanelName(){
        return panelName;
    }    
}
