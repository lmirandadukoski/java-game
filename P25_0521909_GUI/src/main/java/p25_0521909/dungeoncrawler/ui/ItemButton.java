package p25_0521909.dungeoncrawler.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import p25_0521909.dungeoncrawler.interfaces.Displayable;
import p25_0521909.dungeoncrawler.item.Item;
import p25_0521909.dungeoncrawler.player.Player;

/**
 *
 * @author ludmi
 */
public class ItemButton extends JButton implements Displayable, ActionListener{
    final Item ITEM;
    int quantity;
    
    public ItemButton(Item item, int quantity){
        ITEM = item;
        this.quantity = quantity;
    }
    
    @Override
    public void createDisplay() {
        setText(ITEM.getName() + " (" + quantity + ")");
        setToolTipText(ITEM.getDescription());
        setFont(new Font("Serif", Font.BOLD, 12));
        setForeground(Color.white);  
        setBackground(Color.blue);
        setAlignmentX(Component.CENTER_ALIGNMENT); 
        setPreferredSize(new Dimension(200, 50));
        addActionListener(this);
    }    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        ITEM.consume();
        
        Player.getInstance().getInventory().removeItem(ITEM);
        quantity --;
        setText(ITEM.getName() + "(" + quantity + ")");
        
        if(quantity == 0){
            setEnabled(false);
        }
    }  
}
