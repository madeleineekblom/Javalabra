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

    GameMoves m;
    double p = 0.01; //precission

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
        m = new GameMoves(3, 3, 0);
        m.createGameBoard();
        m.openButtons(0, 0);
        boolean[][] help = m.help;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(true, help[i][j]);
            }
        }

    }

    @Test
    public void checkOpenButtonsForABombMatrix() {
        m = new GameMoves(2, 2, 1);
        m.createGameBoard();
        m.openButtons(0, 0);
        boolean[][] help = m.help;

        for (int i = 0; i < m.game.getRows(); i++) {
            for (int j = 0; j < m.game.getColumns(); j++) {
                if (i == 0 && j == 0) {
                    assertEquals(true, help[i][j]);
                } else {
                    assertEquals(false, help[i][j]);
                }
            }
        }
    }

    @Test
    public void checkOpenButtonsNegativeInputs() {
        m = new GameMoves(10, 10, 10);
        m.createGameBoard();
        m.openButtons(-1, -1);
        boolean[][] help = m.help;

        for (int i = 0; i < m.game.getRows(); i++) {
            for (int j = 0; j < m.game.getColumns(); j++) {
                assertEquals(false, help[i][j]);
            }
        }
    }

    @Test
    public void checkOpenButtonsForIndexOutsideMatrix() {
        m = new GameMoves(10, 10, 10);
        m.createGameBoard();
        m.openButtons(20, 2);
        boolean[][] help = m.help;

        for (int i = 0; i < m.game.getRows(); i++) {
            for (int j = 0; j < m.game.getColumns(); j++) {
                assertEquals(false, help[i][j]);
            }
        }
    }

    @Test
    public void checkMarkWithFlagsWorksInMatrix() {
        m = new GameMoves(2, 2, 2);
        m.createGameBoard();
        m.markWithFlags(1, 1);
        boolean[][] flags = m.flagMatrix;

        for (int i = 0; i < m.game.getRows(); i++) {
            for (int j = 0; j < m.game.getColumns(); j++) {
                if (i == 1 && j == 1) {
                    assertEquals(true, flags[i][j]);
                } else {
                    assertEquals(false, flags[i][j]);
                }
            }
        }
    }

    @Test
    public void checkMarkWithFlagsTwiceAtSameCoordinate() {
        m = new GameMoves(4, 4, 3);
        m.createGameBoard();
        m.markWithFlags(3, 1);
        m.markWithFlags(3, 1);

        for (int i = 0; i < m.game.getRows(); i++) {
            for (int j = 0; j < m.game.getColumns(); j++) {
                if (i == 3 && j == 1) {
                    assertEquals(true, m.flagMatrix[i][j]);
                } else {
                    assertEquals(false, m.flagMatrix[i][j]);
                }
            }
        }
    }
   
    @Test 
    public void checkMarkWithFlagsOutsideMatrix() {
        m = new GameMoves(3,6,8);
        m.createGameBoard();
        m.markWithFlags(5,0);
        
        for (int i = 0; i < m.game.getRows(); i++) {
            for (int j = 0; j < m.game.getColumns(); j++) {
                assertEquals(false, m.flagMatrix[i][j]);
            }
        }
    }
    
    @Test
    public void checkUnFlagSquareForAMarkedSquare() {
        m = new GameMoves(4,4,5);
        m.createGameBoard();
        m.markWithFlags(0, 0);
        
        m.unFlagSquare(0, 0);
        
        for (int i = 0; i < m.game.getRows(); i++) {
            for (int j = 0; j < m.game.getColumns(); j++) {
                assertEquals(false, m.flagMatrix[i][j]);
            }
        }
    }
    
    @Test
    public void checkUnFlagSquareForAnUnmarkedSquareFlagsAreCorrect() {
        m = new GameMoves(3,9,4);
        m.createGameBoard();
        m.unFlagSquare(3, 4);
        
        assertEquals(m.game.getMines(), m.getFlags(), p);
    }
    
    @Test
    public void checkThatNumberOfFlagsAreCorrectAfterMarking5() {
        m = new GameMoves(4,4,10);
        m.createGameBoard();
        m.markWithFlags(0, 0);
        m.markWithFlags(1, 0);
        m.markWithFlags(0, 1);
        m.markWithFlags(2, 0);
        m.markWithFlags(3, 0);
        
        assertEquals(m.game.getMines()-5, m.getFlags(), p); 
    }
}
