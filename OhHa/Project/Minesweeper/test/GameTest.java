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
    public void checkConstructorMinesAreMoreThanPlaces() {
        game = new Game(10,10,101);
        assertEquals(10, game.rows, p);
        assertEquals(10, game.columns, p);
        assertEquals(20, game.mines, p);
    }

    @Test
    public void checkConstructorMinesAreLessThanPlaces() {
        for (int i = 10; i < 100; i=i+10) {
            for (int j = 10; j < 100; j=j+10) {
                game = new Game(i,j,(i+j)/2);
                assertEquals(i, game.rows, p);
                assertEquals(j, game.columns, p);
                assertEquals((i+j)/2, game.mines, p);
            }
        }
    }
    
    @Test
    public void checkThatTheNumberOfMinesAre10() {
        game = new Game(5,5,10);
        String[][] gameMatrix = game.createGame();
        int mineSum = 0;
        
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                if (gameMatrix[i][j].equals("*")) {
                    mineSum++;
                }
            }
        }
        
        assertEquals(game.mines, mineSum, p);
    }
    
    @Test
    public void checkThatTheNumberOfMinesAre100() {
        game = new Game(10,10,100);
        String[][] gameMatrix = game.createGame();
        int mineSum = 0;
        
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                if (gameMatrix[i][j].equals("*")) {
                    mineSum++;
                }
            }
        }
        assertEquals(game.mines, mineSum, p);
    }
    
    @Test
    public void checkThatTheMethodCountMinesWorks() {
        game = new Game(10,10,10);
        String[][] matrix = {{"0", "0", "1", "*", "2"},{"0", "0", "2", "3", "*"}, 
            {"1", "1", "2", "*", "2"}, {"2", "*", "2", "1", "1"}, {"*", "2", "1", "0", "0"}};
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int num = game.countMines(matrix, i, j);
                if (!matrix[i][j].equals("*")) {
                    assertEquals(Integer.parseInt(matrix[i][j]), num, p);
                }
            }
        }
    }
}
