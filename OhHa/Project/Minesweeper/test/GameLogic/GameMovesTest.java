package GameLogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Madde
 */
public class GameMovesTest {

    public GameMovesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkOpenButtonsForAnEmptyGame() {
        GameMoves m = new GameMoves(3, 3, 0);
        m.createGameBoard();
        m.openButtons(0, 0);
        boolean[][] help = m.help;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(true, help[i][j]);
            }
        }

    }
    
    // add tests 

}
