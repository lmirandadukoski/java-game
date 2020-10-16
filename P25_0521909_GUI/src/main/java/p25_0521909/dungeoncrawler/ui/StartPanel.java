package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import p25_0521909.dungeoncrawler.events.GameEvent;
import p25_0521909.dungeoncrawler.game.GameManager;

/**
 *
 * @author ludmi
 */
public class StartPanel extends GamePanel implements ActionListener{
    private Dimension frameSize;
    private JButton startButton;
    private ButtonGroup difficultyOptions;
    
    private GameEvent startButtonPressed;
    
    public StartPanel(){
        super("Start");
    }

    @Override
    void initialiseValues() {
        frameSize = GameFrame.getInstance().getSize();
        
        startButtonPressed = new GameEvent("Start Button Pressed");
        startButtonPressed.addObserver(GameFrame.getInstance());
        startButtonPressed.addObserver(GameManager.getInstance());
        
        createLayout();
    }
     
    void createLayout(){
        setLayout(null);
        
        JPanel presetsPanel = new JPanel();
        presetsPanel.setLayout(new BoxLayout(presetsPanel, BoxLayout.Y_AXIS));
        presetsPanel.setBorder(new EmptyBorder(16, 16, 16, 16));  
        presetsPanel.setSize(new Dimension(frameSize.width / 2, frameSize.height / 2));
        presetsPanel.setBounds(frameSize.width / 4, frameSize.height / 5, frameSize.width / 2, frameSize.height / 2);
        presetsPanel.setOpaque(false);
        
        JLabel title = new JLabel("Dungeon Crawler");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 16));
        title.setForeground(Color.white);
        presetsPanel.add(title);
        presetsPanel.add(Box.createVerticalStrut(8)); 
        
        JLabel subtitle = new JLabel("Survive a hoard of enemies for 1 minute.");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("Serif", Font.PLAIN, 11));
        subtitle.setForeground(Color.white);
        presetsPanel.add(subtitle);
        presetsPanel.add(Box.createVerticalStrut(32));  
        
        JLabel difficultyTitle = new JLabel("Choose your difficulty");
        difficultyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultyTitle.setFont(new Font("Serif", Font.BOLD, 11));
        difficultyTitle.setForeground(Color.white);
        presetsPanel.add(difficultyTitle);
        presetsPanel.add(Box.createVerticalStrut(8));  
        
        JPanel difficultySelectionPanel = new JPanel();
        difficultySelectionPanel.setSize(new Dimension(frameSize.width / 2, 10));
        difficultySelectionPanel.setOpaque(false);
        
        JRadioButton easyButton = new JRadioButton("Easy");
        easyButton.setFont(new Font("Serif", Font.PLAIN, 11));
        easyButton.setForeground(Color.white);        
        easyButton.setOpaque(false);
        easyButton.setActionCommand("1");
        
        JRadioButton normalButton = new JRadioButton("Normal", true);
        normalButton.setFont(new Font("Serif", Font.PLAIN, 11));
        normalButton.setForeground(Color.white);         
        normalButton.setOpaque(false);
        normalButton.setActionCommand("2");
        
        JRadioButton hardButton = new JRadioButton("Hard");
        hardButton.setFont(new Font("Serif", Font.PLAIN, 11));
        hardButton.setForeground(Color.white);         
        hardButton.setOpaque(false);
        hardButton.setActionCommand("3");
        
        difficultyOptions = new ButtonGroup();
        difficultyOptions.add(easyButton);
        difficultyOptions.add(normalButton);
        difficultyOptions.add(hardButton);
        
        difficultySelectionPanel.add(easyButton);
        difficultySelectionPanel.add(normalButton);
        difficultySelectionPanel.add(hardButton);        
        
        presetsPanel.add(difficultySelectionPanel);
        presetsPanel.add(Box.createVerticalStrut(16)); 

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Serif", Font.BOLD, 12));
        startButton.setForeground(Color.white);  
        startButton.setBackground(Color.blue);
        startButton.setActionCommand("Start");
        startButton.addActionListener(this);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);  
        
        presetsPanel.add(startButton);
        this.add(presetsPanel); 
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        if(actionCommand.equals("Start")){
            String difficultySelection = difficultyOptions.getSelection().getActionCommand();
            startButtonPressed.invokeEvent();
        }
    }
}
