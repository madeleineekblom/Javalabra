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

    private void addActions() {
        startGame.addActionListener(this);
        quit.addActionListener(this);
        name.addActionListener(this);
    }

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

    public void setGameBoard(int r, int c, int m) {
        this.rows = r;
        this.columns = c;
        this.mines = m;
    }

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
            System.out.println(enteredName + "\nTime = " + time);
        
            int level = getLevel(mines);
        

            try {
                player.writeIntoFile(enteredName, time, level);
            } catch (IOException ex) {
                Logger.getLogger(WinLose.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    
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
