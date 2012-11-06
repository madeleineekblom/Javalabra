package UserInterface;


import GameLogic.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11-01
 */
public class GameBoard extends JFrame{

    private JButton[][] buttons;
    private JButton startNewGame;
    private char[][] matrix;
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
            
            minesweeper = new Game(rows, columns, mines);
            matrix = minesweeper.createGame();
            setLayout(new GridLayout(minesweeper.getRows()+1, minesweeper.getColumns()));
            buttons = new JButton[minesweeper.getRows()][minesweeper.getColumns()];
            
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    
                    buttons[i][j] = new JButton(Character.toString(matrix[i][j]));
                    add(buttons[i][j]);
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
    
     
}
