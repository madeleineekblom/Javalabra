package UserInterface;

import GameLogic.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Madeleine Ekblom
 */
public class ButtonsTestMain extends JFrame implements ActionListener {

    private JButton[][] buttons;
    private final char[][] matrix;
    private JButton startGame = new JButton("New Game");
//    private boolean mouseClick = true;
//    private MyMouseListener mouse = new MyMouseListener();
//    private boolean mouseClick;
    Game testGame;
    private boolean[][] visited;
    private JButton flags = new JButton("flag");
    private JButton dig = new JButton("dig");
    

    public ButtonsTestMain() {
        testGame = new Game(8,6,1);
        matrix = testGame.createGame();
        visited = new boolean[testGame.getRows()][testGame.getColumns()];
        buttons = new JButton[testGame.getRows()][testGame.getColumns()];
        setLayout(new GridLayout(testGame.getRows()+1, testGame.getColumns()));
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                buttons[i][j] = new JButton();
                add(buttons[i][j]);
                buttons[i][j].setEnabled(true);
                buttons[i][j].addActionListener(this);
                

            }
            add(dig);
            dig.setEnabled(false);
            dig.setActionCommand("enable");
            add(flags);
            flags.setEnabled(true);
            flags.setActionCommand("disable");
            add(startGame);
            
            startGame.setActionCommand("newgame");
            startGame.addActionListener(this);
//            addMouseListener(mouse);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        mouseClick = mouse.getMouseClick();
        boolean digMine = true;
//        if (e.getSource() == startGame) {
//            ButtonsTestMain buttonsTestMain = new ButtonsTestMain();
//            startGame.setText("new");
//        }
        if ("enable".equals(e.getActionCommand())) {
            dig.setEnabled(true);
            flags.setEnabled(false);
            digMine = false;
        } else if ("disable".equals(e.getActionCommand())) {
            dig.setEnabled(false);
            flags.setEnabled(true);
            digMine = true;
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
        if (testGame.indexInBounds(row, column)) {
            return;
        }
        if (visited[row][column]) {
            return;
        }
        if (matrix[row][column] != '0') {
            buttons[row][column].setEnabled(false);
            buttons[row][column].setText(Character.toString(matrix[row][column]));
            visited[row][column] = true;
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
