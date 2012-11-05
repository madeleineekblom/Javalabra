
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Madeleine Ekblom
 */
public class Test extends JFrame {

    private final JButton b11;
    private final JButton b12;
    private final JButton b21;
    private final JButton b22;
    private final String[][] matrix;

    public Test() {
        Game testGame = new Game(2, 2, 1);
        matrix = testGame.createGame();
        setLayout(new GridLayout(testGame.rows, testGame.columns));
        b11 = new JButton();
        b11.setActionCommand("visible");
        b12 = new JButton();
        b12.setActionCommand("visible");
        b21 = new JButton();
        b21.setActionCommand("visible");
        b22 = new JButton();
        b22.setActionCommand("visible");

        add(b11);
        add(b12);
        add(b21);
        add(b22);

        b11.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if ("visible".equals(event.getActionCommand())) {
                            b11.setEnabled(false);
                            b11.setText(matrix[0][0]);
                        }
                    }
                });
        b12.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if ("visible".equals(event.getActionCommand())) {
                    b12.setEnabled(false);
                    b12.setText(matrix[0][1]);
                }
            }
        });
        b21.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if ("visible".equals(event.getActionCommand())) {
                    b21.setEnabled(false);
                    b21.setText(matrix[1][0]);
                }
            }
        });
        b22.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                if ("visible".equals(event.getActionCommand())) {
                    b22.setEnabled(false);
                    b22.setText(matrix[1][1]);
                }

            }
        });






    }

    public static void main(String[] args) {
        Test window = new Test();
        window.setTitle("Minesweeper");
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
