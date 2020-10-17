package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import javax.swing.*;

import p25_0521909.dungeoncrawler.constants.PanelName;
import p25_0521909.dungeoncrawler.graphics.BackgroundSprite;
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
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        setResizable(false);
        setTitle("Dungeon Crawler");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        
        background = new BackgroundSprite("sprites/background.jpg");
        
        createLayout();        
    }
    
    void createLayout(){
        gamePanels = new GamePanel[]{new StartPanel(), new BattlePanel()};
        
        screenContent = new JPanel(new CardLayout()){
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(background.getSpriteImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        
        for(int i = 0; i < gamePanels.length; i++){
            gamePanels[i].setOpaque(false);
            gamePanels[i].initialise();
            System.out.println(gamePanels[i].getPanelName());
            screenContent.add(gamePanels[i], gamePanels[i].getPanelName().toString());
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
    
    void switchGamePanels(PanelName name){
        CardLayout cardLayout = (CardLayout) screenContent.getLayout();        
        cardLayout.show(screenContent, name.toString());        
    }    
}
