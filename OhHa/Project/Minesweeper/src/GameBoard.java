
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11-01
 */
public class GameBoard extends JFrame{

    private int rows;
    private int columns;
    private int mines;
    private JButton startNewGame;
    private String[][] matrix;
    Game minesweeper;
    
    /**
     * Constructor for the gameboard
     * Creates a gameboard 
     * 
     * @param rows      numbers of rows in the gameboard
     * @param columns   numbers of columns in the gameboard
     * @param mines     numbers of mines in the gameboard
     */
    public GameBoard(final int rows, final int columns, final int mines) {
            this.rows = 10;
            this.columns = columns;
            this.mines = 10;
            
            minesweeper = new Game(rows, columns, mines);
            matrix = minesweeper.createGame();
            setLayout(new GridLayout(rows+1,columns));
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    add(new JButton(matrix[i][j]));
                }
            }
            
            startNewGame = new JButton("New Game");
            add(startNewGame);
            
            startNewGame.addActionListener(
            new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent event) {
        
                    }
                }
           );
    }
    /**
     * Main
     * @param args 
     */
    public static void main(String[] args) {
        GameBoard window = new GameBoard(5,5,10);
        window.setTitle("Minesweeper");
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        
    }
     
}
