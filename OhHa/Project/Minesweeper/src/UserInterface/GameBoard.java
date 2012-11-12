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
public class GameBoard extends JFrame {

    public JButton[][] buttons;
    public JButton startNewGame;
    public char[][] matrix;
    Game minesweeper;
    public boolean[][] visited;
    JFrame frame;
    

    /** Constructor
     * 
     * @param rows      numbers of rows in the gameboard
     * @param columns   numbers of columns in the gameboard
     * @param mines     numbers of mines in the gameboard
     */
    public GameBoard(final int rows, final int columns, final int mines) {

        minesweeper = new Game(rows, columns, mines);
        createGameBoard();
        visited = new boolean[minesweeper.getRows()][minesweeper.getColumns()];
        
    }

    /**
     * Creates a new jframe with a gridlayout, eg. the gameboard. The gameboard 
     * contains square-buttons and a new game-button.
     */
    private void createGameBoard() {
        frame = new JFrame("Minesweeper");

        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(minesweeper.getRows() * 100, minesweeper.getColumns() * 100));
        frame.setLayout(new GridLayout(minesweeper.getRows() + 1, minesweeper.getColumns()));
        addButtonsToFrame();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
    private void addButtonsToFrame() {
        matrix = minesweeper.createGame();
        buttons = new JButton[minesweeper.getRows()][minesweeper.getColumns()];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(new Dimension(5, 5));
                frame.getContentPane().add(buttons[i][j]);
                buttons[i][j].setEnabled(true);
                buttons[i][j].addMouseListener(new MyMouseListener(this));
            }
        }
        
        startNewGame = new JButton("New Game");
        startNewGame.setSize(new Dimension(5, 5));
        frame.getContentPane().add(startNewGame);
        startNewGame.addMouseListener(new MyMouseListener(this));
    }

    /**
     * creates a new gameboard
     */
    public void reset() {

        frame.removeAll();
        startNewGame.setText("reset");
        createGameBoard();
//        System.out.println("reset");
    }
}
