package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import javax.swing.*;

import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.constants.PanelName;
import p25_0521909.dungeoncrawler.graphics.Sprite;
import p25_0521909.dungeoncrawler.interfaces.Initialisable;

/**
 *
 * @author ludmi
 */
public class GameFrame extends JFrame implements Initialisable{
    private static GameFrame instance;
    
    private Container screenContent;
    private GamePanel[] gamePanels;
    
    private Sprite background;
    
    private GameFrame(){}
    
    @Override
    public void initialise(){
        setSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setResizable(false);
        setTitle(Constants.FRAME_TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        
        background = new Sprite(Constants.BACKGROUND_IMAGE_PATH);
        
        createLayout();        
    }
    
    void createLayout(){
        gamePanels = new GamePanel[]{new StartPanel(), new BattlePanel(), new WinPanel(), new LossPanel()};
        
        screenContent = new JPanel(new CardLayout()){
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(background.getSpriteImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        
        for(GamePanel panel : gamePanels){
            panel.setOpaque(false);
            panel.initialise();
            screenContent.add(panel, panel.getPanelName().toString());
        }
    
        getContentPane().add(screenContent); 
        setVisible(true);
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
        
    public static synchronized GameFrame getInstance(){
        if(instance == null){
            instance = new GameFrame();
        }
        return instance;
    }
    
    public void switchGamePanels(PanelName name){
        CardLayout cardLayout = (CardLayout) screenContent.getLayout();        
        cardLayout.show(screenContent, name.toString());        
    }    
}
