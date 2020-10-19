package p25_0521909.dungeoncrawler.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import p25_0521909.dungeoncrawler.constants.*;
import p25_0521909.dungeoncrawler.game.Difficulty;
import p25_0521909.dungeoncrawler.game.GameManager;
import p25_0521909.dungeoncrawler.interfaces.Displayable;

/**
 *
 * @author ludmi
 */
public class StartPanel extends GamePanel implements Displayable, ActionListener{
    private JButton startButton;
    private ButtonGroup difficultyOptions;
    
    public StartPanel(){
        super(PanelName.START);
    }

    @Override
    public void initialise() {       
        createDisplay();
    }
    
    @Override 
    public void createDisplay(){
        setLayout(null);
        
        JPanel presetsPanel = new JPanel();
        presetsPanel.setLayout(new BoxLayout(presetsPanel, BoxLayout.Y_AXIS));
        presetsPanel.setBorder(new EmptyBorder(Constants.BORDER_MARGIN, Constants.BORDER_MARGIN, Constants.BORDER_MARGIN, Constants.BORDER_MARGIN));  
        presetsPanel.setSize(new Dimension(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2));
        presetsPanel.setBounds(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 5, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        presetsPanel.setOpaque(false);
        
        JLabel title = new JLabel("Dungeon Crawler");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 16));
        title.setForeground(Color.white);
        presetsPanel.add(title);
        presetsPanel.add(Box.createVerticalStrut(Constants.BORDER_MARGIN)); 
        
        JLabel subtitle = new JLabel("Survive a hoard of enemies for 1 minute.");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("Serif", Font.PLAIN, 11));
        subtitle.setForeground(Color.white);
        presetsPanel.add(subtitle);
        presetsPanel.add(Box.createVerticalStrut(Constants.BORDER_MARGIN * 2));  
        
        JLabel difficultyTitle = new JLabel("Choose your difficulty");
        difficultyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultyTitle.setFont(new Font("Serif", Font.BOLD, 11));
        difficultyTitle.setForeground(Color.white);
        presetsPanel.add(difficultyTitle);
        presetsPanel.add(Box.createVerticalStrut(Constants.BORDER_MARGIN));  
        
        JPanel difficultySelectionPanel = new JPanel();
        difficultySelectionPanel.setSize(new Dimension(Constants.SCREEN_WIDTH / 2, 10));
        difficultySelectionPanel.setOpaque(false);
        
        JRadioButton easyButton = new JRadioButton("Easy");
        easyButton.setFont(new Font("Serif", Font.PLAIN, 11));
        easyButton.setForeground(Color.white);        
        easyButton.setOpaque(false);
        easyButton.setActionCommand(DifficultyName.EASY.toString());
        
        JRadioButton normalButton = new JRadioButton("Normal", true);
        normalButton.setFont(new Font("Serif", Font.PLAIN, 11));
        normalButton.setForeground(Color.white);         
        normalButton.setOpaque(false);
        normalButton.setActionCommand(DifficultyName.NORMAL.toString());
        
        JRadioButton hardButton = new JRadioButton("Hard");
        hardButton.setFont(new Font("Serif", Font.PLAIN, 11));
        hardButton.setForeground(Color.white);         
        hardButton.setOpaque(false);
        hardButton.setActionCommand(DifficultyName.HARD.toString());
        
        difficultyOptions = new ButtonGroup();
        difficultyOptions.add(easyButton);
        difficultyOptions.add(normalButton);
        difficultyOptions.add(hardButton);
        
        difficultySelectionPanel.add(easyButton);
        difficultySelectionPanel.add(normalButton);
        difficultySelectionPanel.add(hardButton);        
        
        presetsPanel.add(difficultySelectionPanel);
        presetsPanel.add(Box.createVerticalStrut(Constants.BORDER_MARGIN)); 

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

        Rectangle presetsBackground = new Rectangle(Constants.SCREEN_WIDTH / 4, Constants.SCREEN_HEIGHT / 5, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        Color color = new Color(0, 0, 0, 0.7F);
        g2D.setPaint(color);
        g2D.fill(presetsBackground);     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        if(actionCommand.equals("Start")){
            int difficultySelection = Integer.parseInt(difficultyOptions.getSelection().getActionCommand());
            Difficulty.setDifficultyIndex(difficultySelection);
            GameFrame.getInstance().switchGamePanels(PanelName.BATTLE);
            GameManager.getInstance().startGame();
        }
    }
}
