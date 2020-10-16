package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;

import p25_0521909.dungeoncrawler.enemy.Enemy;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.game.EnemyController;
import p25_0521909.dungeoncrawler.game.GameLoop;
import p25_0521909.dungeoncrawler.game.GameManager;
import p25_0521909.dungeoncrawler.interfaces.Updatable;
import p25_0521909.dungeoncrawler.player.Player;

/**
 *
 * @author ludmi
 */
public class BattlePanel extends GamePanel implements MouseListener, Updatable, Observer{   
    private EnemyController enemyController;
    private Timer timer;
    
    public BattlePanel(){
        super("Battle");
    }

    @Override
    void initialiseValues() {
        addMouseListener(this);
        enemyController = GameManager.getInstance().getEnemyController();
        timer = new Timer(10, new GameLoop(this));
        
        createLayout();
    }
     
    void createLayout(){
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //Enemy enemy = enemyController.enemies.get(0);
        //Image enemyImage = enemy.getEnemySprite().getSpriteImage();
        //Point currentLocation = enemy.getEnemySprite().getCurrentLocation();  
        

        //g.drawImage(enemyImage, currentLocation.x, currentLocation.y, this);

        
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        Point point = e.getPoint();

        Enemy enemy = enemyController.enemies.get(0);
        Point currentLocation = enemy.getEnemySprite().getCurrentLocation();
        Rectangle imageBounds = new Rectangle(currentLocation.x, currentLocation.y, enemy.getEnemySprite().getSpriteImage().getWidth(this), enemy.getEnemySprite().getSpriteImage().getHeight(this));
        if (imageBounds.contains(point)){
            Player.getInstance().getStats().attack(enemy.getEnemyStats());
            System.out.println(enemy.getEnemyStats().isDead());
        }
        else{
            System.out.println(point);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void executeGameLoop() {
        update();
        repaint();
        
    }

    @Override
    public void update() {
        //System.out.println("battle panel update");
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
