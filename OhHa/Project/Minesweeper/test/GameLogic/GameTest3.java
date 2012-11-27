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
 */
public class GameTest3 {
    Game game;
    double p = 0.01; //precision
    
    public GameTest3() {
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
    public void checkThatRandomizingGameBoardsWorks() {
        game = new Game();
        char[][] matrix = game.createGame();
        char[][] help;
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            help = game.createGame();
            if (matrix == help) {
                sum++;
            }
        }
        assertEquals(0, sum, p);
    }
    
    @Test
    public void checkThatRandomizingSmallGameBoardWorks() {
        game = new Game(5,5,4);
        char[][] matrix = game.createGame();
        char[][] help;
        int sum = 0;
        
        for (int i = 0; i < 10; i++) {
            help = game.createGame();
            if (matrix == help) {
                if (matrix == help) {
                    sum++;
                }
            }
        }
        assertEquals(0, sum, p);
    }
    
    @Test 
    public void checkTwoSimilarGameBoardsOccursLessOftenThanPrb() {
        game = new Game(4,4,1); // gives prb 1/16*100
        char[][] matrix = game.createGame();
        char[][] help;
        int sum = 0;
        boolean prb = false;
        
       
        for (int i = 0; i < 100; i++){
            help = game.createGame();
            if (help == matrix) {
                sum++;
            }
        }
        if (sum < 1/16*100 + 4) {
            prb = true;
        }
        
        assertEquals(true, prb);
    }
    
    @Test
    public void checkThatTwoLargeGameBoardsAreEqualLessThan1Per() {
        game = new Game(10,10,10);
        char[][] matrix = game.createGame();
        char[][] help;
        int sum = 0;
        boolean prb = false;
        
        for (int i = 0; i < 500; i++) {
            help = game.createGame();
            if (help == matrix) {
                sum++;
            }
        }
       
        if (sum < 5) {
            prb = true;
        }
        
        assertEquals(true, prb);
    }
}


