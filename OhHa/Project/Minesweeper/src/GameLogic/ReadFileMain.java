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
        player.writeIntoFile("Apa2", 15, 1);
        player.writeIntoFile("Gorilla88", 2, 1);
        player.writeIntoFile("Gorilla3", 2, 1);
        player.writeIntoFile("Apa1", 12, 1);
        player.writeIntoFile("Gorilla1", 13,1);
        
        player.writeIntoFile("Gorilla2", 17,1);
        player.writeIntoFile("Apa3", 50, 1);
        
       
        
        File level1 = new File("Results/Beginner.txt");
        
      
        String[][] results = player.getHighscorelist(level1);
        player.sortList(results);
        
        String[][] sort = player.getTop5(1);
        
        System.out.println(sort.length + " " + sort[0].length);
       
        
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i][0] + " " + results[i][1]);
        }
        
        for (int i = 0; i < 5; i++) {
            System.out.println(sort[i][0] + " " + sort[i][1]);
        }
    }
    
}
