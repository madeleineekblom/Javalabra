package UserInterface;

import GameLogic.Game;
import java.awt.*;
//import java.awt.event.*;
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
    private JPanel panel;
    private MyMouseListener mouse;

    // JFrame frame;
    /** Constructor
     * 
     * @param rows      numbers of rows in the gameboard
     * @param columns   numbers of columns in the gameboard
     * @param mines     numbers of mines in the gameboard
     */
    public GameBoard(final int rows, final int columns, final int mines) {

        minesweeper = new Game(rows, columns, mines);
        visited = new boolean[minesweeper.getRows()][minesweeper.getColumns()];
        buttons = new JButton[minesweeper.getRows()][minesweeper.getColumns()];
        mouse = new MyMouseListener(this);

        createGameBoard();


    }

    /**
     * Creates a new jframe with a gridlayout, eg. the gameboard. The gameboard 
     * contains square-buttons and a new game-button.
     */
    private void createGameBoard() {
        //frame = new JFrame("Minesweeper");
        this.setTitle("Minesweeper");

        this.setResizable(true);
        this.setPreferredSize(new Dimension(minesweeper.getRows() * 100, minesweeper.getColumns() * 100));
//        this.setLayout(new GridLayout(minesweeper.getRows() + 1, minesweeper.getColumns()));
        addButtonsToFrame();
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.validate();
    }

    private void addButtonsToFrame() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(minesweeper.getRows() + 1, minesweeper.getColumns()));
        matrix = minesweeper.createGame();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(new Dimension(5, 5));
                buttons[i][j].setEnabled(true);
                buttons[i][j].addMouseListener(mouse);
                panel.add(buttons[i][j]);

            }
        }

        startNewGame = new JButton("New Game");
        startNewGame.setSize(new Dimension(5, 5));
        panel.add(startNewGame);
        startNewGame.addMouseListener(mouse);
        this.add(panel);
    }

    /**
     * resets the buttons and creates a new gameboard
     */
    public void reset() {
        matrix = minesweeper.createGame();
        startNewGame.setText("reset");
        for (int i = 0; i < minesweeper.getRows(); i++) {
            for (int j = 0; j < minesweeper.getColumns(); j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        
    }
}
