package UserInterface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Madeleine Eklblom
 * @since 2012-11-26
 *
 */
public class WinLose extends JFrame implements ActionListener {

    public JButton startGame = new JButton("New Game");
    public JButton quit = new JButton("Quit");
    public JTextField name = new JTextField("Enter your name:");
    private JLabel result = new JLabel("");
    private int time;

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

        this.setResizable(false);
        this.setPreferredSize(new Dimension(200, 100));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.validate();

    }

    public void newGame() {
        
        this.setTitle("New game?");
        this.setLayout(new BorderLayout());
        addActions();
        
        this.add("North", result);
        this.add("South", name);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(200, 100));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGame) {
            this.dispose();
            GameBoard board = new GameBoard(10, 10, 10);
        }
        if (e.getSource() == quit) {
            this.dispose();
        }
        if (e.getSource() == name) {
            String enteredName = name.getText();
            System.out.println(enteredName + "\nTime = " + time);
            this.dispose();
            this.removeAll();
            newGame();
        }
    }
}
