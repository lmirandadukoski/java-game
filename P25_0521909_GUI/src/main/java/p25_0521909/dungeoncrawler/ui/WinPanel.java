package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import p25_0521909.dungeoncrawler.constants.PanelName;

/**
 *
 * @author ludmi
 */
public class WinPanel extends GamePanel{
    private Dimension frameSize;

    
    public WinPanel(){
        super(PanelName.WIN);
    }

    @Override
    public void initialise() {
        frameSize = GameFrame.getInstance().getSize();
        
        createLayout();
    }
     
    void createLayout(){
        setLayout(null);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(16, 16, 16, 16));  
        contentPanel.setSize(new Dimension(frameSize.width / 2, frameSize.height / 2));
        contentPanel.setBounds(frameSize.width / 4, frameSize.height / 5, frameSize.width / 2, frameSize.height / 2);
        contentPanel.setOpaque(false);
        
        JLabel title = new JLabel("You won!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 16));
        title.setForeground(Color.white);
        contentPanel.add(title);
        contentPanel.add(Box.createVerticalStrut(8)); 
        
        JLabel subtitle = new JLabel("Survive a hoard of enemies for 1 minute.");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("Serif", Font.PLAIN, 11));
        subtitle.setForeground(Color.white);
        contentPanel.add(subtitle);
        contentPanel.add(Box.createVerticalStrut(32));  
        


        this.add(contentPanel); 
    }
     
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        
        int width = GameFrame.getInstance().getSize().width;
        int height = GameFrame.getInstance().getSize().height;
        
        Rectangle presetsBackground = new Rectangle(width / 4, height / 5, width / 2, height / 2);
        Color color = new Color(0, 0, 0, 0.7F); //Red 
        g2D.setPaint(color);
        g2D.fill(presetsBackground);     
    }

}
