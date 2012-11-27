package GameLogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Madeleine Ekblom
 */
public class Highscore {

    File file;
    ArrayList<String> players = new ArrayList<String>();
    File beginner = new File("Results/Beginner.txt");
    File normal = new File("Results/Normal.txt");
    File master = new File("Results/Master.txt");
    File f = new File("f");

    public Highscore() {
    }
    
    /**
     * 
     * @param level     1 for greenhorn, 2 for normal, 3 for master
     * @return          the slowest time on the specific level
     * @throws FileNotFoundException 
     */
    public int getSlowestTime(int level) throws FileNotFoundException {
        String[][] list = getHighscorelist(getFile(level));
        return 100;

    }
    
    /**Returns a list of the top five results at a specific level
     * 
     * @param level 1 for greenhorn, 2 for normal, 3 for master
     * @return      a matrix containg the five fastest times and the names
     * @throws FileNotFoundException 
     */
    public String[][] getTop5(int level) throws FileNotFoundException {
        String[][] list = getHighscorelist(getFile(level));
        String[][] results = new String[5][2];
        
        for (int i = 0; i < list[1].length; i++) {
            
        }
        
        
        return results;
    }

    /**
     * 
     * @param level 1, 2, 3 for greenhorn, normal or master
     * @return      the file corresponding to the specific level, otherwise f 
     *              a file that is just a comparing
     */
    protected File getFile(int level) {
        if (level == 1) {
            return beginner;
        } else if (level == 2) {
            return normal;
        } else if (level == 3) {
            return master;
        }
        return f;
    }

    /**Writes the result into a txt-file depending on which level the game is played on
     * 
     * @param name          name of the person  
     * @param time          the time that the player got
     * @param level         1,2 or 3 depending on how large the gameboard was
     * @throws IOException 
     */
    public void writeIntoFile(String name, int time, int level) throws IOException {
        if (getFile(level) == f) {
            return;
        }
        file = getFile(level);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        String text = name + "#" + Integer.toString(time) + "\n";
        writer.append(text);
        writer.close();
    }

    /**Will give back a highscore list for the specific level as a matrix
     * 
     * @param file      the file that the result is taken from
     * @return          a string matrix containg the name and the time in one row
     * @throws FileNotFoundException 
     */
    public String[][] getHighscorelist(File file) throws FileNotFoundException {
        String[][] results = new String[100][2];
        Scanner reader = new Scanner(file);
        int i = 0;

        while (reader.hasNextLine()) {
            String[] help = reader.nextLine().split("#");
            results[i][0] = help[0];
            results[i][1] = help[1];
            i++;
        }
        return results;
    }
}
