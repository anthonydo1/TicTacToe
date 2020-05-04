import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LightStyle {
    
    private JFrame gui;
    private JButton[][] buttons;
    private JButton undo;
    private JTextPane message;
    
    public LightStyle(JFrame gui, JButton[][] buttons, JButton undo, JTextPane message) {
        this.gui = gui;
        this.buttons = buttons;
        this.undo = undo;
        this.message = message;
    }
    
    public void loadStyle() {
        Border border = new LineBorder(new Color(61, 61, 61));
        
        Border emptyBorder = BorderFactory.createEmptyBorder();
        undo.setBorder(emptyBorder);
        undo.setContentAreaFilled(false);
        undo.setFocusable(false);
        undo.setForeground(new Color(255, 77, 77));
        
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j].setBorder(border);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setBackground(new Color(75, 75, 75));
                buttons[i][j].setForeground(new Color(255, 77, 77));
            }
        }
    }
}
