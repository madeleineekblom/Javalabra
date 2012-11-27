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
public class Player {

    File file;
    ArrayList<String> players = new ArrayList<String>();
    File beginner = new File("Results/Beginner.txt");
    File normal = new File("Results/Normal.txt");
    File master = new File("Results/Master.txt");
    File f = new File("f");

    public Player() {
    }

    public int getSlowestTime(int level) throws FileNotFoundException {
        String[][] list = getHighscorelist(getFile(level));
        return 100;

    }
    
    public String[][] getTop5(int level) throws FileNotFoundException {
        String[][] list = getHighscorelist(getFile(level));
        String[][] results = new String[5][2];
        
        for (int i = 0; i < list[1].length; i++) {
            
        }
        
        
        return results;
    }

    private File getFile(int level) {
        if (level == 1) {
            return beginner;
        } else if (level == 2) {
            return normal;
        } else if (level == 3) {
            return master;
        }
        return f;
    }

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
