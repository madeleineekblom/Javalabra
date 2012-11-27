package UserInterface;

import GameLogic.Player;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

/**
 *
 * @author Madeleine Eklblom
 * @since 2012-11-26
 *
 */
public class WinLose extends JFrame implements ActionListener {

    public JButton startGame = new JButton("New Game");
    public JButton quit = new JButton("Quit");
    public JTextField name = new JTextField("Enter your name:", 20);
    private JLabel result = new JLabel("");
    private int time;
    private int rows;
    private int columns;
    private int mines;
    GameBoard game;
    Player player;
    
    /**
     * Constructor
     * Creates the player
     */
    public WinLose() {
        player = new Player();
    }

    /**
     * adds actionlistener to the buttons (quit and startGame) and to the textfield name
     */
    private void addActions() {
        startGame.addActionListener(this);
        quit.addActionListener(this);
        name.addActionListener(this);
    }

    /**
     * If the game is lost, a window will pop up asking for either to play a new game or to quit
     * @param time  The time that it took to lose the game
     */
    public void lose(int time) {
        addActions();
        result.setText("You lost!\n" + "Time: " + time + " seconds");

        this.setLayout(new BorderLayout());
        this.setTitle("Results");
        this.add("West", startGame);
        this.add("East", quit);
        this.add("North", result);

        this.setPreferredSize(new Dimension(200, 100));
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.validate();

    }
    
    /**
     * If the game is won: Asks the player to enter its name.
     * A button for start a new game and an other to quit
     * @param time  how long the game lasted 
     */
    public void win(int time) {
        this.time = time;
        addActions();
        result.setText("You won!\n" + "Time: " + time + " seconds");

        this.setLayout(new BorderLayout());
        this.setTitle("Results");
        this.add("North", result);
        this.add("South", name);
        this.add("West", startGame);
        this.add("East", quit);

        this.setResizable(false);
        this.setPreferredSize(new Dimension(200, 100));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.validate();

    }

    /**
     * Sets the gameboard parameters so a new game can be started at the same level
     * 
     * @param r         number of rows
     * @param c         number of columns
     * @param m         number of mines
     */
    public void setGameBoard(int r, int c, int m) {
        this.rows = r;
        this.columns = c;
        this.mines = m;
    }

    /**
     * If the source of the event is startGame, a new game will start
     * If the source of the event is quit, the whole game ends and all the windows will be closed
     * If the source of the event is name, the name and the time will be put in a
     * file containg the best results (the file is different for all levels)
     * 
     * @param e     Actionevent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGame) {
            game = new GameBoard(rows, columns, mines);
        }
        if (e.getSource() == quit) {
            System.exit(0);
            game = new GameBoard(rows, columns, mines);
        }
        if (e.getSource() == name) {
            String enteredName = name.getText();
            
            if (enteredName == null || enteredName.isEmpty()) {
                enteredName = "anonymous";
            }
            System.out.println(enteredName + "\nTime = " + time);
        
            int level = getLevel(mines);
        

            try {
                player.writeIntoFile(enteredName, time, level);
            } catch (IOException ex) {
                Logger.getLogger(WinLose.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    /**
     * 
     * @param mines  the level depends on how many mines the gameboard has
     * @return       1 for beginner level, 2 for normal level, 3 for master level, 
     *               otherwise 0 (e.g. a non-specified level)
     */
    private int getLevel(int mines) {
        if (mines == 10) {
            return 1;
        } else if (mines == 40) {
            return 2;
        } else if (mines == 99) {
            return 3;
        }
        return 0;
    }
}
