package GameLogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Madeleine Ekblom
 */
public class ReadFileMain {
    static String[] vector = {"a","b","c", "c", "d", "e", "f", "g"};
    private static File file = new File("apa.txt");

    public static void main(String[] args) throws FileNotFoundException, IOException {
//        
//       BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//        for (int i = 0; i < 5; i++) {
//            String text = vector[i] + "#" +Integer.toString(i) + "\n";
//            writer.append(text);
//           
//        }
//        writer.close();
//        
//        Scanner reader = new Scanner(file);
//        String[][] results = new String[30][2];
//        int i = 0;
//        
//        while(reader.hasNextLine()) {
//            String[] help = reader.nextLine().split("#");
//            results[i][0] = help[0];
//            results[i][1] = help[1];
//            i++;
//        }
//        
//        reader.close();
//        
//        for (int k = 0; k < 5; k++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.print(results[k][j] + " ");
//            }
//            System.out.println("");
//        }
//        
        
        Highscore player = new Highscore();
        player.writeIntoFile("Apa", 13, 1);
        player.writeIntoFile("Gorilla", 100,1);
        
        String[][] results = player.getHighscorelist(new File("Results/Beginner.txt"));
        
        for (int i = 0; i < 3; i++) {
            System.out.println(results[i][0] + " " + results[i][1]);
        }
    }
    
}
