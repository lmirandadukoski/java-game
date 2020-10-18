package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    
    JLabel gameTimerDisplay, playerHealthDisplay;
    
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
        setLayout(new BorderLayout());
        
        JPanel HUDPanel = new JPanel();
        JLabel instructionsText = new JLabel("Left click enemies to attack. Enemies will attack when they reach the middle platform.");
        instructionsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsText.setFont(new Font("Serif", Font.BOLD, 16));
        instructionsText.setForeground(Color.white);
        
        JLabel gameTimerText = new JLabel("Time Left: ");
        gameTimerText.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTimerText.setFont(new Font("Serif", Font.BOLD, 16));
        gameTimerText.setForeground(Color.white);
        
        gameTimerDisplay = new JLabel(GameManager.getInstance().getTimeLeft());
        gameTimerDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameTimerDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        gameTimerDisplay.setForeground(Color.white);
        
        
        HUDPanel.add(instructionsText);
        HUDPanel.add(gameTimerText);
        HUDPanel.add(gameTimerDisplay);
        HUDPanel.setOpaque(false);
        
        JPanel gamePanel = new JPanel();
        JLabel playerHealthText = new JLabel("Your Health: ");
        playerHealthText.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerHealthText.setFont(new Font("Serif", Font.BOLD, 16));
        playerHealthText.setForeground(Color.white);
        
        playerHealthDisplay = new JLabel(Integer.toString(Player.getInstance().getStats().getCurrentHealth()));
        playerHealthDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerHealthDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        playerHealthDisplay.setForeground(Color.white); 
        
        gamePanel.add(playerHealthText);
        gamePanel.add(playerHealthDisplay);
        gamePanel.setOpaque(false);
        
        //create Player health display
        //create inventory display
        
        this.add(HUDPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.SOUTH);
    }
    
    void updateDisplay(){
        gameTimerDisplay.setText(GameManager.getInstance().getTimeLeft());
        playerHealthDisplay.setText(Integer.toString(Player.getInstance().getStats().getCurrentHealth()));
        
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

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
        updateDisplay();
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
