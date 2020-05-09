import java.util.Observable;
import java.util.Stack;

/**
 * Serves as the Model of the MVC pattern.
 * @author Anthony Do, Rico Kam, Vito Gano, Tirth Patel
 *
 */
public class GameModel extends Observable {
    
    private String playerTurn;
    private String[][] board;
    private boolean hasWinner;
    private String winner;
    
    private Stack<int[]> undoList;
    private boolean undoned;
    private int numOfUndosX;
    private int numOfUndosO;
    private String undoTurn;
    
    /**
     * Constructs a GameModel and initializes class variables.
     */
    public GameModel() {
        playerTurn = "X";
        board = new String[3][3];
        hasWinner = false;
        winner = "Nobody";
        undoList = new Stack<>();
        undoned = false;
        numOfUndosX = 0;
        numOfUndosO = 0;
        undoTurn = "X";
    }
    
    /**
     * Game logic for when a player selects a cell.
     * @param row the row
     * @param col the column
     */
    public void makeMove(int row, int col) {
        if (hasWinner) return;
        if (board[row][col] != null) return;
        
        if (playerTurn == "X") {
            board[row][col] = "X";
            playerTurn = "O";
            undoTurn = "X";
            numOfUndosO = 0;
        } else {
            board[row][col] = "O";
            playerTurn = "X";
            undoTurn = "O";
            numOfUndosX = 0;
        }
        
        undoned = false;
        
        undoList.push(new int[] {row, col});
        calculate(row, col);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Undos a player's move.
     */
    public void undo() {
        if (undoList.empty()) return;
        
        if (numOfUndosX == 3 || numOfUndosO == 3 || undoned) return;
        
        if (undoTurn == "X") {
            numOfUndosX++;
        } else {
            numOfUndosO++;
        }
        
        undoned = true;
        
        int[] cell = undoList.pop();
        int row = cell[0];
        int col = cell[1];
        
        if (playerTurn == "X") {
            playerTurn = "O";
        } else {
            playerTurn = "X";
        }
        
        board[row][col] = null;
        hasWinner = false;
        winner = "No one";
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Determines if there is a winner given a row and column.
     * @param row
     * @param col
     */
    public void calculate(int row, int col) {
        String target = board[row][col];
        if (target == null || board[row][col] == null) return;
        
        // Check row
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == null) break;
            if (!board[i][col].equals(target)) break;
            if (i == board.length - 1) {
                hasWinner = true;
                winner = target;
                return;
            }
        }
        
        // Check col
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == null) break;
            if (!board[row][i].equals(target)) break;
            if (i == board.length - 1) {
                hasWinner = true;
                winner = target;
                return;
            }
        }
        
        // Check diagonal
        if (row == col) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] == null) break;
                if (!board[i][i].equals(target)) break;
                if (i == board.length - 1) {
                    hasWinner = true;
                    winner = target;
                    return;
                }
            }
        }
        
        // Check other diagonal
        if (row + col == board.length - 1) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][board.length - 1 - i] == null) break;
                if (!board[i][board.length - 1 - i].equals(target)) break;
                if (i == board.length - 1) {
                    hasWinner = true;
                    winner = target;
                    return;
                }
            }
        }
        
        // Check for stalemate.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) return;
                if (i == board.length - 1 && j == board.length - 1) {
                    hasWinner = true;
                    return;
                }
            }
        }
    }
    
    /**
     * Returns board.
     * @return board
     */
    public String[][] getBoardData() {
        return board;
    }
    
    /**
     * Returns current player turn.
     * @return playerTurn
     */
    public String getTurn() {
        return playerTurn;
    }
    
    /**
     * Returns the winner.
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }
    
    /**
     * Returns true if there is a winner, otherwise false.
     * @return true or false
     */
    public boolean hasWinner() {
        return hasWinner;
    }
}
