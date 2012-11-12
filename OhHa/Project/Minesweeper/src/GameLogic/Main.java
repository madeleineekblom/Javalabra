package GameLogic;

/*
 * @author Madeleine Ekblom
 * @since 2012-10-21
 */

//Prints out a textversion of the gameboard
public class Main {
    public static void main(String[] args) {
        Game minesweeper = new Game(10,10,20);
        char[][] board = minesweeper.createGame();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
        
    
    }
    
}
