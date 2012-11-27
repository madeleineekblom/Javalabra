package GameLogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11-04
 */
public class GameTest {

    double p = 0.01; // precision
    Game game;

    public GameTest() {
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
    public void checkConstructorIfEmpty() {
        game = new Game();
        assertEquals(5, game.getRows(), p);
        assertEquals(5, game.getColumns(), p);
        assertEquals(5, game.getMines(), p);
    }
    
    @Test
    public void checkConstructorMinesAreMoreThanPlaces() {
        game = new Game(10, 10, 101);
        assertEquals(10, game.getRows(), p);
        assertEquals(10, game.getColumns(), p);
        assertEquals(10, game.getMines(), p);
    }
    
    @Test 
    public void checkConstructorIfMinesEqaulsNumberOfSquares() {
        game = new Game(10, 10, 100);
        assertEquals(10, game.getRows(), p);
        assertEquals(10, game.getColumns(), p);
        assertEquals(100, game.getMines(), p);
    }

    @Test
    public void checkConstructorMinesAreLessThanPlaces() {
        for (int i = 10; i < 100; i = i + 10) {
            for (int j = 10; j < 100; j = j + 10) {
                game = new Game(i, j, (i + j) / 2);
                assertEquals(i, game.getRows(), p);
                assertEquals(j, game.getColumns(), p);
                assertEquals((i + j) / 2, game.getMines(), p);
            }
        }
    }
    
    @Test 
    public void checkConstructorForRowValueZero() {
        game = new Game(0, 4,4);
        assertEquals(10, game.getRows(), p);
    }
    
    @Test
    public void checkConstructorForColumnValueZero() {
        game = new Game(22, 0, 34);
        assertEquals(10, game.getMines(), p);
    }

    @Test 
    public void checkConstructorForNegativeRowValue() {
        game = new Game(-1, 6, 5);
        assertEquals(10, game.getRows(), p);
    }
   
    @Test
    public void checkConstructorForNegativeColumnValue() {
        game = new Game(9, -100, 5);
        assertEquals(10, game.getColumns(), p);
    }
   
    @Test 
    public void checkConstructorForNegativeMineValues() {
        game = new Game(4,4,-1);
        assertEquals(10, game.getMines(), p);
    }
}
