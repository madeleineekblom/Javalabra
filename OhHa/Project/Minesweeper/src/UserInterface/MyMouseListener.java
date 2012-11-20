package UserInterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11
 */
public class MyMouseListener extends MouseAdapter {

    GameBoard board;
    private boolean running = false;
    private long startTime;
    private long stopTime;
    boolean lost = false;
    
    /** Constructor
     * 
     * @param w     the gameboard that will use MyMouseListener
     */
    public MyMouseListener(GameBoard w) {
        this.board = w;
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

            if (e.getSource() == board.startNewGame && leftButton(e)) {
                reset();
            }
            
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
            if (win(e)) {
                System.out.println("You won!");
                System.out.println("Time: " + countTime() + " seconds");
            } else if (lost) {
                lost(e);
                
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
        for (int i = 0; i < board.minesweeper.getRows(); i++) {
            for (int j = 0; j < board.minesweeper.getColumns(); j++) {
                if (o == board.buttons[i][j]) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        return coord;
    }

    private void reset() {
        board.matrix = board.minesweeper.createGame();
//        board.startNewGame.setText("reset");
        for (int i = 0; i < board.minesweeper.getRows(); i++) {
            for (int j = 0; j < board.minesweeper.getColumns(); j++) {
                board.buttons[i][j].setText("");
                board.buttons[i][j].setEnabled(true);
                board.buttons[i][j].removeMouseListener(this);
                board.buttons[i][j].addMouseListener(this);
            }
        }
        board.winMatrix = new boolean[board.minesweeper.getRows()][board.minesweeper.getColumns()];
        board.setFlags(board.minesweeper.getMines());
        board.flags.setText(Integer.toString(board.getFlags()));
        lost = false;
        running = false;
    }
    /**
     * Opens the button. If it is a mine, the game is lost. If it is empty,
     * it will open all the surrounding empty squares.
     * 
     * @param row       the button's row-coordinate
     * @param column    the button's column-coordinate
     */
    public void openButtons(int row, int column) {
        if (board.minesweeper.indexOutsideMatrix(row, column)) {
            return;
        }
        if (board.visited[row][column]) {
            return;
        }
        if (board.buttons[row][column].getText().equals("|>")) {
            return;
        }
        if (board.matrix[row][column] != '0') {
            board.buttons[row][column].setEnabled(false);
            board.buttons[row][column].setText(Character.toString(board.matrix[row][column]));
            board.visited[row][column] = true;
            if (board.matrix[row][column] == '*') {
                lost = true;
                
            } else {
                board.winMatrix[row][column] = true;
            }


        } else {
            board.buttons[row][column].setEnabled(false);
            board.visited[row][column] = true;
            board.winMatrix[row][column] = true;
            openButtons(row - 1, column);
            openButtons(row + 1, column);
            openButtons(row, column - 1);
            openButtons(row, column + 1);
            openButtons(row - 1, column - 1);
            openButtons(row - 1, column + 1);
            openButtons(row + 1, column + 1);
            openButtons(row + 1, column - 1);
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
        if (board.minesweeper.indexOutsideMatrix(row, column)) {
            return;
        }
        if (board.buttons[row][column].getText().equals("|>")) {
            board.buttons[row][column].setText("");
            board.setFlags(board.getFlags() + 1);
            board.winMatrix[row][column] = false;
        } else {
            board.buttons[row][column].setText("|>");
            board.setFlags(board.getFlags() - 1);
            if (board.matrix[row][column] == '*') {
                board.winMatrix[row][column] = true;
            }
        }
        board.flags.setText(Integer.toString(board.getFlags()));
    }

    /**
     * checks if the winMatrix is true for all values in the matrix
     * 
     * @return true if all mines are marked with flags and all the other squares are open, 
     *         otherwise false
     */
    public boolean win(MouseEvent e) {
        if (board.getFlags() != 0) {
            return false;
        }
        stopTime = e.getWhen();
        for (int i = 0; i < board.winMatrix.length; i++) {
            for (int j = 0; j < board.winMatrix[i].length; j++) {
                if (!board.winMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void lost(MouseEvent e) {
        stopTime = e.getWhen();
        System.out.println("You lost!");
        System.out.println("Time: " + countTime() + " seconds");
    }
    
    private int countTime() {
        return (int)((stopTime - startTime)/1000.0);
    }
}
