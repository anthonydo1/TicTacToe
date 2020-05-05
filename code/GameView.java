import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Serves as the View of the the MVC pattern.
 * @author Anthony Do, Rico Kam, Vito Gano, Tirth Patel
 *
 */
public class GameView implements Observer {

    private GameModel model;
    private JFrame gui;
    private JButton[][] buttons;
    private JButton undo;
    private JTextPane message;
    private JPanel stylePanel;
    
    /**
     * Constructs a GameView given a GameModel.
     * @param model the GameModel
     */
    public GameView(GameModel model) {
        this.gui = new JFrame();
        this.buttons = new JButton[3][3];
        this.message = new JTextPane();
        this.undo = new JButton();
        this.stylePanel = new JPanel(new FlowLayout());
        
        this.model = model;
        
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                buttons[i][j] = new JButton();
            }
        }
        
        initializeBoard();
    }
    
    /**
     * Initializes the board with the styles to choose from.
     */
    private void initializeBoard() {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(500,350);
        gui.setResizable(true);
        
        Style[] styles = {
                new DarkStyle(gui, buttons, undo, message), 
                new LightStyle(gui, buttons, undo, message)};
        
        for (int i = 0; i < styles.length; i++) {
            createStyleButton(styles[i]);
        }
        
        gui.setVisible(true);
    }
    
    /**
     * Creates a JButton for the Style given.
     * @param style The style to create a button for.
     */
    private void createStyleButton(Style style) {
        JButton button = new JButton();
        button.setText(style.getStyleName());
        button.setFocusable(false);
        stylePanel.add(button);
        gui.add(stylePanel, BorderLayout.CENTER);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stylePanel.setVisible(false);
                initializeGame();
                style.loadStyle();
            }
        });
    }
    
    /**
     * Initializes the game's UI elements.
     */
    private void initializeGame() {
        message.setText("X's Turn:");
        
        StyledDocument doc = message.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        JPanel panel = new JPanel();
        JPanel board = new JPanel(new GridLayout(3,3));
        board.setOpaque(false);
        panel.add(board, BorderLayout.CENTER);
        panel.setOpaque(false);
        
        JPanel undoPanel = new JPanel(new FlowLayout());
        undo.setFocusable(false);
        undo.setText("UNDO");
        undoPanel.add(undo);
        undoPanel.setOpaque(false);
        
        gui.add(panel, BorderLayout.CENTER);
        gui.add(message, BorderLayout.NORTH);
        gui.add(undoPanel, BorderLayout.SOUTH);
        
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                JButton button = buttons[i][j];
                //button.setContentAreaFilled(false);
                button.setFocusable(false);
                button.setPreferredSize(new Dimension(75,75));
                button.setText("");
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j] = button;
                board.add(buttons[i][j]);
            }
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        String[][] data = model.getBoardData();
        
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] == "X") {
                    buttons[i][j].setForeground(new Color(255, 77, 77));
                    buttons[i][j].setText(data[i][j]);
                } else {
                    buttons[i][j].setForeground(new Color(23, 192, 235));
                    buttons[i][j].setText(data[i][j]);
                }
                
            }
        }
        if (model.hasWinner()) {
            message.setText(model.getWinner() + " won the game!");
            return;
        }
        message.setText(model.getTurn() + "'s Turn:");
    }

    /**
     * Returns the buttons in the view
     * @return buttons
     */
    public JButton[][] getButtons() {
        return buttons;
    }
    
    /**
     * REturns the undo button
     * @return the undo button
     */
    public JButton getUndoButton() {
        return undo;
    }
}
