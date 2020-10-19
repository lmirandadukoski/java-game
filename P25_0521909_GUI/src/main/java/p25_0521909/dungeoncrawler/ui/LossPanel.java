package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import p25_0521909.dungeoncrawler.constants.Constants;
import p25_0521909.dungeoncrawler.constants.PanelName;
import p25_0521909.dungeoncrawler.interfaces.Displayable;

/**
 *
 * @author ludmi
 */
public class LossPanel extends GamePanel implements Displayable{
    
    public LossPanel(){
        super(PanelName.LOSS);
    }

    @Override
    public void initialise() {        
        createDisplay();
    }
    
    @Override 
    public void createDisplay(){
        setLayout(null);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(Constants.BORDER_MARGIN, Constants.BORDER_MARGIN, Constants.BORDER_MARGIN, Constants.BORDER_MARGIN));  
        contentPanel.setSize(new Dimension(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2));
        contentPanel.setBounds(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 5, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        contentPanel.setOpaque(false);
        
        JLabel title = new JLabel("You lost...");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 16));
        title.setForeground(Color.white);
        contentPanel.add(title);
        
        this.add(contentPanel); 
    }
     
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        Rectangle presetsBackground = new Rectangle(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 5, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        Color color = new Color(0, 0, 0, 0.7F);
        g2D.setPaint(color);
        g2D.fill(presetsBackground);     
    }
}
