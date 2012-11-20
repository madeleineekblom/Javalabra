package UserInterface;


import GameLogic.GameMoves;
import UserInterface.MyMouseListener;
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
    public GameMoves move;
    public JPanel panel;
    private MyMouseListener mouse;
    public JButton flags;

    
    /** Constructor
     * 
     * @param rows      numbers of rows in the gameboard
     * @param columns   numbers of columns in the gameboard
     * @param mines     numbers of mines in the gameboard
     */
    public GameBoard(final int rows, final int columns, final int mines) {

        move = new GameMoves(rows, columns, mines);
        buttons = new JButton[rows][columns];
        mouse = new MyMouseListener(this);
        createGameBoard();
    }

    /**
     * Creates a new jframe with a gridlayout, eg. the gameboard. The gameboard 
     * contains square-buttons and a new game-button.
     */
    private void createGameBoard() {
        //frame = new JFrame("Minesweeper");
        ImageIcon icon = new ImageIcon("Pictures/mine.gif");      
        Image im = icon.getImage();
        im = im.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        this.setIconImage(im);
        this.setTitle("Minesweeper");

        this.setResizable(false);
        this.setPreferredSize(new Dimension(500,500));
//        this.setLayout(new GridLayout(minesweeper.getRows() + 1, minesweeper.getColumns()));
        addButtonsToFrame();
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.validate();
    }

    private void addButtonsToFrame() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(move.game.getRows() + 1, move.game.getColumns()));
        move.createGameBoard();

        for (int i = 0; i < move.matrix.length; i++) {
            for (int j = 0; j < move.matrix[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(3,3);
                buttons[i][j].setEnabled(true);
                buttons[i][j].addMouseListener(mouse);
                panel.add(buttons[i][j]);

            }
        }
        flags = new JButton(Integer.toString(move.getFlags()));
        panel.add(flags);
        startNewGame = new JButton("New Game");
        startNewGame.setSize(3,3);
        panel.add(startNewGame);
        startNewGame.addMouseListener(mouse);
        this.add(panel);
    }
    
    
}
