package UserInterface;


import GameLogic.GameMoves;
import GameLogic.Highscore;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11-01
 */
public class GameBoard extends JFrame {

    public JButton[][] buttons;
//    public JButton startNewGame;
    public GameMoves move;
    public JPanel panel;
    private MyMouseListener mouse;
    public JButton flags;
    private JMenuBar menubar = new JMenuBar();
    public JMenuItem beginner;
    public JMenuItem medio;
    public JMenuItem master;
    public JMenuItem quit;
    public int row;
    public int column;
    public int mine;
    private Highscore scores = new Highscore();
    
    /** Constructor
     * 
     * @param rows      numbers of rows in the gameboard
     * @param columns   numbers of columns in the gameboard
     * @param mines     numbers of mines in the gameboard
     */
    public GameBoard(final int rows, final int columns, final int mines) throws FileNotFoundException {

        move = new GameMoves(rows, columns, mines);
        buttons = new JButton[rows][columns];
        mouse = new MyMouseListener(this);
        this.row = rows;
        this.column = columns;
        this.mine = mines;
        
        createGameBoard();
        
    }

    /**
     * Builds up the menubar containg two menus and several submenus. 
     */
    public void buildMenu() throws FileNotFoundException {
        JMenu menuHelp = new JMenu("Help");
        menubar.add(menuHelp);
        
        JMenu subHow = new JMenu("How to play");
        menuHelp.add(subHow);
        
        JMenuItem item = new JMenuItem("Open a button pressing the mouse's left button. " 
                + "Mark a button pressing right button. "
                + "Find all the mines and open the others as fast as possible");
        subHow.add(item);
        
        JMenu subStart = new JMenu("New game");
        menuHelp.add(subStart);
        
        quit = new JMenuItem("Quit");
        menuHelp.add(quit);
        quit.addActionListener(mouse);
        
        beginner = new JMenuItem("Greenhorn");
        subStart.add(beginner);
        beginner.addActionListener(mouse);
        
        medio = new JMenuItem("Normal");
        subStart.add(medio);
        medio.addActionListener(mouse);
        
        master = new JMenuItem("Master");
        subStart.add(master);
        master.addActionListener(mouse);
        
        JMenu menuStat= new JMenu("Statistics");
        menubar.add(menuStat);
        
        JMenu top5 = new JMenu("Top 5 result"); // results form highscore list 
        menuStat.add(top5);
        
        JMenu level1 = new JMenu("Greenhorn");
        JMenu level2 = new JMenu("Normal");
        JMenu level3 = new JMenu("Master");
                
        top5.add(level1);
        top5.add(level2);
        top5.add(level3);
        
        addHighscore(level1, 1);
        addHighscore(level2, 2);
        addHighscore(level3, 3);
        
    }
    /**
     * Adds highscore list to the specific level and JMenu.
     * 
     * @param level         The JMenu that the results are added to
     * @param n             Level of the game: 1, 2 or 3
     * @throws FileNotFoundException   if file is not found
     */
    private void addHighscore(JMenu level, int n) throws FileNotFoundException {
        if (n > 3 || n < 0) {
            return;
        }
        String[][] results = scores.getTop5(n);
        for (int i = 0; i < results.length; i++) {
            level.add(new JMenuItem(i+1 + ". " + results[i][0] + " " + results[i][1]));
        }
    }
    
    /**
     * Creates a new jframe with a gridlayout, eg. the gameboard. The gameboard 
     * contains square-buttons and a new game-button.
     */
    private void createGameBoard() throws FileNotFoundException {
        //frame = new JFrame("Minesweeper");
        
        buildMenu();
        this.setJMenuBar(menubar);
        ImageIcon icon = new ImageIcon("Pictures/mine.gif");      
        Image im = icon.getImage();
        im = im.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("Pictures/mine.gif"));
//        this.setIconImage(im);
        this.setTitle("Minesweeper");

        this.setResizable(true);
        this.setPreferredSize(new Dimension((move.game.getRows()+1)*50, move.game.getColumns()*50));
//        this.setLayout(new GridLayout(minesweeper.getRows() + 1, minesweeper.getColumns()));
        addButtonsToFrame();
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.validate();
    }

    /**
     * Adds buttons to the frame, gives the button a mouselistener, and enables the buttons
     */
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
//        startNewGame = new JButton("New Game");
//        startNewGame.setSize(3,3);
//        panel.add(startNewGame);
//        startNewGame.addMouseListener(mouse);
        this.add(panel);
    }
    
    
}
