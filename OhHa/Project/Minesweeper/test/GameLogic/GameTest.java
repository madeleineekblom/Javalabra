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
    public void checkConstructorMinesAreMoreThanPlaces() {
        game = new Game(10, 10, 101);
        assertEquals(10, game.getRows(), p);
        assertEquals(10, game.getColumns(), p);
        assertEquals(20, game.getNumberOfMines(), p);
    }

    @Test
    public void checkConstructorMinesAreLessThanPlaces() {
        for (int i = 10; i < 100; i = i + 10) {
            for (int j = 10; j < 100; j = j + 10) {
                game = new Game(i, j, (i + j) / 2);
                assertEquals(i, game.getRows(), p);
                assertEquals(j, game.getColumns(), p);
                assertEquals((i + j) / 2, game.getNumberOfMines(), p);
            }
        }
    }

    @Test
    public void checkThatTheNumberOfMinesAre10() {
        game = new Game(5, 5, 10);
        char[][] gameMatrix = game.createGame();
        int mineSum = 0;

        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                if (gameMatrix[i][j] == '*') {
                    mineSum++;
                }
            }
        }

        assertEquals(game.getNumberOfMines(), mineSum, p);
    }

    @Test
    public void checkThatTheNumberOfMinesAre100() {
        game = new Game(10, 10, 100);
        char[][] gameMatrix = game.createGame();
        int mineSum = 0;

        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                if (gameMatrix[i][j] == '*') {
                    mineSum++;
                }
            }
        }
        assertEquals(game.getNumberOfMines(), mineSum, p);
    }

    @Test
    public void checkCountMinesForASmallMatrix() {
        game = new Game(2, 2, 1);
        char[][] matrix = {{'*', '1'}, {'1', '1'}};
        int numMine;
        String s;
        char c;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != '*') {
                    numMine = game.countMines(matrix, i, j);
                    c = matrix[i][j];
                    s = Character.toString(c);
                    assertEquals(numMine, Integer.parseInt(s), p);
                }
            }
        }
    }
    
    @Test
    public void checkCountMinesForA2x3Matrix() {
        game = new Game(2,3,1);
        char[][] matrix = {{'*', '1', '0'}, {'1', '1', '0'}};
        
        int numMine;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != '*') {
                    numMine = game.countMines(matrix, i, j);
                    assertEquals(numMine, Integer.parseInt(Character.toString(matrix[i][j])), p);
                }
            }
        }
    }
    
    @Test
    public void checkCountMinesForASquareWithNumber8() {
        game = new Game(3,3,8);
        char[][] m = {{'*', '*', '*'}, {'*', '8', '*'}, {'*', '*', '*'}};
        int numMine = game.countMines(m, 1, 1);
        
        assertEquals(numMine,8,p);
        
    }
  
   
}
