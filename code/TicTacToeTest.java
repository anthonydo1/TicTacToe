
public class TicTacToeTest {
    
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        
        model.addObserver(view);
    }
    
}