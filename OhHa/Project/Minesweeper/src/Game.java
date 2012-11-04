/*
 * @author Madeleine Ekblom
 * @since 2012-10-30
 * 
 * 
 */

import java.util.*;

public class Game {
    
    public int rows;
    public int columns;
    public int mines;
    private String[] numbersString = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    
    /* Constructor
     * 
     * @param rows      numbers of rows in the gameboard
     * @param columns   numbers of columns in the gameboard
     * @param mines     number of mines in the game
     * 
     */
    public Game(int rows, int columns, int mines) {
        if (mines < rows*columns) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        } else {
            this.rows = 10;
            this.columns = 10;
            this.mines = 20;
        }
    }
    
    /**
     * Creates a new game or actually a gameboard
     * 
     * @return  returns a String-matrix where the mines are stars and numbers for the other 
     *          squares depending on how many mines that are aroung 
     *          (0 means empty sqaure)
     */
    public String[][] createGame() {
        String[][] matrix = new String[rows][columns];
        int[][] bombs = placeMines();
        int r;
        int c;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = "0";
            }
        }
        for (int i = 0; i < bombs.length; i++) {
            r = bombs[i][0];
            c = bombs[i][1];
            matrix[r][c] = "*";
        }
        
        int index;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                 index = countMines(matrix, i, j);
                 if (index != 0) {
                     matrix[i][j] = numbersString[index];
                 }
            }
        }
        return matrix;
    }
    
    /**
     * Genetates a matrix containing the coordinates for the mines 
     * 
     * @return a matrix containg all the coordinates for the mines
     */
    private int[][] placeMines() {
        int num = 1;
        int[][] bombCoord = new int[mines][2];
        int[][] matrix = new int[rows][columns]; /* this matrix is a help matrix, where all
                                                  * "places" has their own number between
                                                  * 1...x (x=rows*columns)
                                                  */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = num;
                num++;
            }
        }
        int help = mines;
        Random randomizer = new Random();
        
        /* randomizes a number between 1 and rows*columns and searches the coordinates
         * for the square, replaces the number to 0 if there wasn't a mine there
         * if there was a mine (0) continues
         * 
         */
        while (help > 0) {
            int bomb = randomizer.nextInt(rows*columns) + 1;
            int[] coord = searchCoordinates(bomb, matrix);
            if (noBombIsPlaced(matrix, coord)) {
                matrix[coord[0]][coord[1]] = 0;
                help--;
                bombCoord[help][0] = coord[0];
                bombCoord[help][1] = coord[1];
            }
        }
        
        return bombCoord;
    }
    
    /**
     * Checks if a mine is placed in a specific coordinate in the matrix
     * 
     * @param matrix    matrix where the mine might be
     * @param coord     coordinates for the mine
     * @return          returns true if there is no mine in the given coordinate,  
     *                  otherwise false
     */
    private boolean noBombIsPlaced(int[][] matrix, int[] coord) {
        int[] help = {-1, -1};
        
        if(matrix[coord[0]][coord[1]] == 0 || coord == help) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Searches the coordinates for a bomb 
     * 
     * @param bomb      is a number between 1 and rows*columns
     * @param matrix    the matrix that has numbers between 0...rows*columns 
     *                  0 means there is already a mine placed there
     * @return          returns coordinates for the place where a mine should be placed,
     *                  if there is already a mine placed in the given coordinates 
     *                  then it return {-1, -1}
     */
    private int[] searchCoordinates(int bomb, int[][] matrix) {
        int[] coord = new int[2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == bomb) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        return coord;
    }
    /**
     * Counts how many mines that are placed around a specific square 
     * 
     * @param matrix    the matrix that is checks
     * @param r         the square's row-coordinate
     * @param c         the square's column-coordinate
     * @return          returns how many mines that are around the square, 
     *                  a number between 0 and 8
     */
    public int countMines(String[][] matrix, int r, int c) {
        int sum = 0;
        if (matrix[r][c].equals("*")) {
            return 0;
        }
        else {
            for (int i = r-1; i < r+2; i++) {
                for (int j = c-1 ; j < c+2; j++) {
                    if (i < 0 || j < 0 || i > rows-1 || j > columns-1 || (i == r && j == c)) {
                        continue;
                    } else {
                        if (matrix[i][j].equals("*")) 
                            sum++;
                    }
                }
            } 
            return sum;
        }
    }       
}