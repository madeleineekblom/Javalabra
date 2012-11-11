package UserInterface;

import javax.swing.JFrame;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11-06
 */
public class MainInterface {
    public static void main(String[] args) {
        GameBoard window = new GameBoard(5,5,5);
        window.setTitle("Minesweeper");
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        
    }
}
