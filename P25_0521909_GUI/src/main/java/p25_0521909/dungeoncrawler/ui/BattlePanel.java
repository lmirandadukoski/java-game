package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;

import p25_0521909.dungeoncrawler.constants.*;
import p25_0521909.dungeoncrawler.enemy.Enemy;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.game.*;
import p25_0521909.dungeoncrawler.player.Player;
import p25_0521909.dungeoncrawler.interfaces.Loopable;

/**
 *
 * @author ludmi
 */
public class BattlePanel extends GamePanel implements MouseListener, Loopable, Observer{   
    private EnemyController enemyController;
    private Timer timer;
    
    public BattlePanel(){
        super(PanelName.BATTLE);
    }

    @Override
    public void initialise() {
        addMouseListener(this);
        GameManager.getInstance().addEventListener(EventName.START_GAME, this);
        GameManager.getInstance().addEventListener(EventName.STOP_GAME, this);
        
        enemyController = GameManager.getInstance().getEnemyController();
        timer = new Timer(Constants.FRAME_UPDATE_RATE, new GameLoop(this));
        
        createLayout();
    }
     
    void createLayout(){
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawRect(Constants.SPAWN_POINT_1.x - (148 / 2), Constants.SPAWN_POINT_1.y - 130, 148, 130);
        g.drawRect(Constants.SPAWN_POINT_2.x - (148 / 2), Constants.SPAWN_POINT_2.y - 130, 148, 130);
        g.drawRect(Constants.SPAWN_POINT_3.x - (148 / 2), Constants.SPAWN_POINT_3.y - 130, 148, 130);
        
        g.drawRect(Constants.TARGET_POINT_1.x - (148 / 2), Constants.TARGET_POINT_1.y - 130, 148, 130);
        g.drawRect(Constants.TARGET_POINT_2.x - (148 / 2), Constants.TARGET_POINT_2.y - 130, 148, 130);
        g.drawRect(Constants.TARGET_POINT_3.x - (148 / 2), Constants.TARGET_POINT_3.y - 130, 148, 130);

        for(Enemy enemy : enemyController.enemies){
            Image enemyImage = enemy.getGraphics().getSprite().getSpriteImage();
            Dimension spriteDimension = enemy.getGraphics().getSprite().getSpriteDimension();
            Point currentLocation = enemy.getGraphics().getCurrentLocation();  
            g.drawImage(enemyImage, currentLocation.x - (spriteDimension.width / 2), currentLocation.y - spriteDimension.height, this);
        } 
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        Point point = e.getPoint();
        
        for(Enemy enemy : enemyController.enemies){
            Point currentLocation = enemy.getGraphics().getCurrentLocation();
            Dimension spriteDimension = enemy.getGraphics().getSprite().getSpriteDimension();
            Rectangle imageBounds = new Rectangle(currentLocation.x - (spriteDimension.width / 2), currentLocation.y - spriteDimension.height, spriteDimension.width, spriteDimension.height);
            
            if (imageBounds.contains(point)){
                Player.getInstance().getStats().attack(enemy.getStats());
            }            
        }   
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void loop() {
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEvent event = (GameEvent) o;
        
        if(event.equals(EventName.START_GAME)){
            timer.start();
        } 
        
        if(event.equals(EventName.STOP_GAME)){
            timer.stop();
        } 
    }  
}
