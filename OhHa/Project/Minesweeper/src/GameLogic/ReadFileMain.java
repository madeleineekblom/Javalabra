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
        
        Highscore player = new Highscore();
        player.writeIntoFile("Apa2", 15, 1);
        player.writeIntoFile("Gorilla88", 2, 1);
        player.writeIntoFile("Gorilla3", 2, 1);
        player.writeIntoFile("Apa1", 12, 1);
        player.writeIntoFile("Gorilla1", 13,1);
        
        player.writeIntoFile("Gorilla2", 17,1);
        player.writeIntoFile("Apa3", 50, 1);
      
        String[][] results = {{"Hej", "10"}, {"Apa", "5"},{"iii", "1"}};
        results = player.sortList(results);
        
        String[][] sort = player.getTop5(1);
        
        System.out.println(sort.length + " " + sort[0].length);
       
        
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i][0] + " " + results[i][1]);
        }
        
        
        
    }
    
}
