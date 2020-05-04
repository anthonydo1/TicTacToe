import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * The Dark Theme
 * @author Anthony Do
 *
 */
public class DarkStyle implements Style {
    
    private JFrame gui;
    private JButton[][] buttons;
    private JButton undo;
    private JTextPane message;
    
    public DarkStyle(JFrame gui, JButton[][] buttons, JButton undo, JTextPane message) {
        this.gui = gui;
        this.buttons = buttons;
        this.undo = undo;
        this.message = message;
    }
    
    public void loadStyle() {
        Border border = new LineBorder(new Color(61, 61, 61));
        gui.getContentPane().setBackground(new Color(61, 61, 61));
        
        Font font1 = new Font("Arial", Font.BOLD, 20);
        Font font2 = new Font("Arial", Font.BOLD, 15);
        
        Border emptyBorder = BorderFactory.createEmptyBorder();
        undo.setFont(font2);
        undo.setBorder(emptyBorder);
        undo.setContentAreaFilled(false);
        undo.setFocusable(false);
        undo.setForeground(new Color(255, 77, 77));
        
        message.setOpaque(false);
        message.setFont(font1);
        message.setForeground(new Color(255,255,255));
        message.setEditable(false);
        
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j].setBorder(border);
                buttons[i][j].setBackground(new Color(75, 75, 75));
                buttons[i][j].setForeground(new Color(255, 77, 77));
            }
        }
    }
}
