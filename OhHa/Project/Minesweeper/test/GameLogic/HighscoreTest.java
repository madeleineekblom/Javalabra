package GameLogic;

import java.io.File;
import java.io.IOException;
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
public class HighscoreTest {
    Highscore scores = new Highscore();
    
    
    public HighscoreTest() {
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
    public void checkGetFileForInput1() {
        
        File help = scores.getFile(1);
        File beginner = new File("Results/Beginner.txt");
        
        assertEquals(beginner, help);
    }
    
    @Test
    public void checkGetFileForInput2() {
       
        File help = scores.getFile(2);
        File normal = new File("Results/Normal.txt");
        
        assertEquals(normal, help);
    }
    
    @Test
    public void checkGetFileForInput3() {
       File help = scores.getFile(3);
       File master = new File("Results/Master.txt");
       
       assertEquals(master, help);
    }
    
    @Test
    public void checkGetFileForNegativeInput() {
        File help = scores.getFile(-100);
        File f = new File("f");
        
        assertEquals(f, help);
    }
    
    @Test
    public void checkGetHighScoreListForOneAddedResult() throws IOException {
        
        // First delete all elements in master.txt ... 
        String name = "name";
        int time = 28;
        scores.writeIntoFile(name, time, 3);
        
        String[][] results = scores.getHighscorelist(new File("Results/Master.txt"));
        
        boolean n = false;
        boolean t = false;
        
        if (results[0][0].equals(name)) {
            n = true;
        }
        if (Integer.parseInt(results[0][1]) == time) {
            t = true;
        }
        
        assertEquals(true, n);
        assertEquals(true, t);
    }
}
