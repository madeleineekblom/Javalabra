package UserInterface;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11
 */
public class MyMouseListener extends MouseAdapter implements ActionListener {

    GameBoard board;
    private boolean running = false;
    boolean lost = false;
    ImageIcon mine = new ImageIcon("Pictures/mine.gif");
    ImageIcon flag = new ImageIcon("Pictures/flag.gif");
    private long stopTime;
    private long startTime;
    boolean[][] flags;
    WinLose winlose;
    int r;
    int c;
    int m;

    /** Constructor
     * 
     * @param b     the gameboard that will use MyMouseListener
     */
    public MyMouseListener(GameBoard b) {
        this.board = b;
        r = board.move.game.getRows();
        c = board.move.game.getColumns();
        m = board.move.game.getMines();
        flags = new boolean[r][c];
        Image image1 = mine.getImage();
        Image image2 = flag.getImage();
        image1 = image1.getScaledInstance(50,50, Image.SCALE_SMOOTH); // 50x50 works for 10x10
        image2 = image2.getScaledInstance(50,50, Image.SCALE_REPLICATE);
        mine.setImage(image1);
        flag.setImage(image2);
        this.winlose = new WinLose();

    }

    /**
     * checks whether the mouseevent is caused by the left button or not
     * 
     * @param e     the input
     * @return      returns true if the right button has been clicked
     */
    private boolean rightButton(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON3;
    }

    /**
     * checks whether the mouseevent is caused by the right button or not
     *  
     * @param e     the input
     * @return      returns true if the left button has been clicked
     */
    private boolean leftButton(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON1;
    }

    /**
     * Depending on which jframe button and mouse button is clicked, 
     * this method will do different stuff.
     * 
     * @param e     input from mouse 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Object b = e.getSource();

        if (b instanceof JButton) {
            if (running == false) {
                running = true;
                startTime = e.getWhen();
            }
            int[] coord = findCorrectButton(b);

            if (leftButton(e)) {
                openButtons(coord[0], coord[1]);
            } else if (rightButton(e)) {
                markWithFlag(coord[0], coord[1]);
            }
            if (board.move.win()) {
                stopTime = e.getWhen();
                winlose.setGameBoard(r, c, m);
                winlose.win(countTime());
                
//                board.dispose();
            } else if (lost) {
                stopTime = e.getWhen();
                winlose.setGameBoard(r, c, m);
                winlose.lose(countTime());
//                board.dispose();
            }
        }
    }

    /**
     * Finds the coordinate of a pressed button
     * 
     * @param o     the button that has been pressed
     * @return      returns the coordinates of the button in an integer array
     *              where the first element is the row-coordinate and the
     *              second the column-coordinate
     */
    public int[] findCorrectButton(Object o) {
        int[] coord = {-1, -1};
        for (int i = 0; i < board.move.game.getRows(); i++) {
            for (int j = 0; j < board.move.game.getColumns(); j++) {
                if (o == board.buttons[i][j]) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        return coord;
    }

    /**
     * Creates a new game
     */
    private void reset(int n) {
        board.dispose();
        if (n == 1) {
            board = new GameBoard(9, 9, 10);
        } else if (n == 2) {
            board = new GameBoard(16,16,40);
        } else if (n == 3) {
            board = new GameBoard(30,16,99);
        }
    }

    /**
     * Opens the button. If it is a mine, the game is lost. If it is empty,
     * it will open all the surrounding empty squares.
     * 
     * @param row       the button's row-coordinate
     * @param column    the button's column-coordinate
     */
    public void openButtons(int row, int column) {
        board.move.openButtons(row, column);
        boolean[][] help = board.move.help;

        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i].length; j++) {
                if (help[i][j]) {
                    if (board.move.matrix[i][j] == '*') {
                        lost = true;
                        board.buttons[i][j].setEnabled(false);
                        board.buttons[i][j].setIcon(mine);
                        break;
                    } else {
                        char c = board.move.matrix[i][j];
                        if (c != '0') {
                            board.buttons[i][j].setText(Character.toString(c));
                        } else {
                            board.buttons[i][j].setText("");
                        }
                        board.buttons[i][j].setEnabled(false);
                    }
                }
            }
        }
        return;
    }

    /**
     * Marks the button with a flag. A flag means that the player thinks that 
     * the button hides a mine.
     * 
     * @param row      button's row coordinate
     * @param column   button's column coordinate
     */
    public void markWithFlag(int row, int column) {

        if (!board.buttons[row][column].isEnabled()) {
            return;
        }
        if (flags[row][column]) {
            board.move.unFlagSquare(row, column);
            board.buttons[row][column].setIcon(null);
            flags[row][column] = false;
        } else {

            board.move.markWithFlags(row, column);
            board.buttons[row][column].setIcon(flag);
            flags[row][column] = true;
        }
        board.flags.setText(Integer.toString(board.move.getFlags()));

    }

    /**
     * 
     * @return How many seconds that has elapsed from the first opened button until the game stops
     */
    public int countTime() {
        return (int) ((stopTime - startTime) / 1000.0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == board.master) {
            reset(3);
        }
        if (e.getSource() == board.medio) {
            reset(2);
        }
        if (e.getSource() == board.beginner) {
            reset(1);
        }
        if (e.getSource() == board.quit) {
            System.exit(0);
        }
    }
}