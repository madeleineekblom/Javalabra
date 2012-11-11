package UserInterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author Madeleine Ekblom
 */
public class MyMouseListener extends MouseAdapter{
    private boolean mouseClick;
    
    public boolean getMouseClick() {
        return mouseClick;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseClick = true;
            System.out.println("left");
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            mouseClick = false;
            System.out.println("right");
        }
    }

}
