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
    double p = 0.01; // precision
    
    
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
    public void testSortMethodForOneRowMatrix() {
        String[][] list = {{"Apa", "300"}};
        String[][] result = scores.sortList(list);
        
        assertEquals(list, result);
        
    }
    
    @Test
    public void testSortListMethodForSmallMatrix() {
        String[][] list = {{"Apa", "52"}, {"Gorilla", "2"}, {"Meh", "23"}};
        
        String[][] help = scores.sortList(list);
        
        String[][] correctResult = {{"Gorilla", "2"},{"Meh", "23"},{"Apa", "52"}};
        
        assertEquals(correctResult, help);
    }
    
    @Test
    public void testSortListMethodWhereAllTimesAreEqual() {
        String[][] list = {{"Apa", "5"}, {"Apa2", "5"}, {"Apa3", "5"}};
        String[][] result = scores.sortList(list);
        
        assertEquals(list, result);
    }
    
    @Test
    public void testSortListMethodLargerMatrix() {
        String[][] list = {{"Apa", "3"}, {"Apa2", "53"}, {"Apa3", "5"}, {"Apppp", "1"}, {"Apa4", "5"}};
        String[][] result = scores.sortList(list);
        
        String[][] correctResult = {{"Apppp", "1"},{"Apa", "3"}, {"Apa3", "5"}, {"Apa4", "5"}, {"Apa2", "53"}};
        
        assertEquals(correctResult, result);
    }
    
    
}
