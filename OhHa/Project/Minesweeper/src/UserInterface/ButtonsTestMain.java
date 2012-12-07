package UserInterface;

import GameLogic.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Madeleine Ekblom
 */

//I have used this class to test how to use buttons
public class ButtonsTestMain extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private final char[][] matrix;
    private JButton startGame = new JButton("New Game");
    Game testGame;
    private boolean[][] visited;
    public JButton flags = new JButton("flag");
    public JButton dig = new JButton("dig");
    boolean digMine = true;
    

    public ButtonsTestMain() {
        testGame = new Game(10, 10, 5);
        matrix = testGame.createGame();
        visited = new boolean[testGame.getRows()][testGame.getColumns()];
        buttons = new JButton[testGame.getRows()][testGame.getColumns()];
        setLayout(new GridLayout(testGame.getRows()+1, testGame.getColumns()));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                buttons[i][j] = new JButton("", new ImageIcon("mine.gif"));
                add(buttons[i][j]);
                buttons[i][j].setEnabled(true);
                buttons[i][j].addActionListener(this);
                
//                buttons[i][j].addMouseListener(new MyMouseListener(this));

            }
            add(dig);
            dig.setEnabled(false);
            dig.setActionCommand("enable");
            dig.addActionListener(this);
            add(flags);
            flags.setEnabled(true);
            flags.setActionCommand("disable");
            flags.addActionListener(this);
//            flags.addMouseListener(new MyMouseListener(this) );
            add(startGame);
            
            startGame.setActionCommand("newgame");
            startGame.addActionListener(this);
//            addMouseListener(mouse);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        mouseClick = mouse.getMouseClick();
        
        if (e.getSource() == startGame) {
            startGame.setEnabled(false);
            
        }
        if (e.getSource() == dig) {
            dig.setEnabled(false);
            flags.setEnabled(true);
            digMine = true;
        }
        if (e.getSource() == flags) {
            dig.setEnabled(true);
            flags.setEnabled(false);
            digMine = false;
        }
        
        
        Object help = e.getSource();
        int row = -1;
        int column = -1;

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (help == buttons[i][j]) {
                    row = i;
                    column = j;
                    break;
                }
            }
        }
        if (digMine) {
        openButtons(row, column);
        } else {
            markWithFlag(row, column);
        }

    }

    public void openButtons(int row, int column) {
        if (testGame.indexOutsideMatrix(row, column)) {
            return;
        }
        if (visited[row][column]) {
            return;
        }
        if (matrix[row][column] != '0') {
            buttons[row][column].setEnabled(false);
            buttons[row][column].setText(Character.toString(matrix[row][column]));
            visited[row][column] = true;
            if (matrix[row][column] == '*') {
                System.out.println("You lost!");
                 
            }
        } else {
            buttons[row][column].setEnabled(false);
            visited[row][column] = true;
                openButtons(row - 1, column);
                openButtons(row + 1, column);
                openButtons(row, column - 1);
                openButtons(row, column + 1);
                openButtons(row-1, column-1);
                openButtons(row-1, column+1);
                openButtons(row+1, column+1);
                openButtons(row+1, column-1);
        }
            return;
    }
    
    public void markWithFlag(int row, int column) {
        if (testGame.indexOutsideMatrix(row, column)) {
            return;
        }
        buttons[row][column].setText("|>");
        
    }

    public static void main(String[] args) {
        ButtonsTestMain window = new ButtonsTestMain();
        window.setTitle("Minesweeper");
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
