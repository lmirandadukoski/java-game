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
        GameManager.getInstance().addEventListener("Start Game", this);
        GameManager.getInstance().addEventListener("Stop Game", this);
        
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

        for(int i = 0; i < enemyController.enemies.size(); i++){
            Enemy enemy = enemyController.enemies.get(i);
            Image enemyImage = enemy.getSprite().getSpriteImage();
            Point currentLocation = enemy.getSprite().getCurrentLocation();  
            g.drawImage(enemyImage, currentLocation.x, currentLocation.y, this);
        } 
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        Point point = e.getPoint();

        for(int i = 0; i < enemyController.enemies.size(); i++){
            Enemy enemy = enemyController.enemies.get(i);
            Point currentLocation = enemy.getSprite().getCurrentLocation();
            Rectangle imageBounds = new Rectangle(currentLocation.x, currentLocation.y, enemy.getSprite().getSpriteImage().getWidth(this), enemy.getSprite().getSpriteImage().getHeight(this));
            
            if (imageBounds.contains(point)){
                Player.getInstance().getStats().attack(enemy.getStats());
                //System.out.println(enemy.getStats().isDead());
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
        
        if(event.getEventName().equals("Start Game")){
            timer.start();
        } 
        
        if(event.getEventName().equals("Stop Game")){
            timer.stop();
        } 
    }  
}
