import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameController {
    
    private GameModel model;
    private GameView view;
    
    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        createListeners();
    }
    
    /**
     * Creates the listeners for the buttons.
     */
    private void createListeners() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                createActionListener(i,j);
            }
        }
        
        view.getUndoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.undo();
            }
        });
    }
    
    /**
     * Creates an action listener for the game buttons given a row and column.
     * @param row the row of the button
     * @param col the column of the button
     */
    private void createActionListener(int row, int col) {
        JButton[][] buttons = view.getButtons();
        buttons[row][col].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.makeMove(row, col);
            }
        });
    }
}
