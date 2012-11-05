/*
 * @author Madeleine Ekblom
 * @since 2012-10-21
 */
public class Main {
    public static void main(String[] args) {
        Game minesweeper = new Game(2,2,1);
        String[][] board = minesweeper.createGame();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
        
    
    }
    
}
